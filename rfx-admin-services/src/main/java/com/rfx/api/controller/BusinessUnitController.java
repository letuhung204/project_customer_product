package com.rfx.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfx.api.model.BusinessUnitCreate;
import com.rfx.api.model.BusinessUnitSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;


@CrossOrigin(origins = "*")
@RestController
public class BusinessUnitController   {
	@Autowired
    private RfxAdminService rfxAdminService;
	
    private ErrorResponse errorResponse = null;

    private SuccessResponse successResponse = null;
    
    private static final Logger logger = LoggerFactory.getLogger(BusinessUnitController.class);
	
    /**
     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
     * This method is for cobrand login and will get the api session token to access other apis.
     *
     * @return Jwt token
     * @throws Exception
     */
    @RequestMapping(value="/test")
	public String test() {
		return "hello test";
	}
	
    @RequestMapping(value = "/api/rfx/business-unit", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }

	@RequestMapping(value = "/api/rfx/business-unit/create", method = RequestMethod.POST)
    public ResponseEntity<?> createBusinessUnit(@RequestBody BusinessUnitCreate businessUnitCreate) throws Exception {
       if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (businessUnitCreate.getName() != Constants.EMPTY &&  businessUnitCreate.getType()!=Constants.EMPTY  && businessUnitCreate.getCode() != Constants.EMPTY && businessUnitCreate.getDescription() != Constants.EMPTY && businessUnitCreate.getDescription() != Constants.EMPTY) {
                if (!rfxAdminService.nameExistInBusinessUnit(businessUnitCreate.getName())) {
                    if(rfxAdminService.businessUnitCodeExist(businessUnitCreate.getCode()) != null) {
                        return getErrorResponseEntity(Constants.CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    } else {
                    	logger.debug("Creating Business Unit..");
                        rfxAdminService.createBusinessUnit(businessUnitCreate);
                        
                        return getSuccessResponseEntity(Constants.CREATE_SUCCESS);
                    }
                } else {
                    return getErrorResponseEntity(Constants.BU_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }

	@RequestMapping(value = "/api/rfx/business-unit/get/business/types", method = RequestMethod.GET)
    public ResponseEntity<?> getBusinessUnitTypes() throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.debug("Getting Business Unit types..");
        	return ResponseEntity.ok(rfxAdminService.getBusinessUnitTypesList());
        }else {
        	return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            
        }
	}
	
	
	@RequestMapping(value = "/api/rfx/business-unit/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editBusinessUnit(@RequestBody BusinessUnitCreate businessUnitCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (businessUnitCreate.getBusiness_id() != Constants.EMPTY && businessUnitCreate.getName() != Constants.EMPTY && businessUnitCreate.getCode() != Constants.EMPTY && businessUnitCreate.getDescription() != Constants.EMPTY) {
               
            	if (rfxAdminService.businessIdExist(businessUnitCreate.getBusiness_id())) {
            		
                    	if ((!rfxAdminService.editNameExist( businessUnitCreate.getName())) || rfxAdminService.editNameExistByBusinessIdAndName(businessUnitCreate.getBusiness_id(),businessUnitCreate.getName())) {
                        String bid = rfxAdminService.businessUnitCodeExist(businessUnitCreate.getCode());
                        if( (bid == null) || (bid != null && bid.equalsIgnoreCase(businessUnitCreate.getBusiness_id()))) {
                        	logger.debug("updating Business Unit..");
                        	if (rfxAdminService.updateBusinessUnit(businessUnitCreate) != 0)
                                return getSuccessResponseEntity(Constants.UPDATED_SUCCESS);
                            else
                                return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        } else {
                            return getErrorResponseEntity(Constants.CODE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        }
                   
                    } else {
                        return getErrorResponseEntity(Constants.BU_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    }

                } else {
                    return getErrorResponseEntity(  Constants.INVALID_BUSINESS_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	@RequestMapping(value = "/api/rfx/business-unit/type/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatusBusinessUnitType(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "businessUnitTypeId", defaultValue = "") String businessUnitTypeId) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (businessUnitTypeId != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.businessUnitTypeIdExist(businessUnitTypeId)) {
                	logger.debug("Changing BusinessUnit status..");
                    return getSuccessResponseEntity(rfxAdminService.setBusinessUnitTypeStatus(businessUnitTypeId, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_BUSINESSUNIT_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }
    }
	
	
	@RequestMapping(value = "/api/rfx/business-unit/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatusBusinessUnit(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "business_id", defaultValue = "") String businessUnitId) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (businessUnitId != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.businessIdExist(businessUnitId)) {
                	logger.debug("Changing BusinessUnit status..");
                    return getSuccessResponseEntity(rfxAdminService.setRfxBusinessStatus(businessUnitId, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_BUSINESS_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }
    }
	
	
    @RequestMapping(value = "/api/rfx/business-unit/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchBusinessUnit(@RequestBody BusinessUnitSearch businessUnitSearch) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.debug("Searching BusinessUnit ..");
            return ResponseEntity.ok(rfxAdminService.businessSearch(businessUnitSearch));
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "/api/rfx/business-unit/list", method = RequestMethod.GET)
    public ResponseEntity<?> getBusinessUnitList() throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.debug("Searching BusinessUnit ..");
            return ResponseEntity.ok(rfxAdminService.getBusinessUnitList());
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
