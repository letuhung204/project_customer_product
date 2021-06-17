package com.rfx.api;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import com.rfx.api.controller.BusinessUnitController;
import com.rfx.api.controller.CompanyCapabilitiesController;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.model.WordTemplateSearch;
import com.rfx.api.utils.Constants;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@AutoConfigureMockMvc 
public class WordTemplatecontrollerTest extends AbstractTest{
	private static final Logger logger = LoggerFactory.getLogger(WordTemplatecontrollerTest.class);
	
	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
	
    public String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";
    
    @Value("${server.port}")
    private String port;
    

	void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	 
	  public void wordTemplate_FailedCase() throws Exception {
	     HttpHeaders headers = new HttpHeaders();
	     System.out.println("Token :"+Constants.JWT_TOKEN);
	     headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
		 String fileName="Test Document sample 3_my-avatar.jpg";
		 File f = new File(uploadingDir+ fileName);
         System.out.println(f.isFile()+"  "+f.getName()+f.exists());
         FileInputStream fi1 = new FileInputStream(f);
         MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "Test Document sample 3_my-avatar.jpg", "multipart/form-data", fi1);
         MvcResult result = mvc.perform(MockMvcRequestBuilders.multipart("/api/rfx/word/template/add/update")
        		 .file(mockMultipartFile)
                 .param("templateId", "0")
                 .param("templateName", "My File")
                 .param("description", "Desc")
                
        		 .headers(headers)
        		 .contentType(MediaType.MULTIPART_FORM_DATA) ).andDo(print()).andReturn();
         System.out.println("Response:"+result.getResponse());
         Assert.assertEquals(400, result.getResponse().getStatus());
	  }
	 
	 
	 
	    
	    public void changeWordTemplateStatus_InvalidWrdTempId() throws Exception {
	        String uri = "/api/rfx/word/template/active?status=N&templateId=0";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	        String content = mvcResult.getResponse().getContentAsString();
	        ErrorResponse resp = super.mapFromJson(content,ErrorResponse.class);
	        assertEquals(Constants.WORD_TEMP_ID_INVALID,resp.getErroMessage());
	    }
	    
	    
	    
	    
	    
	    
	    
	    public void wordTemplateSearchList() throws Exception {
	       String uri = "/api/rfx/word/template/search";
	       WordTemplateSearch wordTemplateSearch=new WordTemplateSearch();
	       wordTemplateSearch.setPage_no(0);
	       wordTemplateSearch.setLimit(10);
	       wordTemplateSearch.setOrderby("name");
	       wordTemplateSearch.setSortby("ASC"); // Ascending / Descending
	       wordTemplateSearch.setTemplateName("");
	       wordTemplateSearch.setDescription("");
	       wordTemplateSearch.setStatus("");
	        String inputJson = mapToJson(wordTemplateSearch);
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization","Bearer "+Constants.JWT_TOKEN);
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

	        int status = mvcResult.getResponse().getStatus();
	        String content = mvcResult.getResponse().getContentAsString();
	        System.out.println("Word Template Search Resp"+content);
	        logger.debug("Word Template Search Resp"+content);
	        assertEquals(200, mvcResult.getResponse().getStatus());
	    }
	
	
}
