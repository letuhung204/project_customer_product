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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rfx.api.model.BusinessUnitSearch;
import com.rfx.api.model.CompanyDetailsCreate;
import com.rfx.api.model.CompanyDetailsSearch;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class CompanyDetailsControllerTest extends AbstractTest{

	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	public void addCompanyDeatils_EmptyParams() throws Exception
	{
		
		String uri = "/api/rfx/companydetails/add/update";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
    	CompanyDetailsCreate companyDetailsCreate=new CompanyDetailsCreate();	
		companyDetailsCreate.setCompanyDetailsId(0);
		companyDetailsCreate.setName("Company Name 1");
		companyDetailsCreate.setNameDesc("Company Name Desc");
		companyDetailsCreate.setWebsiteUrl("");
		companyDetailsCreate.setWebsiteUrlDesc("WebsiteUrl Desc");
		companyDetailsCreate.setContact("");
		companyDetailsCreate.setContactDesc("Contact Desc");
		companyDetailsCreate.setLegalStruct("LegalStruct");
		companyDetailsCreate.setLegalStructDesc("LegalStruct Desc");
		companyDetailsCreate.setBackground("Background");
		companyDetailsCreate.setBackgroundDesc("Background Desc");
		companyDetailsCreate.setHistory("History");
		companyDetailsCreate.setHistoryDesc("History Desc");
		companyDetailsCreate.setRevenueGrowth("RevenueGrowth");
		companyDetailsCreate.setRevenueGrowthDesc("RevenueGrowth Desc");
		companyDetailsCreate.setKeyStaffProfile("KeyStaffProfile");
		companyDetailsCreate.setKeyStaffProfileDesc("KeyStaffProfile Desc");
		companyDetailsCreate.setHeadQuaterAddress("HeadQuaterAddress");
		companyDetailsCreate.setHeadQuaterAddressDesc("HeadQuaterAddress Desc");
		companyDetailsCreate.setCapabilityReferences("CapabilityReferences");
		companyDetailsCreate.setCapabilityReferencesDesc("CapabilityReferences Desc");
		companyDetailsCreate.setCompanyClients("");
		companyDetailsCreate.setCompanyClientsDesc("");
		String inputJson = mapToJson(companyDetailsCreate);
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
	
	
	   
	    public void changeCompanyDetailsStatus_InvalidCompanyId() throws Exception {
	        String uri = "/api/rfx/companydetails/active?companyDetailsId=0&status= ";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.INVALID_COMPANY_ID,resp.getErroMessage());
	    }
	
	   
	    public void companyDetailSearchList() throws Exception {
	    
		   String uri = "/api/rfx/companydetails/search";
	        CompanyDetailsSearch companyDetailsSearch = new CompanyDetailsSearch();
	        companyDetailsSearch.setPage_no(0);
	        companyDetailsSearch.setLimit(10);
	        companyDetailsSearch.setOrderby("name");
	        companyDetailsSearch.setSortby("ASC"); // Ascending / Descending
	        companyDetailsSearch.setName("");
	        companyDetailsSearch.setWebsiteUrl("");
	        companyDetailsSearch.setContact("");
	        companyDetailsSearch.setLegalStruct("");
	        companyDetailsSearch.setBackground("");
	        companyDetailsSearch.setHistory("");
	        companyDetailsSearch.setRevenueGrowth("");
	        companyDetailsSearch.setKeyStaffProfile("");
	        companyDetailsSearch.setHeadQuaterAddress("");
	        companyDetailsSearch.setCapabilityReferences("");
	        companyDetailsSearch.setCompanyClients("companyClients2 sample");
		   
	        String inputJson = mapToJson(companyDetailsSearch);
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
