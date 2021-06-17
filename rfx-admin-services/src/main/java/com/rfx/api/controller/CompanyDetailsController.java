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

import com.rfx.api.model.CompanyDetailsCreate;
import com.rfx.api.model.CompanyDetailsSearch;
import com.rfx.api.model.ContentEntriesCreate;
import com.rfx.api.model.ContentEntriesSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class CompanyDetailsController {
	
	 
    private static final Logger logger = LoggerFactory.getLogger(CompanyDetailsController.class);
	
	
	@Autowired
    private RfxAdminService rfxAdminService;
	private ErrorResponse errorResponse = null;
    private SuccessResponse successResponse = null;
   
	
    @RequestMapping(value = "/api/rfx/companydetails", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
    
    @RequestMapping(value = "/api/rfx/companydetails/add/update", method = RequestMethod.POST)
    public ResponseEntity<?> createCompanyDetails(@RequestBody CompanyDetailsCreate companyDetailsCreate) throws Exception {
    	
    	
    	if (rfxAdminService.checkRoleAsAdmin()) 
    	{
    		
    		if(Constants.isNullOrEmpty(companyDetailsCreate.getName() )&& Constants.isNullOrEmpty(companyDetailsCreate.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsCreate.getContact()))
    		{
    			logger.info("Create/Update Company details..");
    			if(rfxAdminService.addCompanyDetails(companyDetailsCreate)==0)
				{
					return getSuccessResponseEntity(Constants.COMPNY_DETAILS_CRT);
				}else 
				{
					return getSuccessResponseEntity(Constants.COMPNY_DETAILS_UPDT);
				}
    		}else
    		{
    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    		}
    	} else {
    		return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_DETAILS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	}
    	
    }
    
	@RequestMapping(value = "/api/rfx/companydetails/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatusCompanyDetails(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "companyDetailsId", defaultValue = "") String companyDetailsId) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (companyDetailsId != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.companyDetailsIdExist(Integer.valueOf(companyDetailsId))) {
                	logger.info("Change company details status ..");
                    return getSuccessResponseEntity(rfxAdminService.setCompanyDetailsStatus(companyDetailsId, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_COMPANY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_DETAILS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }
    }
	
	   @RequestMapping(value = "/api/rfx/companydetails/search", method = RequestMethod.POST)
	    public ResponseEntity<?> searchCompanyDetails(@RequestBody CompanyDetailsSearch companyDetailsSearch) throws Exception {
	    	 if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	    		 logger.info("Company details searching ..");
	             return ResponseEntity.ok(rfxAdminService.companyDetailsSearch(companyDetailsSearch));
	         } else {
	             return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_DETAILS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), httpStatus);
        }
    
    
    
    
}
