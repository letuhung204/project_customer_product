package com.rfx.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rfx.api.model.CompanyDetailsCreate;
import com.rfx.api.model.CompanyMethodologyCreate;
import com.rfx.api.model.CompanyMethodologySearch;
import com.rfx.api.model.CompanyProcessCreate;
import com.rfx.api.model.CompanyProcessSearch;
import com.rfx.api.model.ProductFieldTypesResp;
import com.rfx.api.model.ProductModuleCreate;
import com.rfx.api.model.QualityControlCreate;
import com.rfx.api.model.ServiceFieldTypesResp;
import com.rfx.api.model.ServiceModuleCreate;
import com.rfx.api.model.CompanyProductSearch;
import com.rfx.api.model.CompanyProductsCreate;
import com.rfx.api.model.CompanyQualityControlSearch;
import com.rfx.api.model.CompanySecurityCreate;
import com.rfx.api.model.CompanySecuritySearch;
import com.rfx.api.model.CompanyServiceSearch;
import com.rfx.api.model.CompanyServicesCreate;
import com.rfx.api.model.ErrorResponse;
import com.rfx.api.model.SuccessResponse;
import com.rfx.api.service.RfxAdminService;
import com.rfx.api.utils.Constants;

@CrossOrigin(origins = "*")
@RestController
public class CompanyCapabilitiesController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyCapabilitiesController.class);

	@Autowired
	private RfxAdminService rfxAdminService;
	private ErrorResponse errorResponse = null;
	private SuccessResponse successResponse = null;


	//product add/update
	@RequestMapping(value = "/api/rfx/company/capabilities/products/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createCompanyProducts(@RequestBody CompanyProductsCreate companyProductsCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{       logger.info("Create/Update company products..");
			if(rfxAdminService.addCompanyProducts(companyProductsCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_PROD_ADDED);
			}else {
				return	getSuccessResponseEntity(Constants.COMPNY_PROD_UPTD);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}
	//product delete
	@DeleteMapping(value="/api/rfx/campany/capabilities/products/delete")
	public ResponseEntity<?> deleteCompanyProduct(@RequestParam("productId") int productId){
		if (rfxAdminService.checkRoleAsAdmin())
		{       logger.info("Deleted company products..");
			if(rfxAdminService.deleteCompanyProduct(productId)==1)
			{
				return getSuccessResponseEntity(Constants.COMPNY_PROD_DELETED);
			}else{
				return getErrorResponseEntity(Constants.COMPNY_PROD_ID_INVALID,Constants.ERROR_CODE_204,HttpStatus.NO_CONTENT);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//product status
	@RequestMapping(value = "/api/rfx/company/capabilities/products/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeCompanyProductStatus(@RequestParam(value = "status", defaultValue = "") String status,
														@RequestParam(value = "productId", defaultValue = "") String productId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.productIdExist(productId))
			{
				logger.info("Change company product status..");
				return getSuccessResponseEntity(rfxAdminService.companyProductStatus(productId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_PROD_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}
	//product search
	@RequestMapping(value = "/api/rfx/company/capabilities/products/search", method = RequestMethod.GET)
	public ResponseEntity<?> companyProductsSearch(@RequestBody CompanyProductSearch companyProductSearch) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Company products searching..");
			return  ResponseEntity.ok(rfxAdminService.companyProductSearch(companyProductSearch));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);

		}
	}

	//product module
	//add,update product module
	@RequestMapping(value = "/api/rfx/company/capabilities/products/module/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createProductsModule(@RequestBody ProductModuleCreate  productModuleCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{       logger.info("Create/Update products modules..");
			if(rfxAdminService.addProductsModules(productModuleCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_MOD_ADDED);
			}else {
				return	getSuccessResponseEntity(Constants.COMPNY_MOD_UPTD);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}
	//delete product module
	@DeleteMapping(value="/api/rfx/company/capabilities/products/module/deleted")
	public ResponseEntity<?> deleteCompanyProductModule(@RequestParam("productModuleId") int productModuleId){
		if (rfxAdminService.checkRoleAsAdmin())
		{       logger.info("Deleted products module..");
			if(rfxAdminService.productModuleDeleted(productModuleId)==1)
			{
				return getSuccessResponseEntity(Constants.COMPNY_PROD_MODULE_DELETED);
			}else{
				return getErrorResponseEntity(Constants.COMPNY_PROD_ID_INVALID,Constants.ERROR_CODE_204,HttpStatus.NO_CONTENT);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//product module label
	@RequestMapping(value = "/api/rfx/company/capabilities/get/products/module/field/type", method = RequestMethod.GET)
	public  ResponseEntity<?>  getProductFieldType() throws Exception
	{
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Getting Product Field Type..");
			return ResponseEntity.ok(rfxAdminService.getProductFieldTypeList());
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}

	}

	//product label active
	@RequestMapping(value = "/api/rfx/company/capabilities/product/module/field/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeProductFieldStatus(@RequestParam(value = "status", defaultValue = "") String status,
													  @RequestParam(value = "productFieldTypeId", defaultValue = "") String productFieldTypeId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Change product field status..");
			if(rfxAdminService.productFieldTypeExist(productFieldTypeId))
			{
				return getSuccessResponseEntity(rfxAdminService.productFieldStatus(productFieldTypeId, status));
			}else {
				return	getErrorResponseEntity(Constants.INVALID_PROD_FIELD_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//product module active
	@RequestMapping(value = "/api/rfx/company/capabilities/product/module/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeProductModuleStatus(@RequestParam(value = "status", defaultValue = "") String status,
													   @RequestParam(value = "productModuleId", defaultValue = "") String productModuleId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.productModuleIdExist(productModuleId))
			{
				logger.info("Change product module status..");
				return getSuccessResponseEntity(rfxAdminService.productModuleStatus(productModuleId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_MOD_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//product module get
	@RequestMapping(value = "/api/rfx/company/capabilities/get/product/module", method = RequestMethod.GET)
	public  ResponseEntity<?>  getProductModuleList(@RequestParam(value = "productId",required = false)Integer productId) throws Exception
	{
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Getting Product Module..");
			return ResponseEntity.ok(rfxAdminService.getProductModuleList(productId));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}

	}




	//services
	@RequestMapping(value = "/api/rfx/company/capabilities/services/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createCompanyServices(@RequestBody CompanyServicesCreate companyServicesCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Create/Update Company Services..");
			if(rfxAdminService.addCompanyServices(companyServicesCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_SERV_ADDED);
			}else {
				return	getSuccessResponseEntity(Constants.COMPNY_SERV_UPTD);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}

	}


	//service active

	@RequestMapping(value = "/api/rfx/company/capabilities/services/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeCompanyServicesStatus(@RequestParam(value = "status", defaultValue = "") String status,
														 @RequestParam(value = "serviceId", defaultValue = "") String serviceId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.serviceIdExist(serviceId))
			{
				logger.info("Change company services status..");
				return getSuccessResponseEntity(rfxAdminService.companyServiceStatus(serviceId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_SERV_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//service module add/update
	@RequestMapping(value = "/api/rfx/company/capabilities/service/module/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createServiceModule(@RequestBody ServiceModuleCreate  serviceModuleCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{       logger.info("Create/Update service modules..");
			if(rfxAdminService.addServiceModules(serviceModuleCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.SERV_MOD_ADDED);
			}else {
				return	getSuccessResponseEntity(Constants.SERV_MOD_UPTD);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}


	//service module active
	@RequestMapping(value = "/api/rfx/company/capabilities/service/module/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeServiceModuleStatus(@RequestParam(value = "status", defaultValue = "") String status,
													   @RequestParam(value = "serviceModuleId", defaultValue = "") String serviceModuleId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.serviceModuleIdExist(serviceModuleId))
			{
				logger.info("Change service module status..");
				return getSuccessResponseEntity(rfxAdminService.serviceModuleStatus(serviceModuleId, status));
			}else {
				return	getErrorResponseEntity(Constants.SERV_MOD_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//get service module

	@RequestMapping(value = "/api/rfx/company/capabilities/get/service/module", method = RequestMethod.GET)
	public  ResponseEntity<?>  getServiceModuleList() throws Exception
	{
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Getting Service Module..");
			return ResponseEntity.ok(rfxAdminService.getServiceModuleList());
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}

	}






	//service field active
	@RequestMapping(value = "/api/rfx/company/capabilities/services/module/field/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeServiceFieldStatus(@RequestParam(value = "status", defaultValue = "") String status,
													  @RequestParam(value = "serviceFieldTypeId", defaultValue = "") String serviceFieldTypeId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.serviceFieldTypeExist(serviceFieldTypeId))
			{
				logger.info("Change service field status..");
				return getSuccessResponseEntity(rfxAdminService.serviceFieldStatus(serviceFieldTypeId, status));
			}else {
				return	getErrorResponseEntity(Constants.INVALID_SERV_FIELD_ID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//service module field get

	@RequestMapping(value = "/api/rfx/company/capabilities/get/services/module/field/type", method = RequestMethod.GET)
	public ServiceFieldTypesResp getServiceFieldType() throws Exception
	{
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Getting Service Field Type..");
			return rfxAdminService.getServiceFieldTypeList();
		}else {
			ServiceFieldTypesResp serviceFieldTypesResp=new ServiceFieldTypesResp();
			serviceFieldTypesResp.setServiceFieldTypes(null);
			serviceFieldTypesResp.setMessage(Constants.UNABLE_TO_GET_TYPE);
			serviceFieldTypesResp.setCode(Constants.ERROR_CODE_400);
			return  serviceFieldTypesResp;

		}

	}



	//service search

	@RequestMapping(value = "/api/rfx/company/capabilities/service/search", method = RequestMethod.POST)
	public ResponseEntity<?> companyServiceSearch(@RequestBody CompanyServiceSearch CompanyServiceSearch) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Company service searching..");
			return  ResponseEntity.ok(rfxAdminService.companyServiceSearch(CompanyServiceSearch));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);

		}
	}






	//process
	@RequestMapping(value = "/api/rfx/company/capabilities/process/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createCompanyProcess(@RequestBody CompanyProcessCreate CompanyProcessCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Create/Update Company process..");
			if(rfxAdminService.addCompanyProcess(CompanyProcessCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_PROCSS_ADDED);
			}else {
				return	getSuccessResponseEntity(Constants.COMPNY_PROCSS_UPTD);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//Methodology
	@RequestMapping(value = "/api/rfx/company/capabilities/methodology/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createCompanyMethodology(@RequestBody CompanyMethodologyCreate methodologyCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin()){

			logger.info("Create/Update Company methodology..");
			if(rfxAdminService.addCompanyMethodology(methodologyCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_METHLGY_ADDED);
			}else{
				return	getSuccessResponseEntity(Constants.COMPNY_METHLGY_UPTD);
			}
		}else{
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//security add/update
	@RequestMapping(value = "/api/rfx/company/capabilities/security/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createCompanySecurity(@RequestBody CompanySecurityCreate securityCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin()){

			logger.info("Create/Update Company Security...");
			if(rfxAdminService.addCompanySecurity(securityCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_SECR_ADDED);
			}else{
				return	getSuccessResponseEntity(Constants.COMPNY_SECR_UPTD);
			}
		}else{
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}



	//quality control add/update
	@RequestMapping(value = "/api/rfx/company/capabilities/quality/control/add/update", method = RequestMethod.POST)
	public ResponseEntity<?> createQualityControl(@RequestBody QualityControlCreate qualityControlCreate) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin()){

			logger.info("Create/Update Company Quality Control ..");
			if(rfxAdminService.addQualityControl(qualityControlCreate)==0)
			{
				return	getSuccessResponseEntity(Constants.COMPNY_QC_ADDED);
			}else{
				return	getSuccessResponseEntity(Constants.COMPNY_QC_UPTD);
			}
		}else{
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	//quality control active
	@RequestMapping(value = "/api/rfx/company/capabilities/security/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeSecurityStatus(@RequestParam(value = "status", defaultValue = "") String status,
												  @RequestParam(value = "securityId", defaultValue = "") String securityId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.securityIdExist(securityId))
			{
				logger.info("Change Security Status..");
				return getSuccessResponseEntity(rfxAdminService.changeSecurityStatus(securityId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_SECR_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}



	//quality control active
	@RequestMapping(value = "/api/rfx/company/capabilities/quality/control/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeQualityControlStatus(@RequestParam(value = "status", defaultValue = "") String status,
														@RequestParam(value = "qualityControlId", defaultValue = "") String qualityControlId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.qualityControlIdExist(qualityControlId))
			{
				logger.info("Change Quality Control status..");
				return getSuccessResponseEntity(rfxAdminService.qualityControlStatus(qualityControlId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_QC_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}




	@RequestMapping(value = "/api/rfx/company/capabilities/methodology/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeCompanyMethodologyStatus(@RequestParam(value = "status", defaultValue = "") String status,
															@RequestParam(value = "methodologyId", defaultValue = "") String methodologyId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.methodologyIdExist(methodologyId))
			{
				logger.info("Change company methodology status..");
				return getSuccessResponseEntity(rfxAdminService.companyMethodologyStatus(methodologyId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_METHLGY_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}





	@RequestMapping(value = "/api/rfx/company/capabilities/process/active", method = RequestMethod.PUT)
	public ResponseEntity<?> changeCompanyProcessStatus(@RequestParam(value = "status", defaultValue = "") String status,
														@RequestParam(value = "processId", defaultValue = "") String processId) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			if(rfxAdminService.processIdExist(processId))
			{
				logger.info("Change company process status..");
				return getSuccessResponseEntity(rfxAdminService.companyProcessStatus(processId, status));
			}else {
				return	getErrorResponseEntity(Constants.COMPNY_PROCSS_ID_INVALID, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
			}
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}



	@RequestMapping(value = "/api/rfx/company/capabilities/security/search", method = RequestMethod.POST)
	public ResponseEntity<?> companySecuritySearch(@RequestBody CompanySecuritySearch securitySearch) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Company security searching..");
			return  ResponseEntity.ok(rfxAdminService.companySecuritySearch(securitySearch));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}



	@RequestMapping(value = "/api/rfx/company/capabilities/process/search", method = RequestMethod.POST)
	public ResponseEntity<?> companyProcessSearch(@RequestBody CompanyProcessSearch processSearch) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Company process searching..");
			return  ResponseEntity.ok(rfxAdminService.companyProcessSearch(processSearch));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/api/rfx/company/capabilities/quality/control/search", method = RequestMethod.POST)
	public ResponseEntity<?> qualityControlSearch(@RequestBody CompanyQualityControlSearch qualityControlSearch) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Company Quality Control Search searching..");
			return  ResponseEntity.ok(rfxAdminService.qualityControlSearch(qualityControlSearch));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/api/rfx/company/capabilities/methodology/search", method = RequestMethod.POST)
	public ResponseEntity<?> companyMethodologySearch(@RequestBody CompanyMethodologySearch methodologySearch) throws Exception {
		if (rfxAdminService.checkRoleAsAdmin())
		{
			logger.info("Company methodology searching..");
			return  ResponseEntity.ok(rfxAdminService.companyMethodologySearch(methodologySearch));
		}else {
			return getErrorResponseEntity(Constants.CONTACT_ADMIN_COMPNY_CAPABIL, Constants.ERROR_CODE_400, HttpStatus.BAD_REQUEST);

		}
	}


	public ResponseEntity<?> getSuccessResponseEntity(String message) {
		successResponse = new SuccessResponse();
		successResponse.setStatus("Success");
		successResponse.setMessage(message);
		successResponse.setStatusCode(Constants.SUCCESS_CODE);
		return ResponseEntity.ok(successResponse);
	}

	public ResponseEntity<?> getErrorResponseEntity(String errorMessage, int errorCode, HttpStatus httpStatus) {
		errorResponse = new ErrorResponse();
		errorResponse.setErroMessage(errorMessage);
		errorResponse.setErroCode(errorCode);
		return new ResponseEntity<Object>(
				errorResponse, new HttpHeaders(), httpStatus);
	}


}