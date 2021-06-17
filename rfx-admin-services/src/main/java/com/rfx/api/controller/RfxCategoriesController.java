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

import com.rfx.api.model.BusinessUnitSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxCategoriesCreate;
import com.rfx.api.model.RfxCategoriesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class RfxCategoriesController{
	
	private static final Logger logger = LoggerFactory.getLogger(RfxCategoriesController.class);
    

    private ErrorResponse errorResponse = null;

    private SuccessResponse successResponse = null;
    

    
    @Autowired
    private RfxAdminService rfxAdminService;
    
	
    /**
     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
     * This method is for cobrand login and will get the api session token to access other apis.
     *
     * @return Jwt token
     * @throws Exception
     */
    
	
    @RequestMapping(value = "/api/rfx/rfx-categories/", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
	
	@RequestMapping(value = "/api/rfx/rfx-categories/create", method = RequestMethod.POST)
    public ResponseEntity<?> createRfxCategories(@RequestBody RfxCategoriesCreate rfxCategoriesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (rfxCategoriesCreate.getName() != Constants.EMPTY && rfxCategoriesCreate.getCode() != Constants.EMPTY && rfxCategoriesCreate.getDescription() != Constants.EMPTY) {
                if (!rfxAdminService.nameExistInRfxCategories(rfxCategoriesCreate.getName())) {
                    if(rfxAdminService.rfxCategoryCodeExist(rfxCategoriesCreate.getCode()) != null) {
                        return getErrorResponseEntity(Constants.RFX_CATEG_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    } else {
                    	logger.info("createRfxCategories");
                        rfxAdminService.createRfxCategories(rfxCategoriesCreate);
                        return getSuccessResponseEntity(Constants.CREATE_SUCCESS_RFX_CATEGORY);
                    }
                } else {
                    return getErrorResponseEntity(Constants.RFX_CATG_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_CATEGORY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	

	@RequestMapping(value = "/api/rfx/rfx-categories/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editRfxCategories(@RequestBody RfxCategoriesCreate rfxCategoriesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	
            if (rfxCategoriesCreate.getRfx_category_id()!= Constants.EMPTY && rfxCategoriesCreate.getName() != Constants.EMPTY  && rfxCategoriesCreate.getCode() != Constants.EMPTY  && rfxCategoriesCreate.getDescription() != Constants.EMPTY) {
                if (rfxAdminService.rfxCategoryIdExist(rfxCategoriesCreate.getRfx_category_id())) {
                  
                	if (!rfxAdminService.editRfxCategoryNameExist(rfxCategoriesCreate.getName()) || rfxAdminService.editNameExistByRfxCategoriesIdAndName(rfxCategoriesCreate.getRfx_category_id(),rfxCategoriesCreate.getName()) ) {
                        String rfx_catg_id = rfxAdminService.rfxCategoryCodeExist(rfxCategoriesCreate.getCode());
                        if( (rfx_catg_id == null) || (rfx_catg_id != null && rfx_catg_id.equalsIgnoreCase(rfxCategoriesCreate.getRfx_category_id()))) {
                        	logger.info("editRfxCategories");
                        	if (rfxAdminService.updateRfxCategory(rfxCategoriesCreate) != 0)
                                return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_RFX_CATEGORY);
                            else
                                return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        } else {
                            return getErrorResponseEntity(Constants.RFX_CATEG_CODE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return getErrorResponseEntity(Constants.RFX_CATG_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    }

                } else {
                    return getErrorResponseEntity(Constants.INVALID_RFX_CATEGORY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_CATEGORY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	

	@RequestMapping(value = "/api/rfx/rfx-categories/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeRfxCategoryStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "rfx_category_id", defaultValue = "") String rfx_category_id) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (rfx_category_id != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.rfxCategoryIdExist(rfx_category_id)) {
                	logger.info("changeRfxCategoryStatus");
                    return getSuccessResponseEntity(rfxAdminService.setRfxCategoryStatus(rfx_category_id, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_RFX_CATEGORY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_CATEGORY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	
    @RequestMapping(value = "/api/rfx/rfx-categories/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchRfxCategory(@RequestBody RfxCategoriesSearch rfxCategoriesSearch) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.info("searchRfxCategory");
            return ResponseEntity.ok(rfxAdminService.rfxCategoriesSearch(rfxCategoriesSearch));
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_CATEGORY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "/api/rfx/rfx-categories/get/list", method = RequestMethod.GET)
    public ResponseEntity<?> getListRfxCategory() throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.info("List Rfx Category");
            return ResponseEntity.ok(rfxAdminService.getRfxCategoriesList());
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_CATEGORY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
