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
import com.rfx.api.model.RfxCategoriesCreate;
import com.rfx.api.model.RfxCategoriesSearch;
import com.rfx.api.model.RfxStatusesCreate;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class RfxCategoriesControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void rfxCategories_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-categories/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxCategoriesCreate rfxCategoriesCreate=new RfxCategoriesCreate();
        rfxCategoriesCreate.setRfx_category_id("");
        rfxCategoriesCreate.setName("");
        rfxCategoriesCreate.setDescription("");
        rfxCategoriesCreate.setCode("");
        rfxCategoriesCreate.setEffectivedate("");
        
		String inputJson = mapToJson(rfxCategoriesCreate);
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
	
	
	public void rfxCategoriesCreate_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-categories/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        
        RfxCategoriesCreate rfxCategoriesCreate=new RfxCategoriesCreate();
        
        rfxCategoriesCreate.setName(" New Category 3");
        rfxCategoriesCreate.setDescription("Test Desc");
        rfxCategoriesCreate.setCode("2201");
        rfxCategoriesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxCategoriesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_CATG_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxCategoriesEdit_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-categories/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxCategoriesCreate rfxCategoriesEdit=new RfxCategoriesCreate();
        rfxCategoriesEdit.setRfx_category_id("580aeab3-f6d3-4d67-a1a6-70702fa5b961");
        rfxCategoriesEdit.setName("");
        rfxCategoriesEdit.setDescription("");
        rfxCategoriesEdit.setCode("");
        rfxCategoriesEdit.setEffectivedate("");
        
		String inputJson = mapToJson(rfxCategoriesEdit);
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
    
	
	public void rfxCategoriesEdit_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-categories/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxCategoriesCreate rfxCategoriesEdit=new RfxCategoriesCreate();
        rfxCategoriesEdit.setRfx_category_id("580aeab3-f6d3-4d67-a1a6-70702fa5b961");
        rfxCategoriesEdit.setName(" New Category eew");
        rfxCategoriesEdit.setDescription("Test Desc");
        rfxCategoriesEdit.setCode("2201");
        rfxCategoriesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxCategoriesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_CATG_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxCategoriesEdit_InvalidRfxCategoriesId() throws Exception
	{
		
		String uri = "/api/rfx/rfx-categories/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxCategoriesCreate rfxCategoriesEdit=new RfxCategoriesCreate();
        rfxCategoriesEdit.setRfx_category_id("580aeab3-f6d3-4d67-a1a6-70702f61");
        rfxCategoriesEdit.setName(" New Category eew");
        rfxCategoriesEdit.setDescription("Test Desc");
        rfxCategoriesEdit.setCode("2201");
        rfxCategoriesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxCategoriesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_RFX_CATEGORY_ID,resp.getErroMessage());
	}
	
	
	public void rfxCategoriesEdit_CodeExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-categories/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxCategoriesCreate rfxCategoriesEdit=new RfxCategoriesCreate();
        rfxCategoriesEdit.setRfx_category_id("580aeab3-f6d3-4d67-a1a6-70702fa5b961");
        rfxCategoriesEdit.setName("Statuses name 001");
        rfxCategoriesEdit.setDescription("Test Desc");
        rfxCategoriesEdit.setCode("2020983");
        rfxCategoriesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxCategoriesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_CATEG_CODE_EXIST,resp.getErroMessage());
	}
	
	 
	    public void rfxCategoriesSearchList() throws Exception {
	       String uri = "/api/rfx/rfx-categories/search";
	       RfxCategoriesSearch rfxCategoriesSearch=new RfxCategoriesSearch();
	       rfxCategoriesSearch.setPage_no(0);
	       rfxCategoriesSearch.setLimit(10);
	       rfxCategoriesSearch.setOrderby("name");
	       rfxCategoriesSearch.setSortby("ASC"); // Ascending / Descending
	       rfxCategoriesSearch.setName(" New Category 3");
	       rfxCategoriesSearch.setCode("2020983");
	       rfxCategoriesSearch.setStatus("");
	       rfxCategoriesSearch.setEffectivedate_st("");
		   rfxCategoriesSearch.setEffective_end("");
	        String inputJson = mapToJson(rfxCategoriesSearch);
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

	        int status = mvcResult.getResponse().getStatus();
	        String content = mvcResult.getResponse().getContentAsString();
	        System.out.println("Search Rfx status Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	 
	 
	    public void changeRfxCategoriesStatus_InvalidRfxCategoriesId() throws Exception {
	        String uri = "/api/rfx/rfx-categories/active?rfx_category_id=2a5af7c5-9823-46a9-854468e8a9c&status=N";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_RFX_CATEGORY_ID,resp.getErroMessage());
	    }
}
