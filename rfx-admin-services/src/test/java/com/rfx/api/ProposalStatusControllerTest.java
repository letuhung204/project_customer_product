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
import com.rfx.api.model.ProposalStatusCreate;
import com.rfx.api.model.ProposalStatusSearch;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class ProposalStatusControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void proposalStatusCreate_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/proposal-status/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalStatusCreate proposalStatusCreate=new ProposalStatusCreate();
        proposalStatusCreate.setName("");
        proposalStatusCreate.setDescription("");
        proposalStatusCreate.setCode("");
        proposalStatusCreate.setEffectivedate("");
        
		String inputJson = mapToJson(proposalStatusCreate);
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
	
	
	public void proposalStatusCreate_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/proposal-status/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalStatusCreate proposalStatusCreate=new ProposalStatusCreate();
        proposalStatusCreate.setProposalStatusId("1281d999-d220-4fd2-9d0f-d0bd6ac67243");
        proposalStatusCreate.setName("Proposal Status test 2");
        proposalStatusCreate.setDescription("Test Desc");
        proposalStatusCreate.setCode("10100921");
        proposalStatusCreate.setEffectivedate("12-07-20");
        
        
		String inputJson = mapToJson(proposalStatusCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PRO_STAT_NAME_EXIST,resp.getErroMessage());
	}
	
	
	
	
	public void rfxProposalStatEdit_InvalidProStatusId() throws Exception
	{
		
		String uri = "/api/rfx/proposal-status/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalStatusCreate proposalStatusCreate=new ProposalStatusCreate();
        proposalStatusCreate.setProposalStatusId("d91214b3-c207-67506e16007");
        proposalStatusCreate.setName("Proposal Status test 001");
        proposalStatusCreate.setDescription("Test Desc");
        proposalStatusCreate.setCode("220100");
        proposalStatusCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalStatusCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_PRO_STAT_ID,resp.getErroMessage());
	}
	
	
	public void rfxProposalStatEdit_CodeExist() throws Exception
	{
		
		String uri = "/api/rfx/proposal-status/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        ProposalStatusCreate proposalStatusCreate=new ProposalStatusCreate();
        proposalStatusCreate.setProposalStatusId("ab5f53bf-79a8-4771-bcea-0fb5ed1b13eb");
        proposalStatusCreate.setName("Proposal Status test 2");
        proposalStatusCreate.setDescription("Test Desc");
        proposalStatusCreate.setCode("10100921");
        proposalStatusCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(proposalStatusCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.PRO_STAT_CODE_EXIST,resp.getErroMessage());
	}
	
	 
	    public void rfxStatusSearchList() throws Exception {
	       String uri = "/api/rfx/rfx-statuses/search";
	       ProposalStatusSearch proposalStatusSearch=new ProposalStatusSearch();
	       proposalStatusSearch.setPage_no(0);
	       proposalStatusSearch.setLimit(10);
	       proposalStatusSearch.setOrderby("name");
	       proposalStatusSearch.setSortby("ASC"); // Ascending / Descending
	       proposalStatusSearch.setName("Proposal Status test 2");
	       proposalStatusSearch.setCode("");
	       proposalStatusSearch.setStatus("Y");
	       proposalStatusSearch.setEffectivedate_st("");
	       proposalStatusSearch.setEffective_end("");
	        String inputJson = mapToJson(proposalStatusSearch);
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

	        int status = mvcResult.getResponse().getStatus();
	        String content = mvcResult.getResponse().getContentAsString();
	        System.out.println("Search Rfx status Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	 
	 
	    public void changeRfxStatusStatus_InvalidRfxStatusId() throws Exception {
	        String uri = "/api/rfx/rfx-statuses/active?rfx_status_id=2a5af7c5-9823-46a9-854468e8a9c&status=N";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_RFX_STATUS_ID,resp.getErroMessage());
	    }
}
