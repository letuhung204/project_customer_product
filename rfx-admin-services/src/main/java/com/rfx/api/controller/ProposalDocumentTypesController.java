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
import com.rfx.api.model.RfxCategoriesCreate;
import com.rfx.api.model.RfxCategoriesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class ProposalDocumentTypesController{
	
	private static final Logger logger = LoggerFactory.getLogger(ProposalDocumentTypesController.class);
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
    
    @RequestMapping(value = "/api/rfx/proposal-document-types/", method = RequestMethod.PUT)
    public ResponseEntity<?> check() throws Exception {
        return ResponseEntity.ok("Rendered");
    }
    
    @RequestMapping(value = "/api/rfx/proposal-document-types/create", method = RequestMethod.POST)
    public ResponseEntity<?> createProposalDocumentType(@RequestBody ProposalDocumentTypesCreate proposalDocumentTypesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin() ) { // If True admin
            if (proposalDocumentTypesCreate.getName() != Constants.EMPTY && proposalDocumentTypesCreate.getCode() != Constants.EMPTY && proposalDocumentTypesCreate.getDescription() != Constants.EMPTY) {
                if (!rfxAdminService.nameExistInProposalDocumentType(proposalDocumentTypesCreate.getName())) {
                    if(rfxAdminService.proposalDocumentTypeCodeExist(proposalDocumentTypesCreate.getCode()) != null) {
                        return getErrorResponseEntity(Constants.PRO_DOC_TYPE_CODE_EXIST,Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    } else {
                    	 logger.info("createProposalDocumentType");
                        rfxAdminService.createProposalDocumentType(proposalDocumentTypesCreate);
                        return getSuccessResponseEntity(Constants.CREATE_SUCCESS_PRO_DOC_TYPE);
                    }
                } else {
                    return getErrorResponseEntity(Constants.PRO_DOC_TYPE_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
    
	@RequestMapping(value = "/api/rfx/proposal-document-types/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editProposalDocumentType(@RequestBody ProposalDocumentTypesCreate proposalDocumentTypesCreate) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	
            if (proposalDocumentTypesCreate.getProposal_document_type_id()!= Constants.EMPTY && proposalDocumentTypesCreate.getName() != Constants.EMPTY && proposalDocumentTypesCreate.getCode() != Constants.EMPTY && proposalDocumentTypesCreate.getDescription() != Constants.EMPTY) {
                if ( rfxAdminService.proposalDocumentTypeIdExist(proposalDocumentTypesCreate.getProposal_document_type_id())) {
                  
                	if (!rfxAdminService.editProposalDocumentTypeNameExist( proposalDocumentTypesCreate.getName())||rfxAdminService.editNameExistByProposalDocumentTypeIdAndName(proposalDocumentTypesCreate.getProposal_document_type_id(),proposalDocumentTypesCreate.getName())  ) {
                        String proposal_document_type_id = rfxAdminService.proposalDocumentTypeCodeExist(proposalDocumentTypesCreate.getCode());
                        if( (proposal_document_type_id == null) || (proposal_document_type_id != null && proposal_document_type_id.equalsIgnoreCase(proposalDocumentTypesCreate.getProposal_document_type_id()))) {
                        	 logger.info("editProposalDocumentType");
                        	if (rfxAdminService.updateProposalDocumentType(proposalDocumentTypesCreate) != 0)
                                return getSuccessResponseEntity(Constants.UPDATED_SUCCESS_PRO_DOC_TYPE);
                            else
                                return getErrorResponseEntity(Constants.SOMETHING_WRONG, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        } else {
                            return getErrorResponseEntity(Constants.PRO_DOC_TYPE_CODE_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return getErrorResponseEntity(Constants.PRO_DOC_TYPE_NAME_EXIST, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                    }

                } else {
                    return getErrorResponseEntity(  Constants.INVALID_PRO_DOC_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	@RequestMapping(value = "/api/rfx/proposal-document-types/active", method = RequestMethod.PUT)
    public ResponseEntity<?> changeProposalDocumentTypeStatus(@RequestParam(value = "status", defaultValue = "") String status,
                                              @RequestParam(value = "proposal_document_type_id", defaultValue = "") String proposal_document_type_id) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
            if (proposal_document_type_id != Constants.EMPTY && status != Constants.EMPTY) {
                if (rfxAdminService.proposalDocumentTypeIdExist(proposal_document_type_id)) {
                	 logger.info("changeProposalDocumentTypeStatus");
                    return getSuccessResponseEntity(rfxAdminService.setProposalDocumentTypeStatus(proposal_document_type_id, status));
                } else {
                    return getErrorResponseEntity(Constants.INVALID_PRO_DOC_TYPE_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
                }
            } else {
                return getErrorResponseEntity(Constants.PARAMS_EMPTY, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
            }
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
        }

    }
	
	
    @RequestMapping(value = "/api/rfx/proposal-document-types/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchProposalDocumentType(@RequestBody ProposalDocumentTypesSearch proposalDocumentTypesSearch) throws Exception {
        if (rfxAdminService.checkRoleAsAdmin()) { // If True admin
        	 logger.info("searchProposalDocumentType");
            return ResponseEntity.ok(rfxAdminService.proposalDocumentTypeSearch(proposalDocumentTypesSearch));
        } else {
            return getErrorResponseEntity(Constants.CONTACT_ADMIN_PRO_DOC_TYPE, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
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
