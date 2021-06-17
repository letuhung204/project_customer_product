package com.rfx.api;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.ProposalDocumentTypesCreate;
import com.rfx.api.model.RfxDocumentTypesCreate;
import com.rfx.api.model.RfxDocumentTypesSearch;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class ProposalDocumentTypeControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void createProposalDocumentType_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/proposal-document-types/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalDocumentTypesCreate proposalDocumentTypesCreate=new ProposalDocumentTypesCreate();
        proposalDocumentTypesCreate.setProposal_document_type_id("");
        proposalDocumentTypesCreate.setName("");
        proposalDocumentTypesCreate.setDescription("");
        proposalDocumentTypesCreate.setCode("");
        proposalDocumentTypesCreate.setEffectivedate("");
        
		String inputJson = mapToJson(proposalDocumentTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PARAMS_EMPTY,resp.getErroMessage());
	}
	
	
	public void createProposalDocumentType_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/proposal-document-types/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalDocumentTypesCreate proposalDocumentTypesCreate=new ProposalDocumentTypesCreate();
        proposalDocumentTypesCreate.setProposal_document_type_id("01a9ff0a-9b88-41d6-bdeb-c03b68f7f877");
        proposalDocumentTypesCreate.setName("Proposal document type 123");
        proposalDocumentTypesCreate.setDescription("Test Desc");
        proposalDocumentTypesCreate.setCode("2201");
        proposalDocumentTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalDocumentTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PRO_DOC_TYPE_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void proposalDocumentTypeEdit_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/proposal-document-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalDocumentTypesCreate proposalDocumentTypesEdit=new ProposalDocumentTypesCreate();
        proposalDocumentTypesEdit.setProposal_document_type_id("01a9ff0a-9b88-41d6-bdeb-c03b68f7f877");
        proposalDocumentTypesEdit.setName("");
        proposalDocumentTypesEdit.setDescription("");
        proposalDocumentTypesEdit.setCode("2201");
        proposalDocumentTypesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalDocumentTypesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PARAMS_EMPTY,resp.getErroMessage());
	}
	
	
	public void proposalDocumentTypeEdit_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/proposal-document-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalDocumentTypesCreate proposalDocumentTypesEdit=new ProposalDocumentTypesCreate();
        proposalDocumentTypesEdit.setProposal_document_type_id("01a9ff0a-9b88-41d6-bdeb-c03b68f7f877");
        proposalDocumentTypesEdit.setName("Proposal document type 123");
        proposalDocumentTypesEdit.setDescription("Test Desc");
        proposalDocumentTypesEdit.setCode("2201");
        proposalDocumentTypesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalDocumentTypesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PRO_DOC_TYPE_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void proposalDocumentTypeEdit_Invalid_Doc_type_id() throws Exception
	{
		
		String uri = "/api/rfx/proposal-document-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalDocumentTypesCreate proposalDocumentTypesEdit=new ProposalDocumentTypesCreate();
        proposalDocumentTypesEdit.setProposal_document_type_id("3");
        proposalDocumentTypesEdit.setName("Test name");
        proposalDocumentTypesEdit.setDescription("Test Desc");
        proposalDocumentTypesEdit.setCode("2201");
        proposalDocumentTypesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalDocumentTypesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_PRO_DOC_TYPE_ID,resp.getErroMessage());
	}
	
	
	public void proposalDocumentTypeEdit_CodeExist() throws Exception
	{
		
		String uri = "/api/rfx/proposal-document-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalDocumentTypesCreate proposalDocumentTypesEdit=new ProposalDocumentTypesCreate();
        proposalDocumentTypesEdit.setProposal_document_type_id("01a9ff0a-9b88-41d6-bdeb-c03b68f7f877");
        proposalDocumentTypesEdit.setName("Test name 123");
        proposalDocumentTypesEdit.setDescription("Test Desc");
        proposalDocumentTypesEdit.setCode("202001");
        proposalDocumentTypesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalDocumentTypesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PRO_DOC_TYPE_CODE_EXIST,resp.getErroMessage());
	}
	
	 
	    public void proposalDocumentTypeSearchList() throws Exception {
	       String uri = "/api/rfx/proposal-document-types/search";
		   RfxDocumentTypesSearch rfxDocumentTypesSearch=new RfxDocumentTypesSearch();
		   rfxDocumentTypesSearch.setPage_no(0);
		   rfxDocumentTypesSearch.setLimit(10);
		   rfxDocumentTypesSearch.setOrderby("name");
		   rfxDocumentTypesSearch.setSortby("ASC"); // Ascending / Descending
		   rfxDocumentTypesSearch.setName("Document Type name 3");
		   rfxDocumentTypesSearch.setCode("");
		   rfxDocumentTypesSearch.setStatus("");
		   rfxDocumentTypesSearch.setEffectivedate_st("");
		   rfxDocumentTypesSearch.setEffective_end("");
	        String inputJson = mapToJson(rfxDocumentTypesSearch);
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

	        int status = mvcResult.getResponse().getStatus();
	        String content = mvcResult.getResponse().getContentAsString();
	        System.out.println("Search Proposal Document Type Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	 
	 
	    public void changeProposalDocumentTypeStatus_InvalidProDocTypeId() throws Exception {
	        String uri = "/api/rfx/proposal-document-types/active?proposal_document_type_id=01a9ff0a-41d6-bdeb-c03b68f7f877&status=N";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_PRO_DOC_TYPE_ID,resp.getErroMessage());
	    }
}
