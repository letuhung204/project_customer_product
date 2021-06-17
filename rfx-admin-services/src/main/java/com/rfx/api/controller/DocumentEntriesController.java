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
import org.springframework.web.multipart.MultipartFile;

import com.rfx.api.model.DocumentEntrySearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.KeywordEntrySearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class DocumentEntriesController {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentEntriesController.class);
	
	@Autowired
    private RfxAdminService rfxAdminService;
	
   
	
	private ErrorResponse errorResponse = null;
	private SuccessResponse successResponse = null;
	
    /**
     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
     * This method is for cobrand login and will get the api session token to access other apis.
     *
     * @return Jwt token
     * @throws Exception
     */
	
	 @RequestMapping(value = "/api/rfx/document-entries/create", method = RequestMethod.POST)
	    public ResponseEntity<?> createDocumentEntry(@RequestParam(value = "rfx_document_type_id", defaultValue = "") String rfx_document_type_id,
	    		@RequestParam(value = "document_title", defaultValue = "") String document_title,
	    		@RequestParam(value = "rfx_type_id", defaultValue = "") String rfx_type_id,
	    		@RequestParam(value = "rfx_category_id", defaultValue = "") String rfx_category_id,
	    		@RequestParam(value = "client_name", defaultValue = "") String client_name,
	    		@RequestParam(value = "uploadFile") MultipartFile uploadFile) throws Exception {
	    	 if (rfxAdminService.checkRoleAsAdmin() ) {
	    		 if(Constants.isNullOrEmpty(document_title)&& Constants.isNullOrEmpty(rfx_type_id) && Constants.isNullOrEmpty(client_name)&& Constants.isNullOrEmpty(rfx_category_id) &&  uploadFile!=null )
	    		 {
	    			 
	    				if(!rfxAdminService.documentTitleExist(document_title))
	    				{
	    				  if(rfxAdminService.rfxTypeIdExist(rfx_type_id))
	    				  {
			    			 if(rfxAdminService.rfxCategoryIdExist(rfx_category_id) )
			    			 {
			    				 logger.info("Saving Document entry..");
			                     String saved = rfxAdminService.saveDocumentEntry(rfx_document_type_id, document_title, rfx_type_id, rfx_category_id, client_name, uploadFile);
			    				 if(Constants.isNullOrEmpty(saved))
			    				 {
			    					 logger.info("Document entry saved..");
			    					 return getSuccessResponseEntity(Constants.CREATE_SUCCESS_DOCUMENT_ENTRY);
			    					 
			    				 }else
			    				 {
			    					 return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			    				 }
			    				
			    			 }else{
			    				 return getErrorResponseEntity(Constants.INVALID_RFX_CATEGORY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			    			 }
	    				  }else {
	    					  return getErrorResponseEntity(Constants.INVALID_RFX_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    				  }
	    				}else {
	    					return getErrorResponseEntity(Constants.DOCUMENT_ENTRY_TITLE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    				} 
	    			 
	    		 }else{
	    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    		 } 
	    	 } else{
	    		 return getErrorResponseEntity(Constants.CONTACT_ADMIN_DOCUMENT_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    	 }
	    }
	
	
	 @RequestMapping(value = "/api/rfx/document-entries/edit", method = RequestMethod.POST)
	  public ResponseEntity<?> editDocumentEntry(
			    @RequestParam(value = "document_entry_id", defaultValue = "") String document_entry_id,
			    @RequestParam(value = "rfx_document_type_id", defaultValue = "") String rfx_document_type_id,
	    		@RequestParam(value = "document_title", defaultValue = "") String documentTitle,
	    		@RequestParam(value = "rfx_type_id", defaultValue = "") String rfx_type_id,
	    		@RequestParam(value = "rfx_category_id", defaultValue = "") String rfx_category_id,
	    		@RequestParam(value = "client_name", defaultValue = "") String clientName,
	    		@RequestParam(value = "uploadFile") MultipartFile uploadFile) throws Exception {
		 
		 if (rfxAdminService.checkRoleAsAdmin() ) {
			 if(Constants.isNullOrEmpty(document_entry_id) && Constants.isNullOrEmpty( rfx_document_type_id) && Constants.isNullOrEmpty(documentTitle)&& Constants.isNullOrEmpty(rfx_type_id) && Constants.isNullOrEmpty(clientName)&& Constants.isNullOrEmpty(rfx_category_id) &&  uploadFile!=null )
    		 {
				 if(rfxAdminService.documentEntryIdExist(document_entry_id))
				 {
					 if(rfxAdminService.rfxDocumentTypeIdExist(rfx_document_type_id))
	    			 {
						 if(!rfxAdminService.documentTitleExist(documentTitle) || rfxAdminService.editDocumentTitleExistByDocumentEntryId(document_entry_id, documentTitle))
		    				{
							 
							 if(rfxAdminService.rfxTypeIdExist(rfx_type_id))
							 {
								if(rfxAdminService.rfxCategoryIdExist(rfx_category_id))
								{
									logger.info("Updating document entry");
									if(Constants.isNullOrEmpty(rfxAdminService.updateDocumentEntry(document_entry_id, rfx_document_type_id, documentTitle, rfx_type_id, rfx_category_id, clientName, uploadFile)))
									{
										logger.info("Document entry updated..");
										return getSuccessResponseEntity(Constants.UPDATE_SUCCESS_DOCUMENT_ENTRY);
									}else {
										 return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
									}
									
									
								}else {
									 return getErrorResponseEntity(Constants.INVALID_RFX_CATEGORY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
								}
							 }else {
								 return getErrorResponseEntity(Constants.INVALID_RFX_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
							 }
							 
		    				}else {
		    					return getErrorResponseEntity(Constants.DOCUMENT_ENTRY_TITLE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		    				}
						 
	    			 }else {
	    				 return getErrorResponseEntity(Constants.INVALID_RFX_DOC_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    			 }
					 
				 }else {
					 return getErrorResponseEntity(Constants.INVALID_DOCUMENT_ENTRY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
				 }
    		 }else {
    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    		 }
		 }else {
			 return getErrorResponseEntity(Constants.CONTACT_ADMIN_DOCUMENT_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 
	    @RequestMapping(value = "/api/rfx/document-entries/search", method = RequestMethod.POST)
	    public ResponseEntity<?> searchDocumentEntry(@RequestBody DocumentEntrySearch  documentEntrySearch) throws Exception {
	    	 if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	    		 logger.info("Document entry searching..");
	            return ResponseEntity.ok(rfxAdminService.documentEntrySearch(documentEntrySearch));
	        } else {
	            return getErrorResponseEntity(Constants.CONTACT_ADMIN_KEYWORD_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
