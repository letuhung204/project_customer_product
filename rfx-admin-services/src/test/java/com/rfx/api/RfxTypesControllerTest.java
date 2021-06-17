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

import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxTypesCreate;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class RfxTypesControllerTest extends AbstractTest{
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
	
	public void rfxTypesCreate_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-types/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxTypesCreate rfxTypesCreate=new RfxTypesCreate();
        rfxTypesCreate.setRfx_type_id("");
        rfxTypesCreate.setName("");
        rfxTypesCreate.setDescription("");
        rfxTypesCreate.setCode("");
        rfxTypesCreate.setEffectivedate("");
		String inputJson = mapToJson(rfxTypesCreate);
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
	
	
	public void rfxTypesCreate_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-types/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxTypesCreate rfxTypesCreate=new RfxTypesCreate();
        rfxTypesCreate.setRfx_type_id("Rfx Types two");
        rfxTypesCreate.setName("Test name");
        rfxTypesCreate.setDescription("Test Desc");
        rfxTypesCreate.setCode("2201");
        rfxTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_TYPE_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxTypesEdit_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxTypesCreate rfxTypesCreate=new RfxTypesCreate();
        rfxTypesCreate.setRfx_type_id("");
        rfxTypesCreate.setName("");
        rfxTypesCreate.setDescription("");
        rfxTypesCreate.setCode("");
        rfxTypesCreate.setEffectivedate("");
        
		String inputJson = mapToJson(rfxTypesCreate);
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
	
	
	public void rfxTypesEdit_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxTypesCreate rfxTypesCreate=new RfxTypesCreate();
        rfxTypesCreate.setRfx_type_id("0b741130-91cc-49c4-bddf-2941e1305afc");
        rfxTypesCreate.setName("Rfx Types two");
        rfxTypesCreate.setDescription("Test Desc");
        rfxTypesCreate.setCode("2201");
        rfxTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_TYPE_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxTypesEdit_InvalidRfxTypeId() throws Exception
	{
		
		String uri = "/api/rfx/rfx-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxTypesCreate rfxTypesCreate=new RfxTypesCreate();
        rfxTypesCreate.setRfx_type_id("0b741130-91cc-4fc");
        rfxTypesCreate.setName("Rfx Types two");
        rfxTypesCreate.setDescription("Test Desc");
        rfxTypesCreate.setCode("2201");
        rfxTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_RFX_TYPE_ID,resp.getErroMessage());
	}
	
	
	public void rfxTypesEdit_CodeExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxTypesCreate rfxTypesCreate=new RfxTypesCreate();
        rfxTypesCreate.setRfx_type_id("0b741130-91cc-49c4-bddf-2941e1305afc");
        rfxTypesCreate.setName("Rfx Types two 123");
        rfxTypesCreate.setDescription("Test Desc");
        rfxTypesCreate.setCode("2201");
        rfxTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_TYPE_CODE_EXIST,resp.getErroMessage());
	}
	
	 
	    public void rfxTypesSearchList() throws Exception {
	       String uri = "/api/rfx/rfx-types/search";
	       RfxTypesSearch rfxTypesSearch=new RfxTypesSearch();
		   rfxTypesSearch.setPage_no(0);
		   rfxTypesSearch.setLimit(10);
		   rfxTypesSearch.setOrderby("name");
		   rfxTypesSearch.setSortby("ASC"); // Ascending / Descending
		   rfxTypesSearch.setName("Test name");
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
	        System.out.println("Search Rfx Type Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	 
	 
	    public void changeTypesStatus_InvalidRfxTypeId() throws Exception {
	        String uri = "/api/rfx/rfx-types/active?status=N&rfx_type_id=0b741130-91cc-492941e1305afc";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_RFX_TYPE_ID,resp.getErroMessage());
	    }
	

}
