package com.rfx.api;

import static org.junit.Assert.assertEquals;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.rfx.api.model.ContentEntriesCreate;
import com.rfx.api.model.ContentEntriesSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxCategoriesSearch;
import com.rfx.api.model.RfxTypesCreate;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class ContentEntriesControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	

	private static final Logger logger = LoggerFactory.getLogger(RfxTypesControllerTest.class);
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void contentEntriesCreate_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/content-entries/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ContentEntriesCreate contentEntries=new ContentEntriesCreate();
        contentEntries.setContent_entry_id("");
        contentEntries.setName("Question Name");
        contentEntries.setKeyword("Keyword");
        contentEntries.setTopic("Topics");
        contentEntries.setContent("Content Sample");
        contentEntries.setEffectivedate("2012-07-20");
        contentEntries.setRfx_category_id("");
		String inputJson = mapToJson(contentEntries);
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
	
	
	public void contentEntriesCreate_InvalidRfxCatId() throws Exception
	{
		
		String uri = "/api/rfx/content-entries/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ContentEntriesCreate contentEntries=new ContentEntriesCreate();
        contentEntries.setContent_entry_id("");
        contentEntries.setName("Question Name");
        contentEntries.setKeyword("Keyword");
        contentEntries.setTopic("Topics");
        contentEntries.setContent("Content Sample");
        contentEntries.setEffectivedate("2012-07-20");
        contentEntries.setRfx_category_id("001");
		String inputJson = mapToJson(contentEntries);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Content entries:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_RFX_CATEGORY_ID,resp.getErroMessage());
	}
	
	
	public void contentEntriesEdit_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/content-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ContentEntriesCreate contentEntries=new ContentEntriesCreate();
        contentEntries.setContent_entry_id("");
        contentEntries.setName("Question Name");
        contentEntries.setKeyword("Keyword");
        contentEntries.setTopic("Topics");
        contentEntries.setContent("Content Sample");
        contentEntries.setEffectivedate("2012-07-20");
        contentEntries.setRfx_category_id("");
		String inputJson = mapToJson(contentEntries);
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
	
	
	public void contentEntriesEdit_InvalidRfxCatId() throws Exception
	{
		
		String uri = "/api/rfx/content-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ContentEntriesCreate contentEntries=new ContentEntriesCreate();
        contentEntries.setContent_entry_id("ef3fdc5d-330a-4bcd-bab2-f41fe9001aa4");
        contentEntries.setName("Question Name");
        contentEntries.setKeyword("Keyword");
        contentEntries.setTopic("Topics");
        contentEntries.setContent("Content Sample");
        contentEntries.setEffectivedate("2012-07-20");
        contentEntries.setRfx_category_id("001");
		String inputJson = mapToJson(contentEntries);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Content entries:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_RFX_CATEGORY_ID,resp.getErroMessage());
	}
	
	
	public void contentEntriesEdit_InvalidRfxContentEntryId() throws Exception
	{
		
		String uri = "/api/rfx/content-entries/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ContentEntriesCreate contentEntries=new ContentEntriesCreate();
        contentEntries.setContent_entry_id("001");
        contentEntries.setName("Question Name");
        contentEntries.setKeyword("Keyword");
        contentEntries.setTopic("Topics");
        contentEntries.setContent("Content Sample");
        contentEntries.setEffectivedate("2012-07-20");
        contentEntries.setRfx_category_id("73a475de-1120-4b7c-81dc-e812a3b1d9c8");
		String inputJson = mapToJson(contentEntries);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Content entries:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_CONTENT_ENTRY_ID,resp.getErroMessage());
	}
	
	 
	    public void changeContentEntryStatus_InvalidContentEntryId() throws Exception {
	        String uri = "/api/rfx/content-entries/active?contentEntryId=2a5af7c5-9823-46a9-854468e8a9c&status=N";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_CONTENT_ENTRY_ID,resp.getErroMessage());
	    }
	 
	 
	    public void contentEntrySearchList() throws Exception {
	       String uri = "/api/rfx/content-entries/search";
	       ContentEntriesSearch contentEntriesSearch=new ContentEntriesSearch();
	       contentEntriesSearch.setPage_no(0);
	       contentEntriesSearch.setLimit(10);
	       contentEntriesSearch.setOrderby("name");
	       contentEntriesSearch.setSortby("ASC"); // Ascending / Descending
	       contentEntriesSearch.setName("");
	       contentEntriesSearch.setKeyword("");
	       contentEntriesSearch.setTopic("");
	       contentEntriesSearch.setStatus("Y");
	       contentEntriesSearch.setEffectivedate_st("");
	       contentEntriesSearch.setEffective_end("");
	        String inputJson = mapToJson(contentEntriesSearch);
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
