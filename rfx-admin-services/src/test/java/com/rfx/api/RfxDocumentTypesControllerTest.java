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

import com.rfx.api.model.CompanyDetailsCreate;
import com.rfx.api.model.CompanyDetailsSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxDocumentTypesCreate;
import com.rfx.api.model.RfxDocumentTypesSearch;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class RfxDocumentTypesControllerTest extends AbstractTest{
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void rfxDocumentTypesCreate_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-doucument-types/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxDocumentTypesCreate rfxDocumentTypesCreate=new RfxDocumentTypesCreate();
        rfxDocumentTypesCreate.setRfx_document_type_id("");
        rfxDocumentTypesCreate.setName("");
        rfxDocumentTypesCreate.setDescription("");
        rfxDocumentTypesCreate.setCode("");
        rfxDocumentTypesCreate.setEffectivedate("");
        
		String inputJson = mapToJson(rfxDocumentTypesCreate);
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
	
	
	public void rfxDocumentTypesCreate_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-doucument-types/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxDocumentTypesCreate rfxDocumentTypesCreate=new RfxDocumentTypesCreate();
        rfxDocumentTypesCreate.setRfx_document_type_id("");
        rfxDocumentTypesCreate.setName("Test name");
        rfxDocumentTypesCreate.setDescription("Test Desc");
        rfxDocumentTypesCreate.setCode("2201");
        rfxDocumentTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxDocumentTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_DOC_TYPE_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxDocumentTypesEdit_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/rfx-doucument-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxDocumentTypesCreate rfxDocumentTypesCreate=new RfxDocumentTypesCreate();
        rfxDocumentTypesCreate.setRfx_document_type_id("");
        rfxDocumentTypesCreate.setName("");
        rfxDocumentTypesCreate.setDescription("");
        rfxDocumentTypesCreate.setCode("");
        rfxDocumentTypesCreate.setEffectivedate("");
        
		String inputJson = mapToJson(rfxDocumentTypesCreate);
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
	
	
	public void rfxDocumentTypesEdit_NameExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-doucument-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxDocumentTypesCreate rfxDocumentTypesCreate=new RfxDocumentTypesCreate();
        rfxDocumentTypesCreate.setRfx_document_type_id("913f6b2f-1b72-46a3-be14-0f0461d5855f");
        rfxDocumentTypesCreate.setName("Test name");
        rfxDocumentTypesCreate.setDescription("Test Desc");
        rfxDocumentTypesCreate.setCode("2201");
        rfxDocumentTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxDocumentTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_DOC_TYPE_NAME_EXIST,resp.getErroMessage());
	}
	
	
	public void rfxDocumentTypesEdit_Invalid_Doc_type_id() throws Exception
	{
		
		String uri = "/api/rfx/rfx-doucument-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxDocumentTypesCreate rfxDocumentTypesCreate=new RfxDocumentTypesCreate();
        rfxDocumentTypesCreate.setRfx_document_type_id("3");
        rfxDocumentTypesCreate.setName("Test name");
        rfxDocumentTypesCreate.setDescription("Test Desc");
        rfxDocumentTypesCreate.setCode("2201");
        rfxDocumentTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxDocumentTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.INVALID_RFX_DOC_TYPE_ID,resp.getErroMessage());
	}
	
	
	public void rfxDocumentTypesEdit_CodeExist() throws Exception
	{
		
		String uri = "/api/rfx/rfx-doucument-types/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        
        RfxDocumentTypesCreate rfxDocumentTypesCreate=new RfxDocumentTypesCreate();
        rfxDocumentTypesCreate.setRfx_document_type_id("3c383f25-3d14-454b-98f4-0f071d9c2f89");
        rfxDocumentTypesCreate.setName("Test name 123");
        rfxDocumentTypesCreate.setDescription("Test Desc");
        rfxDocumentTypesCreate.setCode("2201");
        rfxDocumentTypesCreate.setEffectivedate("12-07-20");
        
		String inputJson = mapToJson(rfxDocumentTypesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.RFX_DOC_TYPE_CODE_EXIST,resp.getErroMessage());
	}
	
	 
	    public void rfxDocumentTypesSearchList() throws Exception {
	       String uri = "/api/rfx/rfx-doucument-types/search";
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
	        System.out.println("Search Rfx document Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	 
	 
	    public void changeRfxDocumentTypesStatus_InvalidRfxDocTypeId() throws Exception {
	        String uri = "/api/rfx/rfx-doucument-types/active?rfx_document_type_id=888d6f5eee4fa48b&status=N";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_RFX_DOC_TYPE_ID,resp.getErroMessage());
	    }
	
}
