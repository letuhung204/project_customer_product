package com.rfx.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxStatusesCreate;
import com.rfx.api.model.RfxStatusesSearch;
import com.rfx.api.model.RfxTypesCreate;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class RfxStatusesController{
	
	private ErrorResponse errorResponse = null;

    private SuccessResponse successResponse = null;
    
    private static final Logger logger = LoggerFactory.getLogger(RfxStatusesController.class);
    
    
    @Autowired
    private RfxAdminService rfxAdminService;
    /**
     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
     * This method is for cobrand login and will get the api session token to access other apis.
     *
     * @return Jwt token
     * @throws Exception
     */
	
    @RequestMapping(value = "/api/rfx/rfx-statuses/", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
    
	@RequestMapping(value = "/api/rfx/rfx-statuses/create", method = RequestMethod.POST)
    public ResponseEntity<?> createRfxStatuses(@RequestBody RfxStatusesCreate rfxStatusesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (rfxStatusesCreate.getName() != Constants.EMPTY && rfxStatusesCreate.getCode() != Constants.EMPTY && rfxStatusesCreate.getDescription() != Constants.EMPTY) {
                if (!rfxAdminService.nameExistInRfxStatuses(rfxStatusesCreate.getName())) {
                    if(rfxAdminService.rfxStatuesCodeExist(rfxStatusesCreate.getCode()) != null) {
                        return getErrorResponseEntity(Constants.RFX_STATUS_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    } else {
                    	logger.info("createRfxStatuses");
                        rfxAdminService.createRfxStatuses(rfxStatusesCreate);
                        return getSuccessResponseEntity(Constants.CREATE_SUCCESS_RFX_STATUS);
                    }
                } else {
                    return getErrorResponseEntity(Constants.RFX_STATUS_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_STATUS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	

	@RequestMapping(value = "/api/rfx/rfx-statuses/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editRfxStatuses(@RequestBody RfxStatusesCreate rfxStatusesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (rfxStatusesCreate.getRfx_status_id()!= Constants.EMPTY && rfxStatusesCreate.getName() != Constants.EMPTY && rfxStatusesCreate.getCode() != Constants.EMPTY && rfxStatusesCreate.getDescription() != Constants.EMPTY) {
                if ( rfxAdminService.rfxStatusIdExist(rfxStatusesCreate.getRfx_status_id())) {
                	if (!rfxAdminService.editRfxStatusNameExist( rfxStatusesCreate.getName()) ||  rfxAdminService.editNameExistByRfxStatusIdAndName(rfxStatusesCreate.getRfx_status_id(), rfxStatusesCreate.getName())) {
                        String rfx_status_id = rfxAdminService.rfxStatuesCodeExist(rfxStatusesCreate.getCode());
                        if( (rfx_status_id == null) || (rfx_status_id != null && rfx_status_id.equalsIgnoreCase(rfxStatusesCreate.getRfx_status_id()))) {
                        	logger.info("editRfxStatuses");
                        	if (rfxAdminService.updateRfxStatus(rfxStatusesCreate) != 0)
                                return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_RFX_STATUS);
                            else
                                return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        } else {
                            return getErrorResponseEntity(Constants.RFX_STATUS_CODE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return getErrorResponseEntity(Constants.RFX_STATUS_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    }

                } else {
                    return getErrorResponseEntity( Constants.INVALID_RFX_STATUS_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_STATUS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	@RequestMapping(value = "/api/rfx/rfx-statuses/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeRfxStatusesStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "rfx_status_id", defaultValue = "") String rfx_status_id) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (rfx_status_id != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.rfxStatusIdExist(rfx_status_id)) {
                	logger.info("changeRfxStatusesStatus");
                    return getSuccessResponseEntity(rfxAdminService.setRfxStatusesStatus(rfx_status_id, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_RFX_STATUS_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_STATUS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	
    @RequestMapping(value = "/api/rfx/rfx-statuses/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchRfxStatuses(@RequestBody RfxStatusesSearch rfxTypesSearch) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.info("searchRfxStatuses");
            return ResponseEntity.ok(rfxAdminService.rfxStatusesSearch(rfxTypesSearch));
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_STATUS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	   @RequestMapping(value = "/api/rfx/rfx-statuses/get/list", method = RequestMethod.GET)
	    public ResponseEntity<?> getListRfxStatus() throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	        	logger.info("List Rfx statuses");
	            return ResponseEntity.ok(rfxAdminService.getRfxStatusList());
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_STATUS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }

	    }
    
    
    public ResponseEntity<?> getSuccessResponseEntity(String message) {
        successResponse = new SuccessResponse();
        successResponse.setStatus("Success");
        successResponse.setMessage(message);
        successResponse.setStatusCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok(successResponse);
    }

    public ResponseEntity<?> getErrorResponseEntity(String errorMessage, int errorCode, HttpStatus httpStatus) {
        errorResponse = new ErrorResponse();
        errorResponse.setErroMessage(errorMessage);
        errorResponse.setErroCode(errorCode);
        return new ResponseEntity<Object>(
                errorResponse, new HttpHeaders(), httpStatus);
    }

}
