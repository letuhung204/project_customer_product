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
import com.rfx.api.model.RfxCategoriesCreate;
import com.rfx.api.model.RfxTypesCreate;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class RfxTypesController {
	
	    private ErrorResponse errorResponse = null;

	    private SuccessResponse successResponse = null;
	    
	    private static final Logger logger = LoggerFactory.getLogger(RfxTypesController.class);
	    

	    
	    @Autowired
	    private RfxAdminService rfxAdminService;
	    
	    
		
	    /**
	     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
	     * This method is for cobrand login and will get the api session token to access other apis.
	     *
	     * @return Jwt token
	     * @throws Exception
	     */
		
	    @RequestMapping(value = "/api/rfx/rfx-types/", method = RequestMethod.PUT)
	    public ResponseEntity<?> check() throws Exception {
	        return ResponseEntity.ok("Rendered");
	    }
	    
	    

		@RequestMapping(value = "/api/rfx/rfx-types/create", method = RequestMethod.POST)
	    public ResponseEntity<?> createRfxTypes(@RequestBody RfxTypesCreate rfxTypesCreate) throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
	            if (rfxTypesCreate.getName() != Constants.EMPTY && rfxTypesCreate.getCode() != Constants.EMPTY && rfxTypesCreate.getDescription() != Constants.EMPTY) {
	                if (!rfxAdminService.nameExistInRfxTypes(rfxTypesCreate.getName())) {
	                    if(rfxAdminService.rfxTypeCodeExist(rfxTypesCreate.getCode()) != null) {
	                        return getErrorResponseEntity(Constants.RFX_TYPE_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                    } else {
	                    	logger.info("createRfxTypes");
	                        rfxAdminService.createRfxTypes(rfxTypesCreate);
	                        return getSuccessResponseEntity(Constants.CREATE_SUCCESS_RFX_TYPE);
	                    }
	                } else {
	                    return getErrorResponseEntity(Constants.RFX_TYPE_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                }
	            } else {
	                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	            }
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }

	    }
		

		@RequestMapping(value = "/api/rfx/rfx-types/edit", method = RequestMethod.POST)
	    public ResponseEntity<?> editRfxTypes(@RequestBody RfxTypesCreate rfxTypesCreate) throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	        	
	            if (rfxTypesCreate.getRfx_type_id()!= Constants.EMPTY && rfxTypesCreate.getName() != Constants.EMPTY && rfxTypesCreate.getCode() != Constants.EMPTY && rfxTypesCreate.getDescription() != Constants.EMPTY) {
	                if ( rfxAdminService.rfxTypeIdExist(rfxTypesCreate.getRfx_type_id())) {
	                  
	                	if (!rfxAdminService.editRfxTypeNameExist(rfxTypesCreate.getName()) || rfxAdminService.editNameExistByRfxTypeIdAndName(rfxTypesCreate.getRfx_type_id(),rfxTypesCreate.getName()) ) {
	                        String rfx_type_id = rfxAdminService.rfxTypeCodeExist(rfxTypesCreate.getCode());
	                        if( (rfx_type_id == null) || (rfx_type_id != null && rfx_type_id.equalsIgnoreCase(rfxTypesCreate.getRfx_type_id()))) {
	                        	logger.info("editRfxTypes");
	                        	if (rfxAdminService.updateRfxType(rfxTypesCreate) != 0)
	                            	
	                                return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_RFX_TYPE);
	                            else
	                                return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                        } else {
	                            return getErrorResponseEntity(Constants.RFX_TYPE_CODE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                        }
	                    } else {
	                        return getErrorResponseEntity(Constants.RFX_TYPE_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                    }

	                } else {
	                    return getErrorResponseEntity(  Constants.INVALID_RFX_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                }
	            } else {
	                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	            }
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }

	    }
	    
		

		@RequestMapping(value = "/api/rfx/rfx-types/active", method = RequestMethod.PUT)
	    public ResponseEntity<?> changeRfxTypeStatus(@RequestParam(value = "status", defaultValue = "") String status,
	                                              @RequestParam(value = "rfx_type_id", defaultValue = "") String rfx_type_id) throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	            if (rfx_type_id != Constants.EMPTY && status != Constants.EMPTY) {
	                if (rfxAdminService.rfxTypeIdExist(rfx_type_id)) {
	                	logger.info("changeRfxTypeStatus");
	                    return getSuccessResponseEntity(rfxAdminService.setRfxTypeStatus(rfx_type_id, status));
	                } else {
	                    return getErrorResponseEntity(Constants.INVALID_RFX_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	                }
	            } else {
	                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	            }
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }

	    }
		
		
	    @RequestMapping(value = "/api/rfx/rfx-types/search", method = RequestMethod.POST)
	    public ResponseEntity<?> searchRfxTypes(@RequestBody RfxTypesSearch rfxTypesSearch) throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	        	logger.info("searchRfxTypes");
	            return ResponseEntity.ok(rfxAdminService.rfxTypesSearch(rfxTypesSearch));
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }

	    }
	    
	    @RequestMapping(value = "/api/rfx/rfx/rfx-types/get/list", method = RequestMethod.GET)
	    public ResponseEntity<?> getListRfxTypes() throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	        	logger.info("List Rfx Types");
	            return ResponseEntity.ok(rfxAdminService.getRfxTypesList());
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
