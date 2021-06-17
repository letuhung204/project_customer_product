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
import com.rfx.api.model.ContentEntriesCreate;
import com.rfx.api.model.ContentEntriesSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class ContentEntriesController{
	
	private static final Logger logger = LoggerFactory.getLogger(ContentEntriesController.class);
	

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
    
    @RequestMapping(value = "/api/rfx/content-entries/", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
    
    @RequestMapping(value = "/api/rfx/content-entries/create", method = RequestMethod.POST)
    public ResponseEntity<?> createContentEntry(@RequestBody ContentEntriesCreate contentEntriesCreate) throws Exception {
    	 if (rfxAdminService.checkRoleAsAdmin() ) {
    		 if(contentEntriesCreate.getRfx_category_id()!=Constants.EMPTY && contentEntriesCreate.getTopic()!=Constants.EMPTY && contentEntriesCreate.getContent()!=Constants.EMPTY && contentEntriesCreate.getKeyword()!=Constants.EMPTY)
    		 {
    			 if(rfxAdminService.rfxCategoryIdExist(contentEntriesCreate.getRfx_category_id()) )
    			 {
    				logger.info("Creating content entry..");
    				int inserted= rfxAdminService.createContentEntry(contentEntriesCreate,contentEntriesCreate.getRfx_category_id());
    				if(inserted!=0)
    				{   logger.info("Content entry created..");
    					return getSuccessResponseEntity(Constants.CREATE_SUCCESS_CONTENT_ENTRY);
    				}else{
    					return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    				}
    			 }else{
    				 return getErrorResponseEntity(Constants.INVALID_RFX_CATEGORY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    			 }
    		 }else{
    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    		 } 
    	 } else{
    		 return getErrorResponseEntity(Constants.CONTACT_ADMIN_CONTENT_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	 }
    }
    
    @RequestMapping(value = "/api/rfx/content-entries/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editContentEntry(@RequestBody ContentEntriesCreate contentEntriesCreate) throws Exception {
    	 if (rfxAdminService.checkRoleAsAdmin() ) {
    		 if(contentEntriesCreate.getContent_entry_id()!=Constants.EMPTY &&  contentEntriesCreate.getRfx_category_id()!=Constants.EMPTY && contentEntriesCreate.getTopic()!=Constants.EMPTY && contentEntriesCreate.getContent()!=Constants.EMPTY && contentEntriesCreate.getKeyword()!=Constants.EMPTY)
    		 {
    			if(rfxAdminService.editContentEntryIdExist(contentEntriesCreate.getContent_entry_id()))
    			{
    				if(rfxAdminService.rfxCategoryIdExist(contentEntriesCreate.getRfx_category_id()) )
	    			 {
    					logger.info("Updating content entry..");
	    				 int updated=rfxAdminService.updateContentEntry(contentEntriesCreate, contentEntriesCreate.getRfx_category_id()) ;
	    				 if(updated!=0){
	    					 logger.info("Updated content entry..");
	    					 return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_CONTENT_ENTRY);
	     				 }else{
	     					return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    				 }
	    				 
	    			 }else{
	    				 return getErrorResponseEntity(Constants.INVALID_RFX_CATEGORY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    			 }
    			}else{
    				return getErrorResponseEntity(Constants.INVALID_CONTENT_ENTRY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    			}	 
    		 }else{
    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    		 } 
    	 }else{
    		 return getErrorResponseEntity(Constants.CONTACT_ADMIN_CONTENT_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST); 
    	 }
    }
    
    @RequestMapping(value = "/api/rfx/content-entries/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchContentEntry(@RequestBody ContentEntriesSearch contentEntriesSearch) throws Exception {
    	 if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
    		 logger.info("Content entry searching..");
             return ResponseEntity.ok(rfxAdminService.contentEntrySearch(contentEntriesSearch));
         } else {
             return getErrorResponseEntity(Constants.CONTACT_ADMIN_CONTENT_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
         }
    }
   
	@RequestMapping(value = "/api/rfx/content-entries/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeKeywordEntryStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "contentEntryId", defaultValue = "") String contentEntryId) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (contentEntryId != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.editContentEntryIdExist(contentEntryId)) {
                	logger.info("Change Content Entry Status");
                    return getSuccessResponseEntity(rfxAdminService.setContentEntryStatus(contentEntryId, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_CONTENT_ENTRY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
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
