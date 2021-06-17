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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.model.WordTemplateSearch;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class WordTemplateController {
	private static final Logger logger = LoggerFactory.getLogger(WordTemplateController.class);
	@Autowired
    private RfxAdminService rfxAdminService;
	
	private ErrorResponse errorResponse = null;
    private SuccessResponse successResponse = null;
    
    @RequestMapping(value = "/api/rfx/word/template/add/update", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<?>  addAttachment(
			@RequestPart(value = "templateId", required = true) String templateId,
			@RequestPart(value = "document", required = true) MultipartFile document,
			@RequestPart(value = "templateName", required = true) String templateName,
			@RequestPart(value = "description", required = true) String description) {
		 if (rfxAdminService.checkRoleAsAdmin()) 
    	 {
		 logger.debug("Adding word template");
		 if(rfxAdminService.addOrUpdateWordTemplate(templateId,templateName, document, description)!=0)
		 {
			 return getSuccessResponseEntity(Constants.WORD_TEMP_ADDED);
		 }else {
			 
			 return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		 }
    	 }else 
    	 { 
	         return getErrorResponseEntity(Constants.CONTACT_ADMIN_WORD_TEMP, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST); 
	      }
	}
    
 
    @RequestMapping(value = "/api/rfx/word/template/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeWordTemplateStatus(@RequestParam(value = "status", defaultValue = "") String status,
            @RequestParam(value = "templateId", defaultValue = "") String templateId) throws Exception {
    	if (rfxAdminService.checkRoleAsAdmin()) 
    	{
    		if(rfxAdminService.templateIdExist(templateId))
    		{
    			logger.info("Change word template status..");
    			return getSuccessResponseEntity(rfxAdminService.wordTemplateStatus(templateId, status));
    		}else {
    			return	getErrorResponseEntity(Constants.WORD_TEMP_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	    }
    	}else {
    		return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }
    }
    
    

    @RequestMapping(value = "/api/rfx/word/template/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchWordTemplate(@RequestBody WordTemplateSearch wordTemplateSearch) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	logger.info("Search Word Template");
            return ResponseEntity.ok(rfxAdminService.wordTemplateSearch(wordTemplateSearch));
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_CATEGORY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	 /**
     * @param errorMessage
     * @param errorCode
     * @param httpStatus
     * @return
     */
    public ResponseEntity<?> getErrorResponseEntity(String errorMessage, int errorCode, HttpStatus httpStatus) {
        errorResponse = new ErrorResponse();
        errorResponse.setErroMessage(errorMessage);
        errorResponse.setErroCode(errorCode);
        return new ResponseEntity<Object>(
                errorResponse, new HttpHeaders(), httpStatus);
    }
    
    /**
     * @param message
     * @return
     */
    public ResponseEntity<?> getSuccessResponseEntity(String message) {
        successResponse = new SuccessResponse();
        successResponse.setStatus("Success");
        successResponse.setMessage(message);
        successResponse.setStatusCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok(successResponse);
    }
    
	
	
}
