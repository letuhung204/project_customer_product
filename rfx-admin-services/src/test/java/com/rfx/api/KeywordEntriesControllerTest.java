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

import com.rfx.api.model.BusinessUnitCreate;
import com.rfx.api.model.ContentEntriesSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.KeywordEntryCreate;
import com.rfx.api.model.KeywordEntrySearch;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class KeywordEntriesControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Value("${server.port}")
	private String port;
	
	void setUp() {
	    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
    public void createKeywordForBidSource_EmptryParam() throws Exception {
        String uri = "/api/rfx/keyword-entries/add/bidsource/rfxCategory";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("");
        keywordEntryCreate.setRfx_category_id("");
        keywordEntryCreate.setExclusions("");
		String inputJson = mapToJson(keywordEntryCreate);
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
	
	
    public void createKeywordForBidSource_BidSourceExist() throws Exception {
        String uri = "/api/rfx/keyword-entries/add/bidsource/rfxCategory";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("www.sampleBidSource123.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.BIDSOURCE_FOR_KEYWORD_EXIST,resp.getErroMessage());
    }
    
	
    public void createKeywordEntries_Params() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("www.sampleBidSource123.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id(""); //db56ffd2-90d6-400c-918d-f6522f468b7f
		String inputJson = mapToJson(keywordEntryCreate);
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
	
	
    public void createKeywordEntries_InvalidBidSource() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("www.sampleBidSource1.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-400c-918d-f6522f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.BIDSOURCE_FOR_KEYWORD_INVALID,resp.getErroMessage());
    }
	
	
    public void createKeywordEntries_InvalidBusinessUnit() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("www.sampleBidSource123.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-400c-918d-f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_BUSINESS_ID,resp.getErroMessage());
    }
	
	
    public void createKeywordEntries_BusinessUnitInvalid() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("www.exploregreek.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_BUSINESS_ID,resp.getErroMessage());
    }
	

	
    public void createKeywordEntries_KeywordEntryExist() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/add";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setBid_source("www.starkindustry.org");
        keywordEntryCreate.setRfx_category_id("Aerospace");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-400c-918d-f6522f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.KEYWORD_ENTRY_EXIST,resp.getErroMessage());
    }
	
    public void editKeywordEntries_Params() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setKeyword_entry_id("3e22782f-023d-4f6d-992b-3f88eafead19");
        keywordEntryCreate.setBid_source("www.sampleBidSource123.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id(""); //db56ffd2-90d6-400c-918d-f6522f468b7f
		String inputJson = mapToJson(keywordEntryCreate);
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
	
	
    public void editKeywordEntries_InvalidBidSource() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setKeyword_entry_id("3e22782f-023d-4f6d-992b-3f88eafead19");
        keywordEntryCreate.setBid_source("www.sampleBidSource1.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-400c-918d-f6522f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.BIDSOURCE_FOR_KEYWORD_INVALID,resp.getErroMessage());
    }
	
	
    public void editKeywordEntries_InvalidBusinessUnit() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setKeyword_entry_id("3e22782f-023d-4f6d-992b-3f88eafead19");
        keywordEntryCreate.setBid_source("www.starkindustry.org");
        keywordEntryCreate.setRfx_category_id("Aerospace");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-400c-918d-f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_BUSINESS_ID,resp.getErroMessage());
    }
	
	
    public void editKeywordEntries_BusinessUnitInvalid() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setKeyword_entry_id("3e22782f-023d-4f6d-992b-3f88eafead19");
        keywordEntryCreate.setBid_source("www.exploregreek.org");
        keywordEntryCreate.setRfx_category_id("1");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.BIDSOURCE_FOR_KEYWORD_INVALID,resp.getErroMessage());
    }
	

	
    public void editKeywordEntries_KeywordEntryExist() throws Exception {
		
        String uri = "/api/rfx/keyword-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        KeywordEntryCreate keywordEntryCreate=new KeywordEntryCreate();
        keywordEntryCreate.setKeyword_entry_id("3e22782f-023d-4f6d-992b-3f88eafead19");
        keywordEntryCreate.setBid_source("www.starkindustry.org");
        keywordEntryCreate.setRfx_category_id("Aerospace");
        keywordEntryCreate.setExclusions("Sample Exclusion");
        keywordEntryCreate.setBusiness_id("db56ffd2-90d6-400c-918d-f6522f468b7f"); 
		String inputJson = mapToJson(keywordEntryCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.KEYWORD_ENTRY_EXIST,resp.getErroMessage());
    }
	
	 
	    public void changeKeywordStatus_InvalidKeywordEntryId() throws Exception {
	        String uri = "/api/rfx/keyword-entries/active?status=N&keywordEntryId=0b741130-91cc-492941e1305afc";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_KEYWORD_ENTRY_ID,resp.getErroMessage());
	    }
	
	 
	    public void contentEntrySearchList() throws Exception {
	       String uri = "/api/rfx/keyword-entries/search";
	       KeywordEntrySearch keywordEntrySearch=new KeywordEntrySearch();
	       keywordEntrySearch.setPage_no(0);
	       keywordEntrySearch.setLimit(10);
	       keywordEntrySearch.setOrderby("");
	       keywordEntrySearch.setSortby("ASC"); // Ascending / Descending
	       keywordEntrySearch.setBidSource("");
	       keywordEntrySearch.setRfxCategoryId("");
	       keywordEntrySearch.setExclusions("");
	       keywordEntrySearch.setStatus("");
	       keywordEntrySearch.setBusinessId("");
	       keywordEntrySearch.setEffectivedate_st("");
	       keywordEntrySearch.setEffective_end("");
	        String inputJson = mapToJson(keywordEntrySearch);
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

	        int status = mvcResult.getResponse().getStatus();
	        String content = mvcResult.getResponse().getContentAsString();
	        System.out.println("Search Content Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	
	
	
}
