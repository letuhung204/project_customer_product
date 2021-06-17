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

import com.rfx.api.model.BidSourceAndKeywordCreate;
import com.rfx.api.model.BusinessUnitCreate;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.KeywordEntryCreate;
import com.rfx.api.model.KeywordEntrySearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class KeywordEntriesController {
	
	private ErrorResponse errorResponse = null;
	private SuccessResponse successResponse = null;
	
	@Autowired
    private RfxAdminService rfxAdminService;
	
	private static final Logger logger = LoggerFactory.getLogger(KeywordEntriesController.class);
	
    /**
     * RequestMapping annotation maps HTTP requests to handler methods of REST controllers.
     * This method is for cobrand login and will get the api session token to access other apis.
     *
     * @return Jwt token
     * @throws Exception
     */
	
    @RequestMapping(value = "/api/rfx/keyword-entries", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
    
    
    @RequestMapping(value = "/api/rfx/keyword-entries/add/bidsource/rfxCategory", method = RequestMethod.POST)
    public ResponseEntity<?> addbidsourceAndKeywordExclusion(@RequestBody KeywordEntryCreate keywordEntryCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (keywordEntryCreate.getBid_source() != Constants.EMPTY && keywordEntryCreate.getRfx_category_id() != Constants.EMPTY && keywordEntryCreate.getExclusions()!=Constants.EMPTY) {
            	if(rfxAdminService.checkRfxCategoryIdAndExclusionExistForBidSource(keywordEntryCreate.getBid_source(), keywordEntryCreate.getRfx_category_id(),keywordEntryCreate.getExclusions()))
            	{
            		return getErrorResponseEntity(Constants.BIDSOURCE_FOR_KEYWORD_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            	}else{
            		 logger.info("addKeywordEntry..");
            		int inserted=rfxAdminService.createKeywordForBidSource(keywordEntryCreate);
            		if(inserted!=0)
    				{
    					return getSuccessResponseEntity(Constants.BIDSOURCE_AND_KEYWORD_ADDED);
    				}else{
    					return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    				}
            	}
            }else{
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        }else
        {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_KEYWORD_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "/api/rfx/keyword-entries/add", method = RequestMethod.POST)
    public ResponseEntity<?> addKeywordEntry(@RequestBody KeywordEntryCreate keywordEntryCreate) throws Exception {
    	if (rfxAdminService.checkRoleAsAdmin() ) { 
    		if(keywordEntryCreate.getBid_source()!=Constants.EMPTY && keywordEntryCreate.getBusiness_id()!=Constants.EMPTY && keywordEntryCreate.getRfx_category_id()!=Constants.EMPTY && keywordEntryCreate.getExclusions()!=Constants.EMPTY)
    		{
    			if(rfxAdminService.businessIdExist(keywordEntryCreate.getBusiness_id()))
            	{
    			  if(rfxAdminService.checkRfxCategoryIdAndExclusionExistForBidSource(keywordEntryCreate.getBid_source(), keywordEntryCreate.getRfx_category_id(),keywordEntryCreate.getExclusions()))
    			  {
	    			
	            		if(!rfxAdminService.keywordEntryExist(keywordEntryCreate.getBid_source(), keywordEntryCreate.getRfx_category_id(),keywordEntryCreate.getExclusions(),keywordEntryCreate.getBusiness_id()))
	            		{
	            			logger.info("addUsersInKeywordEntry..");
	            			int inserted=rfxAdminService.createKeywordEntry(keywordEntryCreate);
	                		if(inserted!=0)
	        				{
	        					return getSuccessResponseEntity(Constants.CREATE_SUCCESS_KEYWORD_ENTRY);
	        				}else{
	        					return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        				}	
	            		}else{
	            			return getErrorResponseEntity(Constants.KEYWORD_ENTRY_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	            		}
	            	}else{
	            		return getErrorResponseEntity(Constants.BIDSOURCE_FOR_KEYWORD_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        		}
    			}
    			else
    			{
    				return getErrorResponseEntity( Constants.INVALID_BUSINESS_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	    		}
    		}else{
    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    		}
    	}else {
    		 return getErrorResponseEntity(Constants.CONTACT_ADMIN_KEYWORD_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(value = "/api/rfx/keyword-entries/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editUsersInKeywordEntry(@RequestBody KeywordEntryCreate keywordEntryCreate) throws Exception {
    	if (rfxAdminService.checkRoleAsAdmin() ) { 
    		if(keywordEntryCreate.getKeyword_entry_id()!=Constants.EMPTY &&  keywordEntryCreate.getBid_source()!=Constants.EMPTY && keywordEntryCreate.getBusiness_id()!=Constants.EMPTY && keywordEntryCreate.getRfx_category_id()!=Constants.EMPTY  && keywordEntryCreate.getExclusions()!=Constants.EMPTY)
    		{
    			if(rfxAdminService.keywordEntryIdExist(keywordEntryCreate.getKeyword_entry_id()))
    			{
    				if(rfxAdminService.checkRfxCategoryIdAndExclusionExistForBidSource(keywordEntryCreate.getBid_source(), keywordEntryCreate.getRfx_category_id(),keywordEntryCreate.getExclusions()))
        			{
    					if(rfxAdminService.businessIdExist(keywordEntryCreate.getBusiness_id()))
    	            	{
    						if(!rfxAdminService.keywordEntryExist(keywordEntryCreate.getBid_source(), keywordEntryCreate.getRfx_category_id(),keywordEntryCreate.getExclusions(),keywordEntryCreate.getBusiness_id()))
    	            		{
    							logger.info("editUsersInKeywordEntry..");
    							int updated=rfxAdminService.updateKeywordEntry(keywordEntryCreate);
    							if(updated!=0){
    								logger.info("updateKeywordEntry..");
    								return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_KEYWORD_ENTRY);
    							}else{
    								return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    							}

    	            		}else{
    	            			return getErrorResponseEntity(Constants.KEYWORD_ENTRY_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	            		}
    						
    	            	}else{
    	            		return getErrorResponseEntity( Constants.INVALID_BUSINESS_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	            	}
    					
        			}else{
    					return getErrorResponseEntity(Constants.BIDSOURCE_FOR_KEYWORD_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    				}
    				
    			}else{
    				 return getErrorResponseEntity(Constants.INVALID_KEYWORD_ENTRY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    			}
    			
			}else{
    			 return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    		}
    	}else{
    		 return getErrorResponseEntity(Constants.CONTACT_ADMIN_KEYWORD_ENTRY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(value = "/api/rfx/keyword-entries/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchKeywordEntry(@RequestBody KeywordEntrySearch keywordEntrySearch) throws Exception {
    	 if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
    		 logger.info("searchKeywordEntry..");
            return ResponseEntity.ok(rfxAdminService.keywordEntrySearch(keywordEntrySearch));
        } else {
            return getErrorResponseEntity( Constants.CONTACT_ADMIN_KEYWORD_ENTRY,  Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }
    }
    
	@RequestMapping(value = "/api/rfx/keyword-entries/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeKeywordEntryStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "keywordEntryId", defaultValue = "") String keywordEntryId) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (keywordEntryId != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.keywordEntryIdExist(keywordEntryId)) {
                	logger.info("Change keyword Entry Status");
                    return getSuccessResponseEntity(rfxAdminService.setKeywordEntryStatus(keywordEntryId, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_KEYWORD_ENTRY_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_RFX_STATUS, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	  @RequestMapping(value = "/api/rfx/keyword-entries/get/bidSource/categories", method = RequestMethod.GET)
	    public ResponseEntity<?> getBidSourceExclusion() throws Exception {
	    	 if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
	    		 logger.info("Getting Keyword BidSource and Categories..");
	            return ResponseEntity.ok(rfxAdminService.getBidSourceAndRfxCategoriesList());
	        } else {
	            return getErrorResponseEntity( Constants.CONTACT_ADMIN_KEYWORD_ENTRY,  Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
	        }
	    }
	
	
	public ResponseEntity<?> getSuccessResponseEntity(String message) {
	    successResponse = new SuccessResponse();
	    successResponse.setStatus("Success");
	    successResponse.setMessage(message);
	    successResponse.setStatusCode( Constants.SUCCESS_CODE);
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
