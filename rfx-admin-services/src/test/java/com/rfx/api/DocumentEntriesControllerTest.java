package com.rfx.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.rfx.api.utils.Constants;

@AutoConfigureMockMvc 
public class DocumentEntriesControllerTest {

	@Autowired
	private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    
    @Value("${server.port}")
    private String port;
    
    public String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";
    
	void setUp() {
	    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	    		.alwaysDo(MockMvcResultHandlers.print()).build();
	}
    
    
  
    public void testAddDocController() throws Exception {
    	
       FileInputStream fis = new FileInputStream(uploadingDir+"Sample 22-09-20.xlsx");
       MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

       HashMap<String, String> contentTypeParams = new HashMap<String, String>();
       contentTypeParams.put("boundary", "265001916915724");
       MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

       String resp = mvc.perform(post("/api/rfx/word/template/add/update")
    		   .header("Authorization","Bearer "+Constants.JWT_TOKEN)
    		   .requestAttr("uploadFile", multipartFile.getBytes())
               .requestAttr("document_title", "")
               .requestAttr("rfx_document_type_id", "")
               .requestAttr("rfx_type_id", "")
               .requestAttr("rfx_category_id", "")
               .requestAttr("client_name", "").contentType(mediaType))
               .andReturn().getResponse().getContentAsString();
       //.andExpect(status().isOk());
       System.out.println(resp);
   }


    }

