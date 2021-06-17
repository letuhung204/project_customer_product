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

import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.ProposalDocumentTypesCreate;
import com.rfx.api.model.ProposalDocumentTypesSearch;
import com.rfx.api.model.ProposalStatusCreate;
import com.rfx.api.model.ProposalStatusSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class ProposalStatusController {
	private static final Logger logger = LoggerFactory.getLogger(ProposalDocumentTypesController.class);
    private ErrorResponse errorResponse = null;
    private SuccessResponse successResponse = null;
    @Autowired
    private RfxAdminService rfxAdminService;
    
    @RequestMapping(value = "/api/rfx/proposal-status/add", method = RequestMethod.POST)
    public ResponseEntity<?> createProposalStatus(@RequestBody ProposalStatusCreate proposalStatusCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (proposalStatusCreate.getName() != Constants.EMPTY && proposalStatusCreate.getCode() != Constants.EMPTY && proposalStatusCreate.getDescription() != Constants.EMPTY) {
                
            	 if (!rfxAdminService.nameExistInProposalStatus(proposalStatusCreate.getName()) ) {
                   
            		 String proposalStatusId = rfxAdminService.proposalStatusCodeExist(proposalStatusCreate.getCode()) ;
            		 
            		 if( proposalStatusId == null ) {
                        
            			 logger.info("Create Proposal Status");
                         return getSuccessResponseEntity(rfxAdminService.createProposalStatus(proposalStatusCreate));
            			   } else {
            				   return getErrorResponseEntity(Constants.PRO_STAT_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            	    }
            		 
            		 
                } else {
                    return getErrorResponseEntity(Constants.PRO_STAT_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_STAT, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
    
    
    
    @RequestMapping(value = "/api/rfx/proposal-status/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editProposalStatus(@RequestBody ProposalStatusCreate proposalStatusCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (proposalStatusCreate.getName() != Constants.EMPTY && proposalStatusCreate.getCode() != Constants.EMPTY && proposalStatusCreate.getDescription() != Constants.EMPTY) {
                
            	if( rfxAdminService.proposalStatusIdExist(proposalStatusCreate.getProposalStatusId()) )
            	{
            	 if (!rfxAdminService.nameExistInProposalStatus(proposalStatusCreate.getName()) || rfxAdminService.editNameExistByProposalStatusIdAndName(proposalStatusCreate.getProposalStatusId(),proposalStatusCreate.getName())) {
                   
            		 String proposalStatusId = rfxAdminService.proposalStatusCodeExist(proposalStatusCreate.getCode()) ;
            		 
            		 if( (proposalStatusId == null) || (proposalStatusId != null && proposalStatusId.equalsIgnoreCase(proposalStatusCreate.getProposalStatusId()))) {
                        
            			 logger.info("Update Proposal Status");
                         return getSuccessResponseEntity(rfxAdminService.createProposalStatus(proposalStatusCreate));
            			   } else {
            				   return getErrorResponseEntity(Constants.PRO_STAT_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            	    }
            		 
                  }else {
                    return getErrorResponseEntity(Constants.PRO_STAT_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                   }
            	}else {
            		 return getErrorResponseEntity(Constants.INVALID_PRO_STAT_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            	 
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_STAT, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	@RequestMapping(value = "/api/rfx/proposal-status/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatusProposalStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "proposal_status_id", defaultValue = "") String proposalStatusId) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (proposalStatusId != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.proposalStatusIdExist(proposalStatusId)) {
                	 logger.info("change Proposal Status status");
                    return getSuccessResponseEntity(rfxAdminService.setProposalStatus(proposalStatusId, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_PRO_STAT_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_STAT, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	@RequestMapping(value = "/api/rfx/proposal-status/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchProposalStatus(@RequestBody ProposalStatusSearch proposalStatusSearch) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	 logger.info("searchProposalStatus");
            return ResponseEntity.ok(rfxAdminService.proposalStatusSearch(proposalStatusSearch));
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_STAT, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
