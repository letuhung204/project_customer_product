package com.rfx.api;

import static org.junit.Assert.assertEquals;

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

import com.rfx.api.controller.RfxStatusesController;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxStatusesCreate;
import com.rfx.api.model.RfxTypesCreate;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class RfxStatusControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void rfxStatusCreate_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-statuses/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxStatusesCreate rfxStatusesCreate=new RfxStatusesCreate();
        rfxStatusesCreate.setRfx_status_id("");
        rfxStatusesCreate.setName("");
        rfxStatusesCreate.setDescription("");
        rfxStatusesCreate.setCode("");
        rfxStatusesCreate.setEffectivedate("");
        
		String inputJson = mapToJson(rfxStatusesCreate);
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
	
	
	public void rfxStatusCreate_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-statuses/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        
        RfxStatusesCreate rfxStatusesCreate=new RfxStatusesCreate();
        
        rfxStatusesCreate.setName("Statuses name 1");
        rfxStatusesCreate.setDescription("Test Desc");
        rfxStatusesCreate.setCode("2201");
        rfxStatusesCreate.setEffectivedate("12-07-20");
        
        
		String inputJson = mapToJson(rfxStatusesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_STATUS_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxStatusEdit_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-statuses/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxStatusesCreate rfxStatusesEdit=new RfxStatusesCreate();
        rfxStatusesEdit.setRfx_status_id("42e60496-ee12-4974-9d9f-3da5f02c1f62");
        rfxStatusesEdit.setName("");
        rfxStatusesEdit.setDescription("");
        rfxStatusesEdit.setCode("");
        rfxStatusesEdit.setEffectivedate("");
        
		String inputJson = mapToJson(rfxStatusesEdit);
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
    
	
	public void rfxStatusEdit_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-statuses/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxStatusesCreate rfxStatusesEdit=new RfxStatusesCreate();
        rfxStatusesEdit.setRfx_status_id("b29db92c-6bb5-488d-87c5-901753a748b6");
        rfxStatusesEdit.setName("Statuses name 1");
        rfxStatusesEdit.setDescription("Test Desc");
        rfxStatusesEdit.setCode("2201");
        rfxStatusesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxStatusesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_STATUS_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxStatusEdit_InvalidRfxStatusId() throws Exception
	{
		
		String uri = "/api/rfx/rfx-statuses/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxStatusesCreate rfxStatusesEdit=new RfxStatusesCreate();
        rfxStatusesEdit.setRfx_status_id("42e60496-ee12-4974-9d9f-3da62");
        rfxStatusesEdit.setName("Statuses name 1");
        rfxStatusesEdit.setDescription("Test Desc");
        rfxStatusesEdit.setCode("2201");
        rfxStatusesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxStatusesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_RFX_STATUS_ID,resp.getErroMessage());
	}
	
	
	public void rfxStatusEdit_CodeExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-statuses/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxStatusesCreate rfxStatusesEdit=new RfxStatusesCreate();
        rfxStatusesEdit.setRfx_status_id("42e60496-ee12-4974-9d9f-3da5f02c1f62");
        rfxStatusesEdit.setName("Statuses name 001");
        rfxStatusesEdit.setDescription("Test Desc");
        rfxStatusesEdit.setCode("2020002");
        rfxStatusesEdit.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxStatusesEdit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_STATUS_CODE_EXIST,resp.getErroMessage());
	}
	
	 
	    public void rfxStatusSearchList() throws Exception {
	       String uri = "/api/rfx/rfx-statuses/search";
	       RfxTypesSearch rfxTypesSearch=new RfxTypesSearch();
		   rfxTypesSearch.setPage_no(0);
		   rfxTypesSearch.setLimit(10);
		   rfxTypesSearch.setOrderby("name");
		   rfxTypesSearch.setSortby("ASC"); // Ascending / Descending
		   rfxTypesSearch.setName("Statuses name sample 8-24-20");
		   rfxTypesSearch.setCode("");
		   rfxTypesSearch.setStatus("N");
		   rfxTypesSearch.setEffectivedate_st("");
		   rfxTypesSearch.setEffective_end("");
	        String inputJson = mapToJson(rfxTypesSearch);
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
