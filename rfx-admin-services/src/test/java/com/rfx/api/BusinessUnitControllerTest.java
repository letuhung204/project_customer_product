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
import com.rfx.api.model.BusinessUnitSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class BusinessUnitControllerTest extends AbstractTest{

	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    

    
   
   @Value("${server.port}")
   private String port;



	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	
    public void addBusinessUnit_CodeExist() throws Exception {
        String uri = "/api/rfx/business-unit/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
        businessUnitCreate.setBusiness_id("");
        businessUnitCreate.setName("Business Unit Tests");
        businessUnitCreate.setIsNewType(false);
        businessUnitCreate.setType("Sub Co- ordinator");
        businessUnitCreate.setDescription("");
        businessUnitCreate.setCode("000186");
        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
		String inputJson = mapToJson(businessUnitCreate);
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
    
	
	
	
    public void addBusinessUnit_EmptyParams() throws Exception {
        String uri = "/api/rfx/business-unit/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
        businessUnitCreate.setBusiness_id("");
        businessUnitCreate.setName("");
        businessUnitCreate.setIsNewType(false);
        businessUnitCreate.setType("Sub Co- ordinator");
        businessUnitCreate.setDescription("");
        businessUnitCreate.setCode("");
        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
		String inputJson = mapToJson(businessUnitCreate);
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
    
    
    
    public void addBusinessUnit_NameExist() throws Exception {
        String uri = "/api/rfx/business-unit/create";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
        businessUnitCreate.setBusiness_id("");
        businessUnitCreate.setName("Business Unit sample 01-10-20");
        businessUnitCreate.setIsNewType(false);
        businessUnitCreate.setType("Sub Co- ordinator");
        businessUnitCreate.setDescription("Business Unit Description");
        businessUnitCreate.setCode("2001");
        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
		String inputJson = mapToJson(businessUnitCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        System.out.print("Content:"+content);
        assertEquals(Constants.BU_NAME_EXIST,resp.getErroMessage());
    }
    
   
	
    public void editBusinessUnit_EmptyParams() throws Exception {
        String uri = "/api/rfx/business-unit/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
        businessUnitCreate.setBusiness_id("");
        businessUnitCreate.setName("");
        businessUnitCreate.setIsNewType(false);
        businessUnitCreate.setType("Sub Co- ordinator");
        businessUnitCreate.setDescription("");
        businessUnitCreate.setCode("");
        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
		String inputJson = mapToJson(businessUnitCreate);
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
    
	
	
    public void editBusinessUnit_UnitIdInvalid() throws Exception {
        String uri = "/api/rfx/business-unit/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
        businessUnitCreate.setBusiness_id("100");
        businessUnitCreate.setName("Business Unit sample 01-10-2020");
        businessUnitCreate.setIsNewType(true);
        businessUnitCreate.setType("Sub Co- ordinator");
        businessUnitCreate.setDescription("Sample Description");
        businessUnitCreate.setCode("000186");
        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
		String inputJson = mapToJson(businessUnitCreate);
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
	
	
    public void editBusinessUnit_CodeExist() throws Exception {
        String uri = "/api/rfx/business-unit/edit";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
        businessUnitCreate.setBusiness_id("ee4e5406-441f-4c1a-bbba-226272aa126a");
        businessUnitCreate.setName("Business Unit sample 01-10-2020");
        businessUnitCreate.setIsNewType(false);
        businessUnitCreate.setType("Sub Co- ordinator");
        businessUnitCreate.setDescription("Sample Description");
        businessUnitCreate.setCode("2020112");
        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
		String inputJson = mapToJson(businessUnitCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("Content:"+content);
        ErrorResponse resp=super.mapFromJson(content,ErrorResponse.class);    
        assertEquals(Constants.CODE_EXIST,resp.getErroMessage());
    }
    
	 
	    public void editBusinessUnit_NameExist() throws Exception {
	        String uri = "/api/rfx/business-unit/create";
	        HttpHeaders headers = new HttpHeaders();
	        System.out.println("Token :"+Constants.JWT_TOKEN);
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        BusinessUnitCreate businessUnitCreate=new BusinessUnitCreate();
	        businessUnitCreate.setBusiness_id("ee4e5406-441f-4c1a-bbba-226272aa126a");
	        businessUnitCreate.setName("Business Unit sample 01-10-20");
	        businessUnitCreate.setIsNewType(false);
	        businessUnitCreate.setType("Sub Co- ordinator");
	        businessUnitCreate.setDescription("Business Unit Description");
	        businessUnitCreate.setCode("2001");
	        businessUnitCreate.setEffectivedate("2020-01-10 11:44:50");
			String inputJson = mapToJson(businessUnitCreate);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	        		.headers(headers)
	                .content(inputJson)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        System.out.print("Content:"+content);
	        assertEquals(Constants.BU_NAME_EXIST,resp.getErroMessage());
	    }
	    
    
    public void getBusinessUnitTypes() throws Exception {
        String uri = "/api/rfx/business-unit/get/business/types";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Search Resp"+content); 
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
    
    public void changeBUnitTypeStatus_InvalidType() throws Exception {
        String uri = "/api/rfx/business-unit/type/active?businessUnitTypeId=0&status= ";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.INVALID_BUSINESSUNIT_TYPE,resp.getErroMessage());
    }
    
    
    
    public void changeBUnitStatus_InvalidUnitId() throws Exception {
        String uri = "/api/rfx/business-unit/active?business_id=ce803173-f73a-4a74-857c-d8dce&status=N";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.INVALID_BUSINESS_ID,resp.getErroMessage());
    }
    
    
    public void businessUnitSearchList() throws Exception {
    
        String uri = "/api/rfx/business-unit/search";
        BusinessUnitSearch businessUnitSearch = new BusinessUnitSearch();
        businessUnitSearch.setPage_no(0);
        businessUnitSearch.setLimit(10);
        businessUnitSearch.setOrderby("name");
        businessUnitSearch.setSortby("ASC"); // Ascending / Descending
        businessUnitSearch.setName("");
        businessUnitSearch.setType("");
        businessUnitSearch.setStatus("");
        businessUnitSearch.setCode("");
        businessUnitSearch.setEffectivedate_st("");
        businessUnitSearch.setEffective_end("");
        String inputJson = mapToJson(businessUnitSearch);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Search Resp"+content);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
    
}
