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

import com.rfx.api.model.CompanyDetailsSearch;
import com.rfx.api.model.CompanyMethodologyCreate;
import com.rfx.api.model.CompanyMethodologySearch;
import com.rfx.api.model.CompanyProcessCreate;
import com.rfx.api.model.CompanyProcessSearch;
import com.rfx.api.model.CompanyProductSearch;
import com.rfx.api.model.CompanyProductsCreate;
import com.rfx.api.model.CompanyQualityControlSearch;
import com.rfx.api.model.CompanySecurityCreate;
import com.rfx.api.model.CompanySecuritySearch;
import com.rfx.api.model.CompanyServiceSearch;
import com.rfx.api.model.CompanyServicesCreate;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.ProductFieldTypesResp;
import com.rfx.api.model.ProductModuleCreate;
import com.rfx.api.model.ProductModuleResp;
import com.rfx.api.model.QualityControlCreate;
import com.rfx.api.model.ServiceFieldTypesResp;
import com.rfx.api.model.ServiceModuleCreate;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.utils.Constants;


@AutoConfigureMockMvc
public class CompanyCapabilitiesControllerTest extends AbstractTest{

	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	

    
    public void addOrUpdateProduct() throws Exception {
        String uri = "/api/rfx/company/capabilities/products/add/update";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        CompanyProductsCreate companyProductsCreate=new CompanyProductsCreate();
        companyProductsCreate.setProductId(5);
        companyProductsCreate.setBusinessUnitId("3");
        companyProductsCreate.setBusinessUnitTypeId("20");
        companyProductsCreate.setProductName("Product Name 2");
        companyProductsCreate.setProductFunction("Product Function sample 10");
        companyProductsCreate.setProductDetails("Product Details sample 10");
        companyProductsCreate.setNigpUnpscCodes("221000255");
		String inputJson = mapToJson(companyProductsCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
        System.out.println("Add/Update Resp"+content);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
    
    
    public void changeProductStatus_InvalidProductId() throws Exception {
        String uri = "/api/rfx/company/capabilities/products/active?status=N&productId=60";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.COMPNY_PROD_ID_INVALID,resp.getErroMessage());
    }
    
    
    
    public void productSearchList() throws Exception {
    
	   String uri = "/api/rfx/company/capabilities/products/search";
	    CompanyProductSearch companyProductSearch=new CompanyProductSearch();
	    companyProductSearch.setPage_no(0);
	    companyProductSearch.setLimit(10);
	    companyProductSearch.setOrderby("");
	    companyProductSearch.setSortby("ASC"); // Ascending / Descending
	    companyProductSearch.setProductName("");
	    companyProductSearch.setProductFunction("");
	    companyProductSearch.setProductDetails("");
	    companyProductSearch.setNigpUnpscCodes("");
	    companyProductSearch.setStatus("");
        String inputJson = mapToJson(companyProductSearch);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Search Resp"+content);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
   
    
    

    
    public void addOrUpdateProductModule() throws Exception {
        String uri = "/api/rfx/company/capabilities/products/module/add/update";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ProductModuleCreate productModuleCreate=new ProductModuleCreate();
        productModuleCreate.setProductModuleId(1);
        productModuleCreate.setProductId("10");
        productModuleCreate.setIsNewField(false);
        productModuleCreate.setProductFieldType("New Product Field Type ");
        productModuleCreate.setFieldName("Field Name sample");
        productModuleCreate.setFieldDescription("Field Desc sample");
        productModuleCreate.setFieldData("Field Data sample");
		String inputJson = mapToJson(productModuleCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
        System.out.println("Add/Update Resp"+content);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
    
    public void getProductModuleFieldType() throws Exception {
        String uri = "/api/rfx/company/capabilities/get/products/module/field/type";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Get Product ModuleField Type Resp"+content);
        ProductFieldTypesResp resp = super.mapFromJson(content,ProductFieldTypesResp.class);
        assertEquals(200, resp.getCode());
    }
    
    
    
    public void changeProductFieldStatus_InvalidProductFieldTypeId() throws Exception {
        String uri = "/api/rfx/company/capabilities/product/module/field/active?status=N&productFieldTypeId=100";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.INVALID_PROD_FIELD_ID,resp.getErroMessage());
    }
    
    
    
    
    
    public void changeProductModuleStatus_InvalidProductFieldTypeId() throws Exception {
        String uri = "/api/rfx/company/capabilities/product/module/active?status=N&productModuleId=100";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.COMPNY_MOD_ID_INVALID,resp.getErroMessage());
    }
    
    
    
    
    
    public void getProductModule() throws Exception {
        String uri = "/api/rfx/company/capabilities/get/product/module";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Get Product ModuleField Type Resp"+content);
        ProductModuleResp resp = super.mapFromJson(content,ProductModuleResp.class);
        assertEquals(200, resp.getCode());
    }
    
    
    
    
    public void addOrUpdateService() throws Exception {
    
        String uri = "/api/rfx/company/capabilities/services/add/update";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        CompanyServicesCreate companyServicesCreate=new CompanyServicesCreate();
        
        companyServicesCreate.setServiceId(7);
        companyServicesCreate.setBusinessUnitId("3");
        companyServicesCreate.setBusinessUnitTypeId("10");
        companyServicesCreate.setServiceName("Service Name test data 3");
        companyServicesCreate.setServiceCategory("Service Category test data 3");
        companyServicesCreate.setServiceDescription("Service Description test data");
        companyServicesCreate.setNigpUnpscCodes("001923");
        
		String inputJson = mapToJson(companyServicesCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
        System.out.println("Add/Update Service Resp"+content);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
   
    
    
    public void changeServiceStatus_InvalidServiceId() throws Exception {
        String uri =  "/api/rfx/company/capabilities/services/active?status=Y&serviceId=100";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.COMPNY_SERV_ID_INVALID,resp.getErroMessage());
    }
   
    
   
    
    
    public void addOrUpdateServiceModule() throws Exception {
    
        String uri =  "/api/rfx/company/capabilities/service/module/add/update";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Token :"+Constants.JWT_TOKEN);
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        ServiceModuleCreate serviceModuleCreate=new ServiceModuleCreate();
        
        serviceModuleCreate.setServiceModuleId(2);
        serviceModuleCreate.setServiceId("1");
        serviceModuleCreate.setIsNewField(false);
        serviceModuleCreate.setServiceFieldType("Service Field Type TEST 2");
        serviceModuleCreate.setFieldName("Field Name TEST 2");
        serviceModuleCreate.setFieldDescription("Field Description TEST 2");
        serviceModuleCreate.setFieldData("Field Data TEST 2");
		String inputJson = mapToJson(serviceModuleCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        		.headers(headers)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
        System.out.println("Add/Update Service Module Resp"+content);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
    
    
    		
   
    public void changeServiceModuleStatus_InvalidServiceModuleId() throws Exception {
        String uri =  "/api/rfx/company/capabilities/service/module/active?status=Y&serviceModuleId=100";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.SERV_MOD_ID_INVALID,resp.getErroMessage());
    }
   
   
		   
   
    public void changeServiceFieldStatus_InvalidServiceModuleId() throws Exception {
        String uri =  "/api/rfx/company/capabilities/services/module/field/active?serviceFieldTypeId=100&status=N";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.INVALID_SERV_FIELD_ID,resp.getErroMessage());
    }
   
   
   
   
   
   
   public void getServiceModuleFieldType() throws Exception {
       String uri = "/api/rfx/company/capabilities/get/services/module/field/type";
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
               .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
       String content = mvcResult.getResponse().getContentAsString();
       System.out.println("Get Service ModuleField Type Resp"+content);
       ServiceFieldTypesResp resp = super.mapFromJson(content,ServiceFieldTypesResp.class);
       assertEquals(200, resp.getCode());
   }
   
   
   
   public void serviceSearchList() throws Exception {
   
	   String uri = "/api/rfx/company/capabilities/service/search";
	   CompanyServiceSearch companyServiceSearch=new CompanyServiceSearch();
	   companyServiceSearch.setPage_no(0);
	   companyServiceSearch.setLimit(10);
	   companyServiceSearch.setOrderby("");
	   companyServiceSearch.setSortby("DESC"); // Ascending / Descending
	   companyServiceSearch.setBusinessUnitId("3");
	   companyServiceSearch.setBusinessUnitTypeId("10");
	   companyServiceSearch.setServiceName("Service Name test data 3");
	   companyServiceSearch.setServiceCategory("Service Category test data 3");
	   companyServiceSearch.setServiceDescription("");
	   companyServiceSearch.setNigpUnpscCodes("");
	   companyServiceSearch.setStatus("");
       String inputJson = mapToJson(companyServiceSearch);
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
               .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       String content = mvcResult.getResponse().getContentAsString();
       System.out.println("Search Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
  
   
   
   
   public void addOrUpdateProcess() throws Exception {
   
       String uri =  "/api/rfx/company/capabilities/process/add/update";
       HttpHeaders headers = new HttpHeaders();
       System.out.println("Token :"+Constants.JWT_TOKEN);
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       CompanyProcessCreate companyProcessCreate=new CompanyProcessCreate();
       companyProcessCreate.setProcessId(5);
       companyProcessCreate.setBusinessUnitId("10");
       companyProcessCreate.setBusinessUnitTypeId("15");
       companyProcessCreate.setProcessName("Process test data 123");
       companyProcessCreate.setProcessFunction("Process function sample 123");
       companyProcessCreate.setProcessDetails("Process Details test data 123");
       companyProcessCreate.setNigpUnpscCodes("2209200");
	   String inputJson = mapToJson(companyProcessCreate);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
       		.headers(headers)
               .content(inputJson)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andReturn();
       String content = mvcResult.getResponse().getContentAsString();
       SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
       System.out.println("Add/Update process  Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
  
   
   
   public void addOrUpdateMethodology() throws Exception {
   
       String uri =  "/api/rfx/company/capabilities/methodology/add/update";
       HttpHeaders headers = new HttpHeaders();
       System.out.println("Token :"+Constants.JWT_TOKEN);
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       CompanyMethodologyCreate companyMethodologyCreate=new CompanyMethodologyCreate();
       companyMethodologyCreate.setMethodologyId(7);
       companyMethodologyCreate.setBusinessUnitId("150");
       companyMethodologyCreate.setBusinessUnitTypeId("250");
       companyMethodologyCreate.setMethodologyName("Methodology name test 1");
       companyMethodologyCreate.setMethodologyDetails("Methodology Details 1");
       companyMethodologyCreate.setCertifications("Certification test data `");
	   String inputJson = mapToJson(companyMethodologyCreate);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
       		.headers(headers)
               .content(inputJson)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andReturn();
       String content = mvcResult.getResponse().getContentAsString();
       SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
       System.out.println("Add/Update methodology  Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
   
   
   
   public void addOrUpdateSecurity() throws Exception {
   
       String uri =  "/api/rfx/company/capabilities/security/add/update";
       HttpHeaders headers = new HttpHeaders();
       System.out.println("Token :"+Constants.JWT_TOKEN);
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       CompanySecurityCreate companySecurityCreate=new CompanySecurityCreate();
       companySecurityCreate.setSecurityId(3);
       companySecurityCreate.setBusinessUnitId("150");
       companySecurityCreate.setBusinessUnitTypeId("25");
       companySecurityCreate.setSecurityName("Security Name test 1");
       companySecurityCreate.setSecurityDetails("Security Details test 1");
       companySecurityCreate.setCertifications("Certifications test 1");
	   String inputJson = mapToJson(companySecurityCreate);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
       		.headers(headers)
               .content(inputJson)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andReturn();
       String content = mvcResult.getResponse().getContentAsString();
       SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
       System.out.println("Add/Update security  Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
   
   
   
   public void addOrUpdateQualityControl() throws Exception {
   
       String uri =  "/api/rfx/company/capabilities/quality/control/add/update";
       HttpHeaders headers = new HttpHeaders();
       System.out.println("Token :"+Constants.JWT_TOKEN);
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       QualityControlCreate qualityControlCreate=new QualityControlCreate();
       qualityControlCreate.setQualityControlId(3);
       qualityControlCreate.setBusinessUnitId("10");
       qualityControlCreate.setBusinessUnitTypeId("8");
       qualityControlCreate.setQualityControlName("Quality Control Name test 2");
       qualityControlCreate.setQualityControlDetails("QualityControlDetails 2");
       qualityControlCreate.setCertifications("Certifications 2");
	   String inputJson = mapToJson(qualityControlCreate);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
       		.headers(headers)
               .content(inputJson)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andReturn();
       String content = mvcResult.getResponse().getContentAsString();
       SuccessResponse resp = super.mapFromJson(content,SuccessResponse.class);
       System.out.println("Add/Update Quality Control  Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
   
    public void changeSecurityStatus_InvalidSecurityId() throws Exception {
        String uri =  "/api/rfx/company/capabilities/security/active?status=N&securityId=50";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.COMPNY_SECR_ID_INVALID,resp.getErroMessage());
    }  
	   		
   
    public void changeQualityControlStatus_InvalidSQualityControlId() throws Exception {
        String uri =  "/api/rfx/company/capabilities/quality/control/active?status=N&qualityControlId=20";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.COMPNY_QC_ID_INVALID,resp.getErroMessage());
    }
		  	   
	
   
    public void changeMethodologyStatus_InvalidMethodologyId() throws Exception {
        String uri =  "/api/rfx/company/capabilities/methodology/active?status=N&methodologyId=50";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
        assertEquals(Constants.COMPNY_METHLGY_ID_INVALID,resp.getErroMessage());
    }
   

   
   
   public void changeProcessStatus_InvalidProcessId() throws Exception {
       String uri =  "/api/rfx/company/capabilities/process/active?status=Y&processId=100";
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
               .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
       String content = mvcResult.getResponse().getContentAsString();
       ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
       assertEquals(Constants.COMPNY_PROCSS_ID_INVALID,resp.getErroMessage());
   }
   
   
   public void securitySearchList() throws Exception {
   
	   String uri = "/api/rfx/company/capabilities/security/search";
	   CompanySecuritySearch companySecuritySearch=new CompanySecuritySearch();
	   companySecuritySearch.setPage_no(0);
	   companySecuritySearch.setLimit(10);
	   companySecuritySearch.setOrderby("");
	   companySecuritySearch.setSortby("DESC"); // Ascending / Descending
	   companySecuritySearch.setBusinessUnitId("");
	   companySecuritySearch.setBusinessUnitTypeId("");
	   companySecuritySearch.setSecurityName("Company Name sample 2");
	   companySecuritySearch.setSecurityDetails("");
	   companySecuritySearch.setCertifications("");
	   companySecuritySearch.setStatus("Y");
       String inputJson = mapToJson(companySecuritySearch);
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
               .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       String content = mvcResult.getResponse().getContentAsString();
       System.out.println("Search Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
   
   
   
   public void processSearchList() throws Exception {
   
	   String uri = "/api/rfx/company/capabilities/process/search";
	   CompanyProcessSearch companyProcessSearch=new CompanyProcessSearch();
	   companyProcessSearch.setPage_no(0);
	   companyProcessSearch.setLimit(10);
	   companyProcessSearch.setOrderby("");
	   companyProcessSearch.setSortby("DESC"); // Ascending / Descending
	   companyProcessSearch.setBusinessUnitId("");
	   companyProcessSearch.setBusinessUnitTypeId("");
	   companyProcessSearch.setProcessName("");
	   companyProcessSearch.setProcessFunction("");
	   companyProcessSearch.setProcessDetails("");
	   companyProcessSearch.setNigpUnpscCodes("");
	   companyProcessSearch.setStatus("");
       String inputJson = mapToJson(companyProcessSearch);
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
               .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       String content = mvcResult.getResponse().getContentAsString();
       System.out.println("Search Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
   
   
   public void qualityControlSearchList() throws Exception {
   
	   String uri = "/api/rfx/company/capabilities/quality/control/search";
	   CompanyQualityControlSearch companyQualityControlSearch=new CompanyQualityControlSearch();
	   companyQualityControlSearch.setPage_no(0);
	   companyQualityControlSearch.setLimit(10);
	   companyQualityControlSearch.setOrderby("");
	   companyQualityControlSearch.setSortby("ASC"); // Ascending / Descending
	   companyQualityControlSearch.setBusinessUnitId("");
	   companyQualityControlSearch.setBusinessUnitTypeId("");
	   companyQualityControlSearch.setQualityControlName("");
	   companyQualityControlSearch.setQualityControlDetails("");
	   companyQualityControlSearch.setCertifications("");
	   companyQualityControlSearch.setStatus("");
       String inputJson = mapToJson(companyQualityControlSearch);
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
               .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       String content = mvcResult.getResponse().getContentAsString();
       System.out.println("Search Resp"+content);
       assertEquals(200, mvcResult.getResponse().getStatus());
   }
   
   
   
   
   
   public void methodologySearchList() throws Exception {
   
	   String uri = "/api/rfx/company/capabilities/methodology/search";
	   CompanyMethodologySearch companyMethodologySearch=new CompanyMethodologySearch();
	   companyMethodologySearch.setPage_no(0);
	   companyMethodologySearch.setLimit(10);
	   companyMethodologySearch.setOrderby("");
	   companyMethodologySearch.setSortby("ASC"); // Ascending / Descending
	   companyMethodologySearch.setBusinessUnitId("");
	   companyMethodologySearch.setBusinessUnitTypeId("");
	   companyMethodologySearch.setMethodologyName("");
	   companyMethodologySearch.setMethodologyDetails("");
	   companyMethodologySearch.setCertifications("");
	   companyMethodologySearch.setStatus("");
       String inputJson = mapToJson(companyMethodologySearch);
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
