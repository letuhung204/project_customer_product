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
import com.rfx.api.model.RfxDocumentTypesCreate;
import com.rfx.api.model.RfxDocumentTypesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class RfxDocumentTypeController{
    private ErrorResponse errorResponse = null;

    private SuccessResponse successResponse = null;
    
	private static final Logger logger = LoggerFactory.getLogger(RfxDocumentTypeController.class);
    
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private RfxAdminService rfxAdminService;
    
    
	
    /**
     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
     * This method is for cobrand login and will get the api session token to access other apis.
     *
     * @return Jwt token
     * @throws Exception
     */
	
    @RequestMapping(value = "/api/rfx/rfx-doucument-types/", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
    
    
	@RequestMapping(value = "/api/rfx/rfx-doucument-types/create", method = RequestMethod.POST)
    public ResponseEntity<?> createRfxDocumentType(@RequestBody RfxDocumentTypesCreate rfxDocumentTypesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (rfxDocumentTypesCreate.getName() != Constants.EMPTY && rfxDocumentTypesCreate.getCode() != Constants.EMPTY && rfxDocumentTypesCreate.getDescription() != Constants.EMPTY) {
                if (!rfxAdminService.nameExistInRfxDocumentType(rfxDocumentTypesCreate.getName())) {
                    if(rfxAdminService.rfxDocumentTypeCodeExist(rfxDocumentTypesCreate.getCode()) != null) {
                        return getErrorResponseEntity(Constants.RFX_DOC_TYPE_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    } else {
                    	logger.info("createRfxDocumentType");
                        rfxAdminService.createRfxDocumentType(rfxDocumentTypesCreate);
                        return getSuccessResponseEntity(Constants.CREATE_SUCCESS_RFX_DOC_TYPE);
                    }
                } else {
                    return getErrorResponseEntity(Constants.RFX_DOC_TYPE_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	@RequestMapping(value = "/api/rfx/rfx-doucument-types/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editRfxDocumentType(@RequestBody RfxDocumentTypesCreate rfxDocumentTypesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (rfxDocumentTypesCreate.getRfx_document_type_id()!= Constants.EMPTY && rfxDocumentTypesCreate.getName() != Constants.EMPTY && rfxDocumentTypesCreate.getCode() != Constants.EMPTY && rfxDocumentTypesCreate.getDescription() != Constants.EMPTY) {
               
            	
            		if (rfxAdminService.rfxDocumentTypeIdExist(rfxDocumentTypesCreate.getRfx_document_type_id())) {
            			
            			if ((!rfxAdminService.editRfxDocumentTypeNameExist(rfxDocumentTypesCreate.getName()))||  rfxAdminService.editNameExistByRfxDocumentTypeIdAndName(rfxDocumentTypesCreate.getRfx_document_type_id(), rfxDocumentTypesCreate.getName())) {
                            
            			
                        String rfx_document_type_id = rfxAdminService.rfxDocumentTypeCodeExist(rfxDocumentTypesCreate.getCode());
                      
                        if( (rfx_document_type_id == null) || (rfx_document_type_id != null && rfx_document_type_id.equalsIgnoreCase(rfxDocumentTypesCreate.getRfx_document_type_id()))) {
                        	logger.info("editRfxDocumentType");
                        	if (rfxAdminService.updateRfxDocumentType(rfxDocumentTypesCreate) != 0)
                            	
                                return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_DOC_TYPE);
                            else
                                return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        } else {
                            return getErrorResponseEntity(Constants.RFX_DOC_TYPE_CODE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        }
                        
                    } else {
                        return getErrorResponseEntity(Constants.RFX_DOC_TYPE_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    }

                } else {
                    return getErrorResponseEntity(Constants.INVALID_RFX_DOC_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	@RequestMapping(value = "/api/rfx/rfx-doucument-types/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeRfxDocumentStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "rfx_document_type_id", defaultValue = "") String rfx_document_type_id) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (rfx_document_type_id != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.rfxDocumentTypeIdExist(rfx_document_type_id)) {
                	logger.info("changeRfxDocumentStatus");
                    return getSuccessResponseEntity(rfxAdminService.setRfxDocumentTypeStatus(rfx_document_type_id, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_RFX_DOC_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	  @RequestMapping(value = "/api/rfx/rfx-doucument-types/search", method = RequestMethod.POST)
	    public ResponseEntity<?> searchRfxDocument(@RequestBody RfxDocumentTypesSearch rfxDocumentTypesSearch) throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	        	logger.info("searchRfxDocument");
	            return ResponseEntity.ok(rfxAdminService.rfxDocumentTypesSearch(rfxDocumentTypesSearch));
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }

	    }
	  
	   @RequestMapping(value = "/api/rfx/rfx-doucument-types/get/list", method = RequestMethod.GET)
	    public ResponseEntity<?> getListRfxDocument() throws Exception {
	        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	        	logger.info("List Rfx Document");
	            return ResponseEntity.ok(rfxAdminService.getRfxDocumentTypesList());
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
