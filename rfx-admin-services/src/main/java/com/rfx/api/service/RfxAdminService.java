package com.rfx.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rfx.api.model.BidSourceAndRfxCategoriesResp;
import com.rfx.api.model.BusinessSearchResp;
import com.rfx.api.model.BusinessUnitCreate;
import com.rfx.api.model.BusinessUnitListResp;
import com.rfx.api.model.BusinessUnitSearch;
import com.rfx.api.model.BusinessUnitTypesResp;
import com.rfx.api.model.CompanyDetailsCreate;
import com.rfx.api.model.CompanyDetailsSearch;
import com.rfx.api.model.CompanyDetailsSearchResp;
import com.rfx.api.model.CompanyMethodologyCreate;
import com.rfx.api.model.CompanyMethodologySearch;
import com.rfx.api.model.CompanyMethodologySearchResp;
import com.rfx.api.model.CompanyProcessCreate;
import com.rfx.api.model.CompanyProcessSearch;
import com.rfx.api.model.CompanyProcessSearchResp;
import com.rfx.api.model.ProductFieldTypesResp;
import com.rfx.api.model.ProductModuleCreate;
import com.rfx.api.model.ProductModuleResp;
import com.rfx.api.model.CompanyProductSearch;
import com.rfx.api.model.CompanyProductSearchResp;
import com.rfx.api.model.CompanyProductsCreate;
import com.rfx.api.model.CompanyQualityControlSearch;
import com.rfx.api.model.CompanyQualityControlSearchResp;
import com.rfx.api.model.CompanySecurityCreate;
import com.rfx.api.model.CompanySecuritySearch;
import com.rfx.api.model.CompanySecuritySearchResp;
import com.rfx.api.model.CompanyServiceSearch;
import com.rfx.api.model.CompanyServiceSearchResp;
import com.rfx.api.model.CompanyServicesCreate;
import com.rfx.api.model.ContentEntriesCreate;
import com.rfx.api.model.ContentEntriesSearch;
import com.rfx.api.model.ContentEntrySearchResp;
import com.rfx.api.model.DocumentEntrySearch;
import com.rfx.api.model.DocumentEntrySearchResp;
import com.rfx.api.model.KeywordEntryCreate;
import com.rfx.api.model.KeywordEntrySearch;
import com.rfx.api.model.KeywordEntrySearchResp;
import com.rfx.api.model.ProposalDocumentTypesCreate;
import com.rfx.api.model.ProposalDocumentTypesSearch;
import com.rfx.api.model.ProposalDocumentTypesSearchResp;
import com.rfx.api.model.ProposalStatusCreate;
import com.rfx.api.model.ProposalStatusSearch;
import com.rfx.api.model.ProposalStatusSearchResp;
import com.rfx.api.model.QualityControlCreate;
import com.rfx.api.model.RfxCategoriesCreate;
import com.rfx.api.model.RfxCategoriesListResp;
import com.rfx.api.model.RfxCategoriesSearch;
import com.rfx.api.model.RfxCategoriesSearchResp;
import com.rfx.api.model.RfxDocumentTypesCreate;
import com.rfx.api.model.RfxDocumentTypesListResp;
import com.rfx.api.model.RfxDocumentTypesSearch;
import com.rfx.api.model.RfxDocumentTypesSearchResp;
import com.rfx.api.model.RfxStatusListResp;
import com.rfx.api.model.RfxStatusesCreate;
import com.rfx.api.model.RfxStatusesSearch;
import com.rfx.api.model.RfxStatusesSearchResp;
import com.rfx.api.model.RfxTypesCreate;
import com.rfx.api.model.RfxTypesListResp;
import com.rfx.api.model.RfxTypesSearch;
import com.rfx.api.model.RfxTypesSearchResp;
import com.rfx.api.model.ServiceFieldTypesResp;
import com.rfx.api.model.ServiceModuleCreate;
import com.rfx.api.model.ServiceModuleResp;
import com.rfx.api.model.TableBidSourceAndKeyword;
import com.rfx.api.model.TableBusinessUnit;
import com.rfx.api.model.TableBusinessUnitTypes;
import com.rfx.api.model.TableCompanyDetails;
import com.rfx.api.model.TableCompanyMethodology;
import com.rfx.api.model.TableCompanyProcess;
import com.rfx.api.model.TableProductFieldTypes;
import com.rfx.api.model.TableProductModules;
import com.rfx.api.model.TableCompanyProducts;
import com.rfx.api.model.TableCompanyQualityControl;
import com.rfx.api.model.TableCompanySecurity;
import com.rfx.api.model.TableCompanyServices;
import com.rfx.api.model.TableContentEntries;
import com.rfx.api.model.TableDocumentEntries;
import com.rfx.api.model.TableKeywordEntries;
import com.rfx.api.model.TableProposalDocumentTypes;
import com.rfx.api.model.TableProposalStatus;
import com.rfx.api.model.TableRefreshToken;
import com.rfx.api.model.TableRfxCategories;
import com.rfx.api.model.TableRfxDocumentTypes;
import com.rfx.api.model.TableRfxStatuses;
import com.rfx.api.model.TableRfxTypes;
import com.rfx.api.model.TableServiceFieldTypes;
import com.rfx.api.model.TableServiceModules;
import com.rfx.api.model.TableUserAccount;
import com.rfx.api.model.TableWordTemplateAttachments;
import com.rfx.api.model.WordTemplateSearch;
import com.rfx.api.model.WordTemplateSearchResp;
import com.rfx.api.repository.BidSourceAndKeywordRepository;
import com.rfx.api.repository.BusinessUnitRepository;
import com.rfx.api.repository.BusinessUnitTypeRepository;
import com.rfx.api.repository.CompanyDetailsRepository;
import com.rfx.api.repository.CompanyMethodologyRepo;
import com.rfx.api.repository.CompanyProcessRepository;
import com.rfx.api.repository.CompanyProductFieldsRepository;
import com.rfx.api.repository.CompanyProductsRepo;
import com.rfx.api.repository.CompanySecurityRepository;
import com.rfx.api.repository.CompanyServiceFieldsRepository;
import com.rfx.api.repository.CompanyServiceRepository;
import com.rfx.api.repository.ContentEntriesRepository;
import com.rfx.api.repository.DocumentEntriesRepository;
import com.rfx.api.repository.KeywordEntryRepository;
import com.rfx.api.repository.ProductModuleRepository;
import com.rfx.api.repository.ProposalDocumentTypesRepository;
import com.rfx.api.repository.ProposalStatusRepository;
import com.rfx.api.repository.QualityControlRepository;
import com.rfx.api.repository.RefreshTokenRepository;
import com.rfx.api.repository.RfxCategoriesRepository;
import com.rfx.api.repository.RfxDocumentTypesRepository;
import com.rfx.api.repository.RfxStatusesRepository;
import com.rfx.api.repository.RfxTypesRepository;
import com.rfx.api.repository.ServiceModuleRepository;
import com.rfx.api.repository.UserAccountRepository;
import com.rfx.api.repository.WordTemplateRepository;
import com.rfx.api.utils.Constants;


@Service
public class RfxAdminService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(RfxAdminService.class);
	
	

	String authorization_user_email = "";
	String authorize_jwttoken = "";
	
    @Value("${endpointUrl}")
    private String endpointUrl;
    

	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
    private BusinessUnitRepository businessUnitRepository;
	
	@Autowired
	BusinessUnitTypeRepository businessUnitTypeRepository;
	

	@Autowired
    private RfxCategoriesRepository rfxCategoriesRepository;
	
	@Autowired
	private RfxTypesRepository rfxTypesRepository;
	
	@Autowired
	private RfxStatusesRepository rfxStatusesRepository;
	
	@Autowired
	private RfxDocumentTypesRepository rfxDocumentTypesRepository;
	
	@Autowired
	private ProposalDocumentTypesRepository proposalDocumentTypesRepository;
	
	@Autowired
	private ProposalStatusRepository proposalStatusRepository;
	
	@Autowired
	private ContentEntriesRepository contentEntriesRepository;
	
	@Autowired
	private KeywordEntryRepository  keywordEntryRepository;
	
	@Autowired
	private DocumentEntriesRepository documentEntriesRepository;
	
	
	@Autowired
	private BidSourceAndKeywordRepository bidSourceAndKeywordRepository;
	
	@Autowired
	private CompanyProductsRepo companyProductsRepo;
	
	@Autowired
	private CompanyServiceRepository companyServiceRepo;
	
	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;
	
	@Autowired
	private CompanyProductFieldsRepository companyFieldsRepository;
	
	@Autowired
	private CompanyServiceFieldsRepository companyServiceFieldsRepo;
	
	@Autowired
	private CompanyProcessRepository processRepository;
	
	@Autowired
	private CompanyMethodologyRepo methodologyRepo;
	
	@Autowired
	private ProductModuleRepository productModuleRepository;
	
	@Autowired
	private ServiceModuleRepository serviceModuleRepository;
	
	@Autowired
	private QualityControlRepository qualityControlRepository;
	
	@Autowired
	private CompanySecurityRepository companySecurityRepository;
	
	@Autowired
	private WordTemplateRepository wordTemplateRepository;
	
	public String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";
	    
	
	
	
	public int getUserRole(String email) {
		return userAccountRepository.getRoleByUserEmail(email);
	}
	
	public String generateRefreshToken(long expiredSecs, String email) {
		String refreshToken = UUID.randomUUID().toString().trim();
		String expired = String.valueOf(System.currentTimeMillis() / 1000l + expiredSecs);
		TableRefreshToken tableRefreshToken = new TableRefreshToken();
		tableRefreshToken.setToken(refreshToken);
		tableRefreshToken.setExpired(expired);
		tableRefreshToken.setUserEmail(email);
		tableRefreshToken.setCreatedAt(currentDateTime());
		refreshTokenRepository.save(tableRefreshToken);
		return refreshToken;
	}
	public String currentDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateobj = new Date();
		return df.format(dateobj);
	}
	

	
	
	public String getEmailByUserId(String userId) {
		return userAccountRepository.getEmailByUserId(userId);
	}
	

	/**
	 * This method is to get the user information from User registration table.
	 *
	 * @param user email
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	public UserDetails loadUserByUsername(String email) {
		TableUserAccount userAccount = userAccountRepository.findByEmail(email);
		return new User(userAccount.getEmail(), userAccount.getPassword(), new ArrayList<>());
	}
	
	public boolean checkRoleAsAdmin() {
		int role_id = userAccountRepository.getRoleByUserEmail(authorization_user_email);
		if (role_id == Constants.ROLE_ADMIN)
			return true;
		else
			return false;
	}
	
	  public boolean nameExistInBusinessUnit(String name) {
	        String b_id = businessUnitRepository.getIdByBusinessName(name);
	        if (b_id == null)
	            return false;
	        else
	            return true;
	    }
	  
	  public boolean nameExistInRfxCategories(String name) {
	        String rfx_cat_id = rfxCategoriesRepository.getIdByRfxCategoryName(name);
	        if (rfx_cat_id == null)
	            return false;
	        else
	            return true;
	    }
	  
	   
	  
	  
	  public boolean nameExistInProposalDocumentType(String name) {
	        String proposal_document_type_id = proposalDocumentTypesRepository.getIdByProposalDocumentTypeName(name);
	        if (proposal_document_type_id == null)
	            return false;
	        else
	            return true;
	    }
	  
	  
	  public boolean nameExistInProposalStatus(String name) {
	        String proposal_status_id = proposalStatusRepository.getIdByProposalStatusName(name);
	        if (proposal_status_id == null)
	            return false;
	        else
	            return true;
	    }
	  
	  
	  public boolean nameExistInRfxTypes(String name) {
	        String rfx_type_id = rfxTypesRepository.getIdByRfxTypesName(name);
	        
	        if (rfx_type_id == null)
	            return false;
	        else
	            return true;
	    }
	  
	  public boolean nameExistInRfxStatuses(String name) {
	        String rfx_statuses_id = rfxStatusesRepository.getIdByRfxStatusName(name);
	        if (rfx_statuses_id == null)
	            return false;
	        else
	            return true;
	    }
	  
	  public boolean nameExistInRfxDocumentType(String name) {
	        String rfx_document_type_id = rfxDocumentTypesRepository.getIdByRfxDocumentTypeName(name);
	        if (rfx_document_type_id == null)
	            return false;
	        else
	            return true;
	    }
	  public boolean editNameExistByRfxDocumentTypeIdAndName( String rfx_document_type_id,String rfx_document_type_name) {
	        String name = rfxDocumentTypesRepository.getNameByRfxDocumentTypeId(rfx_document_type_id);
	        if (name.equalsIgnoreCase(rfx_document_type_name))
	            return true;
	        else
	            return false;
	    }
	   public void createBusinessUnit(BusinessUnitCreate businessUnitCreate) {
	        String business_id = UUID.randomUUID().toString().trim();
	        TableBusinessUnit tableBusinessUnit = new TableBusinessUnit();
	        tableBusinessUnit.setBusinessId(business_id); 
	        tableBusinessUnit.setName(businessUnitCreate.getName());
	        tableBusinessUnit.setDescription(businessUnitCreate.getDescription());
	        if(businessUnitCreate.getIsNewType())//if new type,if not then add to business unit type 
	        {
	        	addNewBusinessUnitType(businessUnitCreate.getType());
	        }
	        tableBusinessUnit.setType(businessUnitCreate.getType());
	        tableBusinessUnit.setCode(businessUnitCreate.getCode());
	        tableBusinessUnit.setEffectivedate(businessUnitCreate.getEffectivedate());
	        tableBusinessUnit.setStatus(Constants.STATUS_Y);
	        tableBusinessUnit.setCreatedAt(currentDateTime());
	        tableBusinessUnit.setUpdatedAt(currentDateTime());
	        businessUnitRepository.save(tableBusinessUnit);
	        logger.info("createBusinessUnit");
	    }
	   
	   public void addNewBusinessUnitType(String businessUnitType) 
	   {
		   
        	TableBusinessUnitTypes businessUnitTypes=new TableBusinessUnitTypes();
        	businessUnitTypes.setTypeName(businessUnitType);
        	businessUnitTypes.setStatus("Y");
        	businessUnitTypes.setCreatedAt(currentDateTime());
        	businessUnitTypeRepository.save(businessUnitTypes);
	       
	   }
	   
	   public BusinessUnitTypesResp getBusinessUnitTypesList()
		{
			ArrayList<TableBusinessUnitTypes> businessUnitTypesList=(ArrayList<TableBusinessUnitTypes>) businessUnitTypeRepository.findAll();
			ArrayList<TableBusinessUnitTypes> businessUnitTypesListData=new ArrayList<TableBusinessUnitTypes>();
			for(TableBusinessUnitTypes businessUnitTypeData:businessUnitTypesList)
			{
				businessUnitTypesListData.add(businessUnitTypeData);
			}
			BusinessUnitTypesResp businessUnitTypesResp=new BusinessUnitTypesResp();
			businessUnitTypesResp.setBusinessUnitTypes(businessUnitTypesListData);
			businessUnitTypesResp.setMessage("Success");
			businessUnitTypesResp.setCode(200);
			return businessUnitTypesResp;
		}
	   
	
	    public boolean editNameExist( String name) {
	        String b_id = businessUnitRepository.getIdByBusinessName(name);
	        if (b_id == null)
	            return false;
	        else
	            return true;
	    }
	    public boolean editNameExistByBusinessIdAndName( String business_id,String business_name) {
	        String name = businessUnitRepository.getNameByBusinessId(business_id);
	        System.out.println(name+" "+business_id);
	        if (name.equalsIgnoreCase(business_name))
	            return true;
	        else
	            return false;
	    }
	    
	    
	    public boolean businessIdExist(String business_id) {
	        String name = businessUnitRepository.getNameByBusinessId(business_id);
	        if (name == null)
	            return false;
	        else
	            return true;
	    }
	    
	    
	    public boolean businessUnitTypeIdExist(String businessTypeId) {
	        boolean businessTypeIdExist = businessUnitTypeRepository.existsById(Integer.parseInt(businessTypeId));
	        return businessTypeIdExist;
	    }
	    
	    public String setRfxBusinessStatus(String business_id, String status) {
	        businessUnitRepository.updateStatus(business_id, status);
	        logger.info("RfxBusinessUnit status updated..");
	        if (status.equalsIgnoreCase(Constants.STATUS_Y))
	            return Constants.BU_ACTIVATED;
	        else
	            return Constants.BU_INACTIVATED;

	    }
	    
	    public String setBusinessUnitTypeStatus(String businessTypeId, String status) {
	    	TableBusinessUnitTypes  tableBusinessUnitTypes=businessUnitTypeRepository.findById(Integer.parseInt(businessTypeId)).get();
	        logger.info("RfxBusiness Type Status status updated..");
	        tableBusinessUnitTypes.setStatus(status);
	        tableBusinessUnitTypes.setUpdatedAt(currentDateTime());
	        businessUnitTypeRepository.save(tableBusinessUnitTypes);
	        if (status.equalsIgnoreCase(Constants.STATUS_Y))
	        { 
	            return Constants.BU_TYPE_ACTIVATED;
	        }else
	        {
	            return Constants.BU_TYPE_INACTIVATED;
	        }
	        

	    }
	  
	    
	    public int updateBusinessUnit(BusinessUnitCreate businessUnitCreate) {
	    	logger.info("updateBusinessUnit");
	    	if(businessUnitCreate.getIsNewType())
	        {
	        	addNewBusinessUnitType(businessUnitCreate.getType());
	        }
	        return businessUnitRepository.updateBusinessUnit(businessUnitCreate.getBusiness_id(), businessUnitCreate.getName(), businessUnitCreate.getDescription(),
	                businessUnitCreate.getCode(),businessUnitCreate.getType(), businessUnitCreate.getEffectivedate(), currentDateTime());
	    }
	    
	

	/**
	 * @param email
	 * @return
	 */
	public String emailExist(String email) {
		return userAccountRepository.getUserIdByEmail(email);
	}
	
	
	  public String businessUnitCodeExist( String code) {
	        return businessUnitRepository.getBidByCode(code);
	    }
	

	/**
	 * This method is to set authorization token of every service call.
	 *
	 * @param authorization_user_email
	 */
	public void setAuthorizationUserEmail(String authorization_user_email, String authorize_jwttoken) {
		this.authorization_user_email = authorization_user_email;
		this.authorize_jwttoken = authorize_jwttoken;
	}
	
	
	 public BusinessSearchResp businessSearch(BusinessUnitSearch businessUnitSearch) {
	        int limit = businessUnitSearch.getLimit();
	        int page = businessUnitSearch.getPage_no();
	        Pageable pageable = null;
	        Page<TableBusinessUnit> businessUnit = null;

	        if (businessUnitSearch.getOrderby() != null && businessUnitSearch.getOrderby() != Constants.EMPTY) {
	            if (businessUnitSearch.getSortby() != null && businessUnitSearch.getSortby() != Constants.EMPTY) {
	                if (businessUnitSearch.getSortby().equalsIgnoreCase(Constants.ASC))
	                    pageable = PageRequest.of(page, limit, Sort.by(businessUnitSearch.getOrderby()).ascending());
	                else
	                    pageable = PageRequest.of(page, limit, Sort.by(businessUnitSearch.getOrderby()).descending());
	            } else {
	                pageable = PageRequest.of(page, limit, Sort.by(businessUnitSearch.getOrderby()).descending());
	            }

	        } else {
	            if (businessUnitSearch.getSortby().equalsIgnoreCase(Constants.ASC))
	                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
	            else
	                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
	        }
	        // status name effective code type
	        if (Constants.isNullOrEmpty(businessUnitSearch.getStatus()) && (Constants.isNullOrEmpty(businessUnitSearch.getName()) && Constants.isNullOrEmpty(businessUnitSearch.getCode()))
	                && Constants.isNullOrEmpty(businessUnitSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(businessUnitSearch.getType()) ) {
	            businessUnit = businessUnitRepository.findByStatusAndNameAndCodeAndTypeAndEffectivedateBetween(
	                    businessUnitSearch.getStatus(), businessUnitSearch.getName(),businessUnitSearch.getCode(),businessUnitSearch.getType(),  businessUnitSearch.getEffectivedate_st(), businessUnitSearch.getEffective_end(), pageable);
	        }
	        // status effective
	        else if (Constants.isNullOrEmpty(businessUnitSearch.getStatus()) && Constants.isNullOrEmpty(businessUnitSearch.getEffectivedate_st())) {
	            businessUnit = businessUnitRepository.findByStatusAndEffectivedateBetween(businessUnitSearch.getStatus(), businessUnitSearch.getEffectivedate_st(), businessUnitSearch.getEffective_end(), pageable);
	        }
	        //// name effective
	        else if (Constants.isNullOrEmpty(businessUnitSearch.getName()) && Constants.isNullOrEmpty(businessUnitSearch.getEffectivedate_st())) {
	            businessUnit = businessUnitRepository.findByNameContainingAndEffectivedateBetween(businessUnitSearch.getName(), businessUnitSearch.getEffectivedate_st(), businessUnitSearch.getEffective_end(), pageable);
	        }
	        //// code effective
	        else if (Constants.isNullOrEmpty(businessUnitSearch.getCode()) && Constants.isNullOrEmpty(businessUnitSearch.getEffectivedate_st())) {
	            businessUnit = businessUnitRepository.findByCodeContainingAndEffectivedateBetween(businessUnitSearch.getCode(), businessUnitSearch.getEffectivedate_st(), businessUnitSearch.getEffective_end(), pageable);
	        }

	        //// type effective
	        else if (Constants.isNullOrEmpty(businessUnitSearch.getType()) && Constants.isNullOrEmpty(businessUnitSearch.getEffectivedate_st())) {
	            businessUnit = businessUnitRepository.findByTypeContainingAndEffectivedateBetween(businessUnitSearch.getType(), businessUnitSearch.getEffectivedate_st(), businessUnitSearch.getEffective_end(), pageable);
	        }
	        // name Status code
	        else if (Constants.isNullOrEmpty(businessUnitSearch.getName()) && Constants.isNullOrEmpty(businessUnitSearch.getStatus()) && Constants.isNullOrEmpty(businessUnitSearch.getCode()) && Constants.isNullOrEmpty(businessUnitSearch.getType())) {
	            businessUnit = businessUnitRepository.findByNameContainingAndStatusAndCodeAndType(businessUnitSearch.getName(),  businessUnitSearch.getStatus(), businessUnitSearch.getCode(),businessUnitSearch.getType(),pageable);
	        }
	        // name Status
	        else if (Constants.isNullOrEmpty(businessUnitSearch.getName()) && Constants.isNullOrEmpty(businessUnitSearch.getStatus())) {
	            businessUnit = businessUnitRepository.findByNameContainingAndStatus(businessUnitSearch.getName(),  businessUnitSearch.getStatus(),pageable);
	        }
	        // Only effective
	        else if(Constants.isNullOrEmpty(businessUnitSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(businessUnitSearch.getEffective_end())) {
	            businessUnit = businessUnitRepository.findByEffectivedateBetween(businessUnitSearch.getEffectivedate_st(), businessUnitSearch.getEffective_end(), pageable);
	        }
	        //only status
	        else if(Constants.isNullOrEmpty(businessUnitSearch.getStatus())) {
	            businessUnit = businessUnitRepository.findByStatusContaining(businessUnitSearch.getStatus(), pageable);
	        }
	        //Only name
	        else if(Constants.isNullOrEmpty(businessUnitSearch.getName())) {
	            businessUnit = businessUnitRepository.findByNameContaining(businessUnitSearch.getName(),pageable);
	        }
	      //Only code
	        else if(Constants.isNullOrEmpty(businessUnitSearch.getCode())) {
	            businessUnit = businessUnitRepository.findByCodeContaining(businessUnitSearch.getCode(),pageable);
	        }
	        //Only type
	        else if(Constants.isNullOrEmpty(businessUnitSearch.getType())) {
	            businessUnit = businessUnitRepository.findByTypeContaining(businessUnitSearch.getType(),pageable);
	        }
	        else {
	            businessUnit = businessUnitRepository.findAll(pageable);
	        }
	        BusinessSearchResp businessResp = new BusinessSearchResp();
	        businessResp.setBusinessSearch(businessUnit);
	        businessResp.setMessage("Success");
	        businessResp.setCode(200);
	        return businessResp;
	    }

	 
	 public BusinessUnitListResp getBusinessUnitList()
	 {
		 BusinessUnitListResp businessUnitListResp=new BusinessUnitListResp();
		 businessUnitListResp.setBusinessUnitList(businessUnitRepository.findAll());
		 return businessUnitListResp;
		 
	 }
	 
	 public RfxCategoriesListResp getRfxCategoriesList()
	 {
		 RfxCategoriesListResp rfxCategoriesList=new RfxCategoriesListResp();
		 rfxCategoriesList.setRfxCategoriesList(rfxCategoriesRepository.findAll());
		 return rfxCategoriesList;
	 }
	 
	 public  RfxDocumentTypesListResp getRfxDocumentTypesList()
	 {
		 RfxDocumentTypesListResp rfxDocumentTypesList=new RfxDocumentTypesListResp();
		 rfxDocumentTypesList.setRfxDocumentTypesList(rfxDocumentTypesRepository.findAll());
		 return rfxDocumentTypesList;
		 
	 }
	 
	 public  RfxTypesListResp getRfxTypesList()
	 {
		 RfxTypesListResp rfxTypesList=new RfxTypesListResp();
		 rfxTypesList.setRfxTypesList(rfxTypesRepository.findAll());
		 return rfxTypesList;
		 
	 }
	 
	 public  RfxStatusListResp getRfxStatusList()
	 {
		 RfxStatusListResp rfxStatusList=new RfxStatusListResp();
		 rfxStatusList.setRfxStatusList(rfxStatusesRepository.findAll());
		 return rfxStatusList;
	 }
	   
	    
	    //Rfx-Categories
		   public void createRfxCategories(RfxCategoriesCreate rfxCategoriesCreate) {
		        String rfx_category_id = UUID.randomUUID().toString().trim();
		        TableRfxCategories tableRfxCategories = new TableRfxCategories();
		        tableRfxCategories.setRfxCategoryId(rfx_category_id) ; 
		        tableRfxCategories.setName(rfxCategoriesCreate.getName());
		        tableRfxCategories.setDescription(rfxCategoriesCreate.getDescription());
		        tableRfxCategories.setCode(rfxCategoriesCreate.getCode());
		        tableRfxCategories.setEffectivedate(rfxCategoriesCreate.getEffectivedate());
		        tableRfxCategories.setStatus(Constants.STATUS_Y);
		        tableRfxCategories.setCreatedAt(currentDateTime());
		        tableRfxCategories.setUpdatedAt(currentDateTime());
		        rfxCategoriesRepository.save(tableRfxCategories);
		    }
		   
		   public boolean editRfxCategoryNameExist( String name) {
		        String rfx_cat_id = rfxCategoriesRepository.getIdByRfxCategoryName(name);
		        if (rfx_cat_id == null)
		            return false;
		        else
		            return true;
		    }
		   
		    public boolean editNameExistByRfxCategoriesIdAndName( String rfx_category_id,String rfx_category_name) {
		        String name = rfxCategoriesRepository.getNameByRfxCategoriesId(rfx_category_id);
		        if (name.equalsIgnoreCase(rfx_category_name))
		            return true;
		        else
		            return false;
		    }
		    
		    public boolean rfxCategoryIdExist(String rfx_category_id) {
		        String name = rfxCategoriesRepository.getNameByRfxCategoriesId(rfx_category_id);
		       
		        if (name == null)
		            return false;
		        else
		            return true;
		    }
		    
		    public String setRfxCategoryStatus(String rfx_category_id, String status) {
		    	rfxCategoriesRepository.updateStatus(rfx_category_id, status);
		        if (status.equalsIgnoreCase(Constants.STATUS_Y))
		            return Constants.RFX_CATG_ACTIVATED;
		        else
		            return Constants.RFX_CATG_INACTIVATED;

		    }
		    
		    public int updateRfxCategory(RfxCategoriesCreate rfxCategoriesCreate) {
		        return rfxCategoriesRepository.updateRfxCategories(rfxCategoriesCreate.getRfx_category_id(), rfxCategoriesCreate.getName(), rfxCategoriesCreate.getDescription(),
		        		rfxCategoriesCreate.getCode(), rfxCategoriesCreate.getEffectivedate(), currentDateTime());
		    }
		    
		    public String rfxCategoryCodeExist( String code) {
		        return rfxCategoriesRepository.getRfxCategoryIdByCode(code);
		    }
		    


			 public RfxCategoriesSearchResp rfxCategoriesSearch(RfxCategoriesSearch rfxCategoriesSearch) {
			        int limit = rfxCategoriesSearch.getLimit();
			        int page = rfxCategoriesSearch.getPage_no();
			        Pageable pageable = null;
			        Page<TableRfxCategories> rfxCategory = null;

			        if (rfxCategoriesSearch.getOrderby() != null && rfxCategoriesSearch.getOrderby() != Constants.EMPTY) {
			            if (rfxCategoriesSearch.getSortby() != null && rfxCategoriesSearch.getSortby() != Constants.EMPTY) {
			                if (rfxCategoriesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                    pageable = PageRequest.of(page, limit, Sort.by(rfxCategoriesSearch.getOrderby()).ascending());
			                else
			                    pageable = PageRequest.of(page, limit, Sort.by(rfxCategoriesSearch.getOrderby()).descending());
			            } else {
			                pageable = PageRequest.of(page, limit, Sort.by(rfxCategoriesSearch.getOrderby()).descending());
			            }

			        } else {
			            if (rfxCategoriesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
			            else
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
			        }
			        // status name effective code
			        if (Constants.isNullOrEmpty(rfxCategoriesSearch.getStatus()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getName()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getCode())
			                && Constants.isNullOrEmpty(rfxCategoriesSearch.getEffectivedate_st())) {
			            rfxCategory = rfxCategoriesRepository.findByStatusAndNameAndCodeAndEffectivedateBetween(
			                    rfxCategoriesSearch.getStatus(), rfxCategoriesSearch.getName(),rfxCategoriesSearch.getCode(), rfxCategoriesSearch.getEffectivedate_st(), rfxCategoriesSearch.getEffective_end(), pageable);
			        }
			        // status effective
			        else if (Constants.isNullOrEmpty(rfxCategoriesSearch.getStatus()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getEffectivedate_st())) {
			            rfxCategory = rfxCategoriesRepository.findByStatusAndEffectivedateBetween(rfxCategoriesSearch.getStatus(), rfxCategoriesSearch.getEffectivedate_st(), rfxCategoriesSearch.getEffective_end(), pageable);
			        }
			        //// name effective
			        else if (Constants.isNullOrEmpty(rfxCategoriesSearch.getName()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getEffectivedate_st())) {
			            rfxCategory = rfxCategoriesRepository.findByNameAndEffectivedateBetween(rfxCategoriesSearch.getName(), rfxCategoriesSearch.getEffectivedate_st(), rfxCategoriesSearch.getEffective_end(), pageable);
			        }
			        /// code effective
			        else if (Constants.isNullOrEmpty(rfxCategoriesSearch.getCode()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getEffectivedate_st())) {
			            rfxCategory = rfxCategoriesRepository.findByCodeAndEffectivedateBetween(rfxCategoriesSearch.getCode(), rfxCategoriesSearch.getEffectivedate_st(), rfxCategoriesSearch.getEffective_end(), pageable);
			        }
			        // name Status code
			        else if (Constants.isNullOrEmpty(rfxCategoriesSearch.getName()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getStatus()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getCode())) {
			            rfxCategory = rfxCategoriesRepository.findByNameAndStatusAndCode(rfxCategoriesSearch.getName(), rfxCategoriesSearch.getStatus(),rfxCategoriesSearch.getCode() ,pageable);
			        }
			        // Only effective
			        else if(Constants.isNullOrEmpty(rfxCategoriesSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(rfxCategoriesSearch.getEffective_end())) {
			            rfxCategory = rfxCategoriesRepository.findByEffectivedateBetween(rfxCategoriesSearch.getEffectivedate_st(), rfxCategoriesSearch.getEffective_end(), pageable);
			        }
			        //only status
			        else if(Constants.isNullOrEmpty(rfxCategoriesSearch.getStatus())) {
			            rfxCategory = rfxCategoriesRepository.findByStatus(rfxCategoriesSearch.getStatus(), pageable);
			        }
			        //Only name
			        else if(Constants.isNullOrEmpty(rfxCategoriesSearch.getName())) {
			            rfxCategory = rfxCategoriesRepository.findByNameContaining(rfxCategoriesSearch.getName(),pageable);
			        }
			        //only code
			        else if(Constants.isNullOrEmpty(rfxCategoriesSearch.getCode())) {
			            rfxCategory = rfxCategoriesRepository.findByCodeContaining(rfxCategoriesSearch.getCode(),pageable);
			        }
			        else {
			            rfxCategory = rfxCategoriesRepository.findAll(pageable);
			        }
			        RfxCategoriesSearchResp rfxCategoriesSearchResp= new RfxCategoriesSearchResp();
			        rfxCategoriesSearchResp.setRfxCategoriesSearch(rfxCategory);
			        rfxCategoriesSearchResp.setMessage("Success");
			        rfxCategoriesSearchResp.setCode(200);
			        return rfxCategoriesSearchResp;
			    }
			 
			 //Rfx Types
			 
			 public void createRfxTypes(RfxTypesCreate rfxTypesCreate) {
			        String rfx_type_id = UUID.randomUUID().toString().trim();
			        TableRfxTypes tableRfxTypes = new TableRfxTypes();
			        tableRfxTypes.setRfxTypeId(rfx_type_id) ; 
			        tableRfxTypes.setName(rfxTypesCreate.getName());
			        tableRfxTypes.setDescription(rfxTypesCreate.getDescription());
			        tableRfxTypes.setCode(rfxTypesCreate.getCode());
			        tableRfxTypes.setEffectivedate(rfxTypesCreate.getEffectivedate());
			        tableRfxTypes.setStatus(Constants.STATUS_Y);
			        tableRfxTypes.setCreatedAt(currentDateTime());
			        tableRfxTypes.setUpdatedAt(currentDateTime());
			        rfxTypesRepository.save(tableRfxTypes);
			    }
			   
			   public boolean editRfxTypeNameExist( String name) {
			        String type_id = rfxTypesRepository.getIdByRfxTypesName(name);
			        if (type_id == null)
			            return false;
			        else
			            return true;
			    }
				  public boolean editNameExistByRfxTypeIdAndName( String rfx_type_id,String rfx_type_name) {
				        String name = rfxTypesRepository.getNameByRfxTypesId(rfx_type_id);
				        if (name.equalsIgnoreCase(rfx_type_name))
				            return true;
				        else
				            return false;
				    }
			   
			   
			    public boolean rfxTypeIdExist(String rfx_type_id) {
			        String name = rfxTypesRepository.getNameByRfxTypesId(rfx_type_id);
			        System.out.println("!!!!>>"+rfx_type_id);
			        if (name == null)
			            return false;
			        else
			            return true;
			    }
			    
			    public String setRfxTypeStatus(String rfx_type_id, String status) {
			    	rfxTypesRepository.updateStatus(rfx_type_id, status);
			        if (status.equalsIgnoreCase(Constants.STATUS_Y))
			            return Constants.RFX_TYPE_ACTIVATED;
			        else
			            return Constants.RFX_TYPE_INACTIVATED;

			    }
			    
			    public int updateRfxType(RfxTypesCreate rfxTypeCreate) {
			        return rfxTypesRepository.updateRfxTypes(rfxTypeCreate.getRfx_type_id(), rfxTypeCreate.getName(), rfxTypeCreate.getDescription(),
			        		rfxTypeCreate.getCode(), rfxTypeCreate.getEffectivedate(), currentDateTime());
			    }
			    
			    public String rfxTypeCodeExist( String code) {
			        return rfxTypesRepository.getRfxTypeIdByCode(code);
			    }
			    
			    public String rfxStatuesCodeExist( String code) {
			        return rfxStatusesRepository.getRfxStatusIdByCode(code);
			    }
			    
	     public WordTemplateSearchResp wordTemplateSearch(WordTemplateSearch wordTemplateSearch) {
	    	    int limit = wordTemplateSearch.getLimit();
		        int page = wordTemplateSearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableWordTemplateAttachments> wordTemplateAttachments = null;
		        

		        if (wordTemplateSearch.getOrderby() != null && wordTemplateSearch.getOrderby() != Constants.EMPTY) {
		            if (wordTemplateSearch.getSortby() != null && wordTemplateSearch.getSortby() != Constants.EMPTY) {
		                if (wordTemplateSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(wordTemplateSearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(wordTemplateSearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(wordTemplateSearch.getOrderby()).descending());
		            }

		        } else {
		            if (wordTemplateSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
		        }
		        if(Constants.isNullOrEmpty(wordTemplateSearch.getTemplateName()) && 
		        		Constants.isNullOrEmpty(wordTemplateSearch.getDescription()) && 
		        		Constants.isNullOrEmpty(wordTemplateSearch.getStatus())){
		        	wordTemplateAttachments=wordTemplateRepository.findByNameAndDescrAndStatus(wordTemplateSearch.getTemplateName(), wordTemplateSearch.getDescription(), wordTemplateSearch.getStatus(), pageable);
		        }
		        else if(Constants.isNullOrEmpty(wordTemplateSearch.getTemplateName()) && 
		        		Constants.isNullOrEmpty(wordTemplateSearch.getDescription()) )
		        {
		        	wordTemplateAttachments=wordTemplateRepository.findByNameAndDescr(wordTemplateSearch.getTemplateName(), wordTemplateSearch.getDescription(),pageable);
				 }
		        else if(Constants.isNullOrEmpty(wordTemplateSearch.getTemplateName()))
		        {
		        	wordTemplateAttachments=wordTemplateRepository.findByNameContaining(wordTemplateSearch.getTemplateName(),pageable);
				 }
		        else if(Constants.isNullOrEmpty(wordTemplateSearch.getDescription()))
		        {
		        	wordTemplateAttachments=wordTemplateRepository.findByDescrContaining(wordTemplateSearch.getDescription(),pageable);
				}
		        else if(Constants.isNullOrEmpty(wordTemplateSearch.getStatus()))
		        {
		        	wordTemplateAttachments=wordTemplateRepository.findByStatus(wordTemplateSearch.getStatus(),pageable);
				}
		        else {
		        	wordTemplateAttachments=wordTemplateRepository.findAll(pageable);
		        }
		        
		        WordTemplateSearchResp wordTemplateSearchResp=new WordTemplateSearchResp();
		        wordTemplateSearchResp.setWordTemplateAttachments(wordTemplateAttachments);
		        wordTemplateSearchResp.setMessage("Success");
		        wordTemplateSearchResp.setCode(200);
		        return wordTemplateSearchResp;
		        
	     }

		 public RfxTypesSearchResp rfxTypesSearch(RfxTypesSearch rfxTypesSearch) {
		        int limit = rfxTypesSearch.getLimit();
		        int page = rfxTypesSearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableRfxTypes> rfxTypes = null;

		        if (rfxTypesSearch.getOrderby() != null && rfxTypesSearch.getOrderby() != Constants.EMPTY) {
		            if (rfxTypesSearch.getSortby() != null && rfxTypesSearch.getSortby() != Constants.EMPTY) {
		                if (rfxTypesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(rfxTypesSearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(rfxTypesSearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(rfxTypesSearch.getOrderby()).descending());
		            }

		        } else {
		            if (rfxTypesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
		        }
		        // status name effective code
		        if (Constants.isNullOrEmpty(rfxTypesSearch.getStatus()) && (Constants.isNullOrEmpty(rfxTypesSearch.getName())) && (Constants.isNullOrEmpty(rfxTypesSearch.getCode()))
		                && Constants.isNullOrEmpty(rfxTypesSearch.getEffectivedate_st())) {
		        	rfxTypes = rfxTypesRepository.findByStatusAndNameContainingAndCodeAndEffectivedateBetween(
		            		rfxTypesSearch.getStatus(), rfxTypesSearch.getName(),rfxTypesSearch.getCode(), rfxTypesSearch.getEffectivedate_st(), rfxTypesSearch.getEffective_end(), pageable);
		        }
		        // status effective
		        else if (Constants.isNullOrEmpty(rfxTypesSearch.getStatus()) && Constants.isNullOrEmpty(rfxTypesSearch.getEffectivedate_st())) {
		        	rfxTypes = rfxTypesRepository.findByStatusAndEffectivedateBetween(rfxTypesSearch.getStatus(), rfxTypesSearch.getEffectivedate_st(), rfxTypesSearch.getEffective_end(), pageable);
		        }
		     // code effective
		        else if (Constants.isNullOrEmpty(rfxTypesSearch.getCode()) && Constants.isNullOrEmpty(rfxTypesSearch.getEffectivedate_st())) {
		        	rfxTypes = rfxTypesRepository.findByCodeContainingAndEffectivedateBetween(rfxTypesSearch.getCode(), rfxTypesSearch.getEffectivedate_st(), rfxTypesSearch.getEffective_end(), pageable);
		        }
		        
		        //// name effective
		        else if (Constants.isNullOrEmpty(rfxTypesSearch.getName()) && Constants.isNullOrEmpty(rfxTypesSearch.getEffectivedate_st())) {
		        	rfxTypes = rfxTypesRepository.findByNameContainingAndEffectivedateBetween(rfxTypesSearch.getName(), rfxTypesSearch.getEffectivedate_st(), rfxTypesSearch.getEffective_end(), pageable);
		        }
		        // name Status code
		        else if (Constants.isNullOrEmpty(rfxTypesSearch.getName()) && Constants.isNullOrEmpty(rfxTypesSearch.getStatus())  && Constants.isNullOrEmpty(rfxTypesSearch.getCode())) {
		        	rfxTypes = rfxTypesRepository.findByNameContainingAndStatusAndCode(rfxTypesSearch.getName(), rfxTypesSearch.getStatus(),rfxTypesSearch.getCode(), pageable);
		        }
		        // Only effective
		        else if(Constants.isNullOrEmpty(rfxTypesSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(rfxTypesSearch.getEffective_end())) {
		        	rfxTypes = rfxTypesRepository.findByEffectivedateBetween(rfxTypesSearch.getEffectivedate_st(), rfxTypesSearch.getEffective_end(), pageable);
		        }
		        //only status
		        else if(Constants.isNullOrEmpty(rfxTypesSearch.getStatus())) {
		        	rfxTypes = rfxTypesRepository.findByStatus(rfxTypesSearch.getStatus(), pageable);
		        }
		      //only code
		        else if(Constants.isNullOrEmpty(rfxTypesSearch.getCode())) {
		        	rfxTypes = rfxTypesRepository.findByCodeContaining(rfxTypesSearch.getCode(), pageable);
		        }
		        //Only name
		        else if(Constants.isNullOrEmpty(rfxTypesSearch.getName())) {
		        	rfxTypes = rfxTypesRepository.findByNameContaining(rfxTypesSearch.getName(),pageable);
		        }
		        else {
		        	rfxTypes = rfxTypesRepository.findAll(pageable);
		        }
		        RfxTypesSearchResp rfxTypesSearchResp= new RfxTypesSearchResp();
		        rfxTypesSearchResp.setRfxTypesSearch(rfxTypes);
		        rfxTypesSearchResp.setMessage("Success");
		        rfxTypesSearchResp.setCode(200);
		        return rfxTypesSearchResp;
		    }
		 
		 
		 //RFX STATUSES
		 
		 public void createRfxStatuses(RfxStatusesCreate rfxStatusesCreate) {
		        String rfx_status_id = UUID.randomUUID().toString().trim();
		        TableRfxStatuses tableRfxStatuses = new TableRfxStatuses();
		        tableRfxStatuses.setRfxStatusId(rfx_status_id) ; 
		        tableRfxStatuses.setName(rfxStatusesCreate.getName());
		        tableRfxStatuses.setDescription(rfxStatusesCreate.getDescription());
		        tableRfxStatuses.setCode(rfxStatusesCreate.getCode());
		        tableRfxStatuses.setEffectivedate(rfxStatusesCreate.getEffectivedate());
		        tableRfxStatuses.setStatus(Constants.STATUS_Y);
		        tableRfxStatuses.setCreatedAt(currentDateTime());
		        tableRfxStatuses.setUpdatedAt(currentDateTime());
		        rfxStatusesRepository.save(tableRfxStatuses);
		    }
		 
		 public boolean editRfxStatusNameExist(String name) {
		        String status_id = rfxStatusesRepository.getIdByRfxStatusName(name);
		        if (status_id == null)
		            return false;
		        else
		            return true;
		    }
		  public boolean editNameExistByRfxStatusIdAndName( String rfx_status_id,String rfx_status_name) {
		        String name = rfxStatusesRepository.getNameByRfxStatusId(rfx_status_id);
		        if (name.equalsIgnoreCase(rfx_status_name))
		            return true;
		        else
		            return false;
		    }
		 
		   public boolean getNameByRfxStatusId( String rfx_status_id) {
		        String name = rfxStatusesRepository.getNameByRfxStatusId(rfx_status_id);
		        if (name == null)
		            return false;
		        else
		            return true;
		    }
		   
		    public boolean rfxStatusIdExist(String rfx_status_id) {
		        String name = rfxStatusesRepository.getNameByRfxStatusId(rfx_status_id);
		       
		        if (name == null)
		            return false;
		        else
		            return true;
		    }
		    
		    public String setRfxStatusesStatus(String rfx_status_id, String status) {
		    	rfxStatusesRepository.updateStatus(rfx_status_id, status);
		        if (status.equalsIgnoreCase(Constants.STATUS_Y))
		            return Constants.RFX_STATUS_ACTIVATED;
		        else
		            return Constants.RFX_STATUS_INACTIVATED;

		    }
		    
		    public String setKeywordEntryStatus(String keywordEntryId, String status) {
		    	keywordEntryRepository.updateStatus(keywordEntryId, status);
		        if (status.equalsIgnoreCase(Constants.STATUS_Y))
		            return Constants.KE_STATUS_ACTIVATED;
		        else
		            return Constants.KE_STATUS_INACTIVATED;

		    }

		    public String setContentEntryStatus(String contentEntryId, String status) {
		    	contentEntriesRepository.updateStatus(contentEntryId, status);
		        if (status.equalsIgnoreCase(Constants.STATUS_Y))
		            return Constants.CE_STATUS_ACTIVATED;
		        else
		            return Constants.CE_STATUS_INACTIVATED;

		    }
		    
		    
		    
		    
		    
		    public int updateRfxStatus(RfxStatusesCreate rfxStatusesCreate) {
		        return rfxStatusesRepository.updateRfxStatus(rfxStatusesCreate.getRfx_status_id(), rfxStatusesCreate.getName(), rfxStatusesCreate.getDescription(),
		        		rfxStatusesCreate.getCode(), rfxStatusesCreate.getEffectivedate(), currentDateTime());
		    }
		    

		   


			 public RfxStatusesSearchResp rfxStatusesSearch(RfxStatusesSearch rfxStatusesSearch) {
			        int limit = rfxStatusesSearch.getLimit();
			        int page = rfxStatusesSearch.getPage_no();
			        Pageable pageable = null;
			        Page<TableRfxStatuses> rfxStatuses = null;

			        if (rfxStatusesSearch.getOrderby() != null && rfxStatusesSearch.getOrderby() != Constants.EMPTY) {
			            if (rfxStatusesSearch.getSortby() != null && rfxStatusesSearch.getSortby() != Constants.EMPTY) {
			                if (rfxStatusesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                    pageable = PageRequest.of(page, limit, Sort.by(rfxStatusesSearch.getOrderby()).ascending());
			                else
			                    pageable = PageRequest.of(page, limit, Sort.by(rfxStatusesSearch.getOrderby()).descending());
			            } else {
			                pageable = PageRequest.of(page, limit, Sort.by(rfxStatusesSearch.getOrderby()).descending());
			            }

			        } else {
			            if (rfxStatusesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
			            else
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
			        }
			        // status name effective code
			        if (Constants.isNullOrEmpty(rfxStatusesSearch.getStatus()) && (Constants.isNullOrEmpty(rfxStatusesSearch.getName())) && (Constants.isNullOrEmpty(rfxStatusesSearch.getCode()))
			                && Constants.isNullOrEmpty(rfxStatusesSearch.getEffectivedate_st())) {
			        	rfxStatuses = rfxStatusesRepository.findByStatusAndNameAndCodeAndEffectivedateBetween(
			        			rfxStatusesSearch.getStatus(), rfxStatusesSearch.getName(),rfxStatusesSearch.getCode(), rfxStatusesSearch.getEffectivedate_st(), rfxStatusesSearch.getEffective_end(), pageable);
			        }
			        // status effective
			        else if (Constants.isNullOrEmpty(rfxStatusesSearch.getStatus()) && Constants.isNullOrEmpty(rfxStatusesSearch.getEffectivedate_st())) {
			        	rfxStatuses = rfxStatusesRepository.findByStatusAndEffectivedateBetween(rfxStatusesSearch.getStatus(), rfxStatusesSearch.getEffectivedate_st(), rfxStatusesSearch.getEffective_end(), pageable);
			        }
			        //// code effective
			        else if (Constants.isNullOrEmpty(rfxStatusesSearch.getCode()) && Constants.isNullOrEmpty(rfxStatusesSearch.getEffectivedate_st())) {
			        	rfxStatuses = rfxStatusesRepository.findByCodeAndEffectivedateBetween(rfxStatusesSearch.getCode(), rfxStatusesSearch.getEffectivedate_st(), rfxStatusesSearch.getEffective_end(), pageable);
			        }
			        //// name effective 
			        else if (Constants.isNullOrEmpty(rfxStatusesSearch.getName()) && Constants.isNullOrEmpty(rfxStatusesSearch.getEffectivedate_st())) {
			        	rfxStatuses = rfxStatusesRepository.findByNameAndEffectivedateBetween(rfxStatusesSearch.getName(), rfxStatusesSearch.getEffectivedate_st(), rfxStatusesSearch.getEffective_end(), pageable);
			        }
			        // name Status code
			        else if (Constants.isNullOrEmpty(rfxStatusesSearch.getName()) && Constants.isNullOrEmpty(rfxStatusesSearch.getStatus()) && Constants.isNullOrEmpty(rfxStatusesSearch.getCode())) {
			        	rfxStatuses = rfxStatusesRepository.findByNameAndStatusAndCode(rfxStatusesSearch.getName(), rfxStatusesSearch.getStatus(),rfxStatusesSearch.getCode(), pageable);
			        }
			        // Only effective
			        else if(Constants.isNullOrEmpty(rfxStatusesSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(rfxStatusesSearch.getEffective_end())) {
			        	rfxStatuses = rfxStatusesRepository.findByEffectivedateBetween(rfxStatusesSearch.getEffectivedate_st(), rfxStatusesSearch.getEffective_end(), pageable);
			        }
			        //only status
			        else if(Constants.isNullOrEmpty(rfxStatusesSearch.getStatus())) {
			        	rfxStatuses = rfxStatusesRepository.findByStatus(rfxStatusesSearch.getStatus(), pageable);
			        }

			        //only code
			        else if(Constants.isNullOrEmpty(rfxStatusesSearch.getCode())) {
			        	rfxStatuses = rfxStatusesRepository.findByCodeContaining(rfxStatusesSearch.getCode(), pageable);
			        }
			        //Only name
			        else if(Constants.isNullOrEmpty(rfxStatusesSearch.getName())) {
			        	rfxStatuses = rfxStatusesRepository.findByNameContaining(rfxStatusesSearch.getName(),pageable);
			        }
			        else {
			        	rfxStatuses = rfxStatusesRepository.findAll(pageable);
			        }
			        RfxStatusesSearchResp rfxTypesSearchResp= new RfxStatusesSearchResp();
			        rfxTypesSearchResp.setRfxStatusesSearch(rfxStatuses);
			        rfxTypesSearchResp.setMessage("Success");
			        rfxTypesSearchResp.setCode(200);
			        return rfxTypesSearchResp;
			    }
		 

		 public void createRfxDocumentType(RfxDocumentTypesCreate rfxDocumentTypesCreate) {
		        String rfx_document_type_id = UUID.randomUUID().toString().trim();
		        TableRfxDocumentTypes tableRfxDocumentTypes = new TableRfxDocumentTypes();
		        tableRfxDocumentTypes.setRfx_document_type_id(rfx_document_type_id);
		        tableRfxDocumentTypes.setName(rfxDocumentTypesCreate.getName());
		        tableRfxDocumentTypes.setDescription(rfxDocumentTypesCreate.getDescription());
		        tableRfxDocumentTypes.setCode(rfxDocumentTypesCreate.getCode());
		        tableRfxDocumentTypes.setEffectivedate(rfxDocumentTypesCreate.getEffectivedate());
		        tableRfxDocumentTypes.setStatus(Constants.STATUS_Y);
		        tableRfxDocumentTypes.setCreatedAt(currentDateTime());
		        tableRfxDocumentTypes.setUpdatedAt(currentDateTime());
		        rfxDocumentTypesRepository.save(tableRfxDocumentTypes);
		    }
		 
		 public boolean editRfxDocumentTypeNameExist( String name) {
		        String document_type_id = rfxDocumentTypesRepository.getIdByRfxDocumentTypeName(name);
		        if ( document_type_id==null)
		            return false;
		        else
		            return true;
		    }
	
		    public boolean rfxDocumentTypeIdExist(String rfx_document_type_id) {
		        String name = rfxDocumentTypesRepository.getNameByRfxDocumentTypeId(rfx_document_type_id);
		        System.out.println("---------name:"+name);
		        if (name == null)
		            return false;
		        else
		            return true;
		    }
		    
		    public String setRfxDocumentTypeStatus(String rfx_document_type_id, String status) {
		    	rfxDocumentTypesRepository.updateStatus(rfx_document_type_id, status);
		        if (status.equalsIgnoreCase(Constants.STATUS_Y))
		            return Constants.RFX_STATUS_ACTIVATED;
		        else
		            return Constants.RFX_STATUS_INACTIVATED;

		    }
		    
		    public int updateRfxDocumentType(RfxDocumentTypesCreate rfxDocumentTypesCreate) {
		        return rfxDocumentTypesRepository.updateRfxDocumentType(rfxDocumentTypesCreate.getRfx_document_type_id(), rfxDocumentTypesCreate.getName(), rfxDocumentTypesCreate.getDescription(),
		        		rfxDocumentTypesCreate.getCode(), rfxDocumentTypesCreate.getEffectivedate(), currentDateTime());
		    }
		    
		    public String rfxDocumentTypeCodeExist( String code) {
		        return rfxDocumentTypesRepository.getRfxDocumentTypeIdByCode(code);
		    }
		    
		   


			 public RfxDocumentTypesSearchResp rfxDocumentTypesSearch(RfxDocumentTypesSearch rfxDocumentTypesSearch) {
			        int limit = rfxDocumentTypesSearch.getLimit();
			        int page = rfxDocumentTypesSearch.getPage_no();
			        Pageable pageable = null;
			        Page<TableRfxDocumentTypes> rfxDocumentTypes = null;

			        if (rfxDocumentTypesSearch.getOrderby() != null && rfxDocumentTypesSearch.getOrderby() != Constants.EMPTY) {
			            if (rfxDocumentTypesSearch.getSortby() != null && rfxDocumentTypesSearch.getSortby() != Constants.EMPTY) {
			                if (rfxDocumentTypesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                    pageable = PageRequest.of(page, limit, Sort.by(rfxDocumentTypesSearch.getOrderby()).ascending());
			                else
			                    pageable = PageRequest.of(page, limit, Sort.by(rfxDocumentTypesSearch.getOrderby()).descending());
			            } else {
			                pageable = PageRequest.of(page, limit, Sort.by(rfxDocumentTypesSearch.getOrderby()).descending());
			            }

			        } else {
			            if (rfxDocumentTypesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
			            else
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
			        }
			        // status name effective code
			        if (Constants.isNullOrEmpty(rfxDocumentTypesSearch.getStatus()) && (Constants.isNullOrEmpty(rfxDocumentTypesSearch.getName())) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getCode())
			                && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getEffectivedate_st())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByStatusAndNameAndCodeAndEffectivedateBetween(
			        			rfxDocumentTypesSearch.getStatus(), rfxDocumentTypesSearch.getName(),rfxDocumentTypesSearch.getCode(), rfxDocumentTypesSearch.getEffectivedate_st(), rfxDocumentTypesSearch.getEffective_end(), pageable);
			        }
			        // status effective
			        else if (Constants.isNullOrEmpty(rfxDocumentTypesSearch.getStatus()) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getEffectivedate_st())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByStatusAndEffectivedateBetween(rfxDocumentTypesSearch.getStatus(), rfxDocumentTypesSearch.getEffectivedate_st(), rfxDocumentTypesSearch.getEffective_end(), pageable);
			        }
			        // code effective
			        else if (Constants.isNullOrEmpty(rfxDocumentTypesSearch.getCode()) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getEffectivedate_st())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByCodeAndEffectivedateBetween(rfxDocumentTypesSearch.getCode(), rfxDocumentTypesSearch.getEffectivedate_st(), rfxDocumentTypesSearch.getEffective_end(), pageable);
			        }
			        //// name effective
			        else if (Constants.isNullOrEmpty(rfxDocumentTypesSearch.getName()) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getEffectivedate_st())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByNameAndEffectivedateBetween(rfxDocumentTypesSearch.getName(), rfxDocumentTypesSearch.getEffectivedate_st(), rfxDocumentTypesSearch.getEffective_end(), pageable);
			        }
			        // name Status code
			        else if (Constants.isNullOrEmpty(rfxDocumentTypesSearch.getName()) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getStatus()) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getCode())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByNameAndStatusAndCode(rfxDocumentTypesSearch.getName(), rfxDocumentTypesSearch.getStatus(),rfxDocumentTypesSearch.getCode(), pageable);
			        }
			        // Only effective
			        else if(Constants.isNullOrEmpty(rfxDocumentTypesSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(rfxDocumentTypesSearch.getEffective_end())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByEffectivedateBetween(rfxDocumentTypesSearch.getEffectivedate_st(), rfxDocumentTypesSearch.getEffective_end(), pageable);
			        }
			        //only status
			        else if(Constants.isNullOrEmpty(rfxDocumentTypesSearch.getStatus())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByStatus(rfxDocumentTypesSearch.getStatus(), pageable);
			        }
			        //Only name
			        else if(Constants.isNullOrEmpty(rfxDocumentTypesSearch.getName())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByNameContaining(rfxDocumentTypesSearch.getName(),pageable);
			        }
			      //Only code
			        else if(Constants.isNullOrEmpty(rfxDocumentTypesSearch.getCode())) {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findByCodeContaining(rfxDocumentTypesSearch.getCode(),pageable);
			        }
			        else {
			        	rfxDocumentTypes = rfxDocumentTypesRepository.findAll(pageable);
			        }
			        RfxDocumentTypesSearchResp rfxTypesSearchResp= new RfxDocumentTypesSearchResp();
			        rfxTypesSearchResp.setRfxDocumentSearch(rfxDocumentTypes);
			        rfxTypesSearchResp.setMessage("Success");
			        rfxTypesSearchResp.setCode(200);
			        return rfxTypesSearchResp;
			    }
			 
			 
		//Proposal Document Types	 
			  public void createProposalDocumentType(ProposalDocumentTypesCreate proposalDocumentTypesCreate) {
			        String proposal_document_type_id = UUID.randomUUID().toString().trim();
			        TableProposalDocumentTypes tableProposalDocumentTypes = new TableProposalDocumentTypes();
			        tableProposalDocumentTypes.setProposalDocumentTypeId(proposal_document_type_id) ; 
			        tableProposalDocumentTypes.setName(proposalDocumentTypesCreate.getName());
			        tableProposalDocumentTypes.setDescription(proposalDocumentTypesCreate.getDescription());
			        tableProposalDocumentTypes.setCode(proposalDocumentTypesCreate.getCode());
			        tableProposalDocumentTypes.setEffectivedate(proposalDocumentTypesCreate.getEffectivedate());
			        tableProposalDocumentTypes.setStatus(Constants.STATUS_Y);
			        tableProposalDocumentTypes.setCreatedAt(currentDateTime());
			        tableProposalDocumentTypes.setUpdatedAt(currentDateTime());
			        proposalDocumentTypesRepository.save(tableProposalDocumentTypes);
			    }
			   
			  public String createProposalStatus(ProposalStatusCreate proposalStatusCreate) {
				  
				  String proposalStatusId = proposalStatusRepository.getIdByProposalStatusId(proposalStatusCreate.getProposalStatusId());
				   if(!Constants.isNullOrEmpty(proposalStatusId))
				  {
					  TableProposalStatus proposalStatus = new TableProposalStatus();
				      addProposalStatus(proposalStatusCreate,proposalStatus,0);
				      return Constants.CREATE_SUCCESS_PRO_STAT;
				  }else {
					  TableProposalStatus proposalStatus = proposalStatusRepository.getInfoProposalStatusId(proposalStatusCreate.getProposalStatusId());
				      addProposalStatus(proposalStatusCreate,proposalStatus,1);
				      return Constants.UPDATED_SUCCESS_PRO_STAT;
				  }
			    }
			  public void addProposalStatus(ProposalStatusCreate proposalStatusCreate ,TableProposalStatus proposalStatus,int code)
			  {
				
			        proposalStatus.setName(proposalStatusCreate.getName());
			        proposalStatus.setDescription(proposalStatusCreate.getDescription());
			        proposalStatus.setCode(proposalStatusCreate.getCode());
			        proposalStatus.setEffectivedate(proposalStatusCreate.getEffectivedate());
			        if(code==0)
			        {
	        	    String proposalStatusId = UUID.randomUUID().toString().trim();
			        proposalStatus.setProposalStatusId(proposalStatusId);
                    proposalStatus.setStatus(Constants.STATUS_Y);
			        proposalStatus.setCreatedAt(currentDateTime());
			        }else {
			        proposalStatus.setUpdatedAt(currentDateTime());
			        }
			        proposalStatusRepository.save(proposalStatus);
				  
			  }
			  
			  
			  
			  
			  
			   public boolean editProposalDocumentTypeNameExist( String name) {
			        String document_type_id = proposalDocumentTypesRepository.getIdByProposalDocumentTypeName(name);
			        if (document_type_id == null)
			            return false;
			        else
			            return true;
			    }
			   public boolean editNameExistByProposalDocumentTypeIdAndName( String proposal_document_type_id,String proposal_document_type_name) {
			        String name = proposalDocumentTypesRepository.getNameByProposalDocumentTypeId(proposal_document_type_id);
			        if (name.equalsIgnoreCase(proposal_document_type_name))
			            return true;
			        else
			            return false;
			    }
			   
			   public boolean editNameExistByProposalStatusIdAndName( String proposalStatusId,String proposalStatusName) {
			        String name = proposalStatusRepository.getNameByProposalStatusId(proposalStatusId);
			        if (name.equalsIgnoreCase(proposalStatusName))
			            return true;
			        else
			            return false;
			    }
			   
			    public boolean proposalDocumentTypeIdExist(String proposal_document_type_id) {
			        String name = proposalDocumentTypesRepository.getNameByProposalDocumentTypeId(proposal_document_type_id);
			        if (name == null)
			            return false;
			        else
			            return true;
			    }
			    public boolean proposalStatusIdExist(String proposal_status_id) {
			        String name = proposalStatusRepository.getNameByProposalStatusId(proposal_status_id);
			        if (name == null)
			            return false;
			        else
			            return true;
			    }
			    
			    public String setProposalDocumentTypeStatus(String proposal_document_type_id, String status) {
			    	proposalDocumentTypesRepository.updateStatus(proposal_document_type_id, status);
			        if (status.equalsIgnoreCase(Constants.STATUS_Y))
			            return Constants.PRO_DOC_TYPE_ACTIVATED;
			        else
			            return Constants.PRO_DOC_TYPE_INACTIVATED;

			    }
			    
			    public String setProposalStatus(String proposal_status_id, String status) {
			    	proposalStatusRepository.updateStatus(proposal_status_id, status);
			        if (status.equalsIgnoreCase(Constants.STATUS_Y))
			            return Constants.PRO_STAT_ACTIVATED;
			        else
			            return Constants.PRO_STAT_INACTIVATED;

			    }
			    
			    public int updateProposalDocumentType(ProposalDocumentTypesCreate proposalDocumentTypesCreate) {
			        return proposalDocumentTypesRepository.updateProposalDocumentType(proposalDocumentTypesCreate.getProposal_document_type_id(), proposalDocumentTypesCreate.getName(), proposalDocumentTypesCreate.getDescription(),
			        		proposalDocumentTypesCreate.getCode(), proposalDocumentTypesCreate.getEffectivedate(), currentDateTime());
			    }
			    
			    public String proposalDocumentTypeCodeExist( String code) {
			        return proposalDocumentTypesRepository.getProposalDocumentIdByCode(code);
			    }
			    public String proposalStatusCodeExist( String code) {
			        return proposalStatusRepository.getProposalStatusIdByCode(code);
			    }
			    
				 public ProposalDocumentTypesSearchResp proposalDocumentTypeSearch(ProposalDocumentTypesSearch proposalDocumentTypesSearch) {
				        int limit = proposalDocumentTypesSearch.getLimit();
				        int page = proposalDocumentTypesSearch.getPage_no();
				        Pageable pageable = null;
				        Page<TableProposalDocumentTypes> proposalDocumentType = null;

				        if (proposalDocumentTypesSearch.getOrderby() != null && proposalDocumentTypesSearch.getOrderby() != Constants.EMPTY) {
				            if (proposalDocumentTypesSearch.getSortby() != null && proposalDocumentTypesSearch.getSortby() != Constants.EMPTY) {
				                if (proposalDocumentTypesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
				                    pageable = PageRequest.of(page, limit, Sort.by(proposalDocumentTypesSearch.getOrderby()).ascending());
				                else
				                    pageable = PageRequest.of(page, limit, Sort.by(proposalDocumentTypesSearch.getOrderby()).descending());
				            } else {
				                pageable = PageRequest.of(page, limit, Sort.by(proposalDocumentTypesSearch.getOrderby()).descending());
				            }

				        } else {
				            if (proposalDocumentTypesSearch.getSortby().equalsIgnoreCase(Constants.ASC))
				                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
				            else
				                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
				        }
				        // status name effective code
				        if (Constants.isNullOrEmpty(proposalDocumentTypesSearch.getStatus()) && (Constants.isNullOrEmpty(proposalDocumentTypesSearch.getName())) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getCode())
				                && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getEffectivedate_st())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByStatusAndNameAndCodeAndEffectivedateBetween(
				            		proposalDocumentTypesSearch.getStatus(), proposalDocumentTypesSearch.getName(),proposalDocumentTypesSearch.getCode(), proposalDocumentTypesSearch.getEffectivedate_st(), proposalDocumentTypesSearch.getEffective_end(), pageable);
				        }
				        // status effective
				        else if (Constants.isNullOrEmpty(proposalDocumentTypesSearch.getStatus()) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getEffectivedate_st())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByStatusAndEffectivedateBetween(proposalDocumentTypesSearch.getStatus(), proposalDocumentTypesSearch.getEffectivedate_st(), proposalDocumentTypesSearch.getEffective_end(), pageable);
				        }
				        //// name effective
				        else if (Constants.isNullOrEmpty(proposalDocumentTypesSearch.getName()) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getEffectivedate_st())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByNameAndEffectivedateBetween(proposalDocumentTypesSearch.getName(), proposalDocumentTypesSearch.getEffectivedate_st(), proposalDocumentTypesSearch.getEffective_end(), pageable);
				        }
				        
				        //// code effective
				        else if (Constants.isNullOrEmpty(proposalDocumentTypesSearch.getCode()) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getEffectivedate_st())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByCodeAndEffectivedateBetween(proposalDocumentTypesSearch.getCode(), proposalDocumentTypesSearch.getEffectivedate_st(), proposalDocumentTypesSearch.getEffective_end(), pageable);
				        }
				        // name Status code
				        else if (Constants.isNullOrEmpty(proposalDocumentTypesSearch.getName()) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getStatus()) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getCode())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByNameAndStatusAndCode(proposalDocumentTypesSearch.getName(), proposalDocumentTypesSearch.getStatus(),proposalDocumentTypesSearch.getCode(), pageable);
				        }
				        // Only effective
				        else if(Constants.isNullOrEmpty(proposalDocumentTypesSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(proposalDocumentTypesSearch.getEffective_end())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByEffectivedateBetween(proposalDocumentTypesSearch.getEffectivedate_st(), proposalDocumentTypesSearch.getEffective_end(), pageable);
				        }
				        //only status
				        else if(Constants.isNullOrEmpty(proposalDocumentTypesSearch.getStatus())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByStatus(proposalDocumentTypesSearch.getStatus(), pageable);
				        }
				        //Only name
				        else if(Constants.isNullOrEmpty(proposalDocumentTypesSearch.getName())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByNameContaining(proposalDocumentTypesSearch.getName(),pageable);
				        }
				        //Only code
				        else if(Constants.isNullOrEmpty(proposalDocumentTypesSearch.getCode())) {
				        	proposalDocumentType = proposalDocumentTypesRepository.findByCodeContaining(proposalDocumentTypesSearch.getCode(),pageable);
				        }
				        else {
				        	proposalDocumentType = proposalDocumentTypesRepository.findAll(pageable);
				        }
				        ProposalDocumentTypesSearchResp proposalDocumentTypesSearchResp= new ProposalDocumentTypesSearchResp();
				        proposalDocumentTypesSearchResp.setProposalDocumentTypes(proposalDocumentType);
				        proposalDocumentTypesSearchResp.setMessage("Success");
				        proposalDocumentTypesSearchResp.setCode(200);
				        return proposalDocumentTypesSearchResp;
				    }
				 
				 
			 //Content Entries
			 public int createContentEntry(ContentEntriesCreate contentEntriesCreate,String rfx_category_id)
			 {
				 TableContentEntries tableContentEntries=new TableContentEntries();
				 String content_entry_id=UUID.randomUUID().toString().trim();
				 tableContentEntries.setContentEntryId(content_entry_id);
				 tableContentEntries.setName(contentEntriesCreate.getName());//Question name
				 tableContentEntries.setRfxCategoryId(rfx_category_id);// category_id
				 tableContentEntries.setKeyword(contentEntriesCreate.getKeyword());//tag / keyword
				 tableContentEntries.setTopic(contentEntriesCreate.getTopic());
				 tableContentEntries.setContent(contentEntriesCreate.getContent()); //answer
				 tableContentEntries.setEffectivedate(contentEntriesCreate.getEffectivedate()); //creation date
				 tableContentEntries.setStatus("Y");
				 tableContentEntries.setCreatedAt(currentDateTime());
				 return contentEntriesRepository.save(tableContentEntries).getId();
				
			 }
			 
			 public boolean editContentEntryIdExist(String content_entry_id)
			 {
				 String content_id=contentEntriesRepository.getIdByContentEntryId(content_entry_id);
				 if(content_id==null)
				 {
					 return false;
				 }else{
					 return true;
				 }
				 
			 }
			 
			 public int updateContentEntry(ContentEntriesCreate contentEntriesCreate,String rfx_category_id)
			 {
				 return contentEntriesRepository.updateContentEntry(contentEntriesCreate.getContent_entry_id(),contentEntriesCreate.getName(),
						 contentEntriesCreate.getKeyword() ,contentEntriesCreate.getTopic() ,contentEntriesCreate.getContent() ,
						 contentEntriesCreate.getEffectivedate() ,rfx_category_id ,currentDateTime() );
				
			 }
			 
			 
			 
			 

			 public ContentEntrySearchResp contentEntrySearch(ContentEntriesSearch contentEntrySearch) {
			        int limit = contentEntrySearch.getLimit();
			        int page = contentEntrySearch.getPage_no();
			        Pageable pageable = null;
			        Page<TableContentEntries> contentEntries = null;

			        if (contentEntrySearch.getOrderby() != null && contentEntrySearch.getOrderby() != Constants.EMPTY) {
			            if (contentEntrySearch.getSortby() != null && contentEntrySearch.getSortby() != Constants.EMPTY) {
			                if (contentEntrySearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                    pageable = PageRequest.of(page, limit, Sort.by(contentEntrySearch.getOrderby()).ascending());
			                else
			                    pageable = PageRequest.of(page, limit, Sort.by(contentEntrySearch.getOrderby()).descending());
			            } else {
			                pageable = PageRequest.of(page, limit, Sort.by(contentEntrySearch.getOrderby()).descending());
			            }

			        } else {
			            if (contentEntrySearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
			            else
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
			        }
			        // name topic keyword rfx_category_id effective
			        if (Constants.isNullOrEmpty(contentEntrySearch.getName()) && (Constants.isNullOrEmpty(contentEntrySearch.getTopic())) && (Constants.isNullOrEmpty(contentEntrySearch.getKeyword()))
			        		 && Constants.isNullOrEmpty(contentEntrySearch.getRfxCategoryId()) && Constants.isNullOrEmpty(contentEntrySearch.getEffectivedate_st())) {
			        	contentEntries = contentEntriesRepository.findByNameContainingAndTopicContainingAndKeywordContainingAndRfxCategoryIdContainingAndEffectivedateBetween(
			        			contentEntrySearch.getName(), contentEntrySearch.getTopic(),contentEntrySearch.getKeyword(),contentEntrySearch.getRfxCategoryId() ,contentEntrySearch.getEffectivedate_st() , contentEntrySearch.getEffective_end(), pageable);
			        }
			        // name effective
			        else if (Constants.isNullOrEmpty(contentEntrySearch.getName()) && Constants.isNullOrEmpty(contentEntrySearch.getEffectivedate_st())) {
			        	contentEntries = contentEntriesRepository.findByNameContainingAndEffectivedateBetween(contentEntrySearch.getName(), contentEntrySearch.getEffectivedate_st(), contentEntrySearch.getEffective_end(), pageable);
			        }
			        else if (Constants.isNullOrEmpty(contentEntrySearch.getName()) && Constants.isNullOrEmpty(contentEntrySearch.getStatus())) {
			        	contentEntries = contentEntriesRepository.findByNameContainingAndStatus(contentEntrySearch.getName(), contentEntrySearch.getStatus(), pageable);
			        }
			       // topic effective
			        else if (Constants.isNullOrEmpty(contentEntrySearch.getTopic()) && Constants.isNullOrEmpty(contentEntrySearch.getEffectivedate_st())) {
			        	contentEntries = contentEntriesRepository.findByTopicContainingAndEffectivedateBetween(contentEntrySearch.getTopic(), contentEntrySearch.getEffectivedate_st(), contentEntrySearch.getEffective_end(), pageable);
			        }
			        //// keyword effective
			        else if (Constants.isNullOrEmpty(contentEntrySearch.getKeyword()) && Constants.isNullOrEmpty(contentEntrySearch.getEffectivedate_st())) {
			        	contentEntries = contentEntriesRepository.findByNameContainingAndEffectivedateBetween(contentEntrySearch.getKeyword(), contentEntrySearch.getEffectivedate_st(), contentEntrySearch.getEffective_end(), pageable);
			        }
			        //// rfx_category_id effective
			        else if (Constants.isNullOrEmpty(contentEntrySearch.getRfxCategoryId()) && Constants.isNullOrEmpty(contentEntrySearch.getEffectivedate_st())) {
			        	contentEntries = contentEntriesRepository.findByRfxCategoryIdContainingAndEffectivedateBetween(contentEntrySearch.getRfxCategoryId(), contentEntrySearch.getEffectivedate_st(), contentEntrySearch.getEffective_end(), pageable);
			        }
			        // name topic keyword rfx_category_id
			        else if (Constants.isNullOrEmpty(contentEntrySearch.getName()) && Constants.isNullOrEmpty(contentEntrySearch.getTopic())  && Constants.isNullOrEmpty(contentEntrySearch.getKeyword())  && Constants.isNullOrEmpty(contentEntrySearch.getRfxCategoryId()) ) {
			        	contentEntries = contentEntriesRepository.findByNameContainingAndTopicContainingAndKeywordContainingAndRfxCategoryIdContaining(contentEntrySearch.getName(), contentEntrySearch.getTopic(),contentEntrySearch.getKeyword(),contentEntrySearch.getRfxCategoryId(), pageable);
			        }
			        // Only effective
			        else if(Constants.isNullOrEmpty(contentEntrySearch.getEffectivedate_st()) && Constants.isNullOrEmpty(contentEntrySearch.getEffective_end())) {
			        	contentEntries = contentEntriesRepository.findByEffectivedateBetween(contentEntrySearch.getEffectivedate_st(), contentEntrySearch.getEffective_end(), pageable);
			        }
			        //only name
			        else if(Constants.isNullOrEmpty(contentEntrySearch.getName())) {
			        	contentEntries = contentEntriesRepository.findByNameContaining(contentEntrySearch.getName(), pageable);
			        }
			      //only topic
			        else if(Constants.isNullOrEmpty(contentEntrySearch.getTopic())) {
			        	contentEntries = contentEntriesRepository.findByTopicContaining(contentEntrySearch.getTopic(), pageable);
			        }
			        //Only keyword
			        else if(Constants.isNullOrEmpty(contentEntrySearch.getKeyword())) {
			        	contentEntries = contentEntriesRepository.findByKeywordContaining(contentEntrySearch.getKeyword(),pageable);
			        }
			        //Only rfx_category_id
			        else if(Constants.isNullOrEmpty(contentEntrySearch.getRfxCategoryId())) {
			        	contentEntries = contentEntriesRepository.findByRfxCategoryIdContaining(contentEntrySearch.getRfxCategoryId(),pageable);
			        }
			        else if(Constants.isNullOrEmpty(contentEntrySearch.getStatus())) {
			        	contentEntries = contentEntriesRepository.findByStatus(contentEntrySearch.getStatus(),pageable);
			        }
			        else {
			        	contentEntries = contentEntriesRepository.findAll(pageable);
			        }
			        ContentEntrySearchResp rfxTypesSearchResp= new ContentEntrySearchResp();
			        rfxTypesSearchResp.setContentEntries(contentEntries);   
			        rfxTypesSearchResp.setMessage("Success");
			        rfxTypesSearchResp.setCode(200);
			        return rfxTypesSearchResp;
			    }
			  
	 

			public boolean checkRfxCategoryIdAndExclusionExistForBidSource(String bid_source, String rfx_category_id,String exclusion)
			{
				String id=bidSourceAndKeywordRepository.getIdByBidSouceAndRfxCategoryIdAndExclusion(bid_source, rfx_category_id,exclusion);
				if(Constants.isNullOrEmpty(id)){
				   return true;
				}else{
				   return false;
				}
			}
			
			public boolean keywordEntryExist(String bid_source, String rfx_category_id,String exclusion,String business_id)
			{
				String id=keywordEntryRepository.keywordEntryExist(bid_source, rfx_category_id,exclusion,business_id);
				if(Constants.isNullOrEmpty(id)){
				   return true;
				}else{
				   return false;
				}
			}
				
            public int updateKeywordEntry( KeywordEntryCreate keywordEntryCreate)
            {
            	return keywordEntryRepository.updateKeywordEntry(keywordEntryCreate.getKeyword_entry_id(),keywordEntryCreate.getBid_source() ,keywordEntryCreate.getRfx_category_id(),keywordEntryCreate.getExclusions() ,keywordEntryCreate.getBusiness_id(),currentDateTime()) ;
            }
				
				
			public boolean keywordEntryIdExist(String keyword_entry_id)
			{
				String id=keywordEntryRepository.getIdByKeywordEntryId(keyword_entry_id);
				if(Constants.isNullOrEmpty(id)){
					   return true;
					}else{
					   return false;
					}
			}
				
			public int createKeywordForBidSource(KeywordEntryCreate keywordEntryCreate)
			{
				TableBidSourceAndKeyword tableBidSourceAndKeyword=new TableBidSourceAndKeyword();
				tableBidSourceAndKeyword.setBidSource(keywordEntryCreate.getBid_source());
				tableBidSourceAndKeyword.setRfxCategoryId(keywordEntryCreate.getRfx_category_id());
				tableBidSourceAndKeyword.setExclusions(keywordEntryCreate.getExclusions());
				tableBidSourceAndKeyword.setCreatedAt(currentDateTime());
				tableBidSourceAndKeyword.setUpdatedAt(currentDateTime());
				logger.info("createKeywordForBidSource..");
				return bidSourceAndKeywordRepository.save(tableBidSourceAndKeyword).getId();
				
			}
				
			public int createKeywordEntry(KeywordEntryCreate keywordEntryCreate)
			{
				String keyword_entry_id=UUID.randomUUID().toString().trim();
				TableKeywordEntries tableKeywordEntries=new TableKeywordEntries();
				tableKeywordEntries.setKeywordEntryId(keyword_entry_id);
				tableKeywordEntries.setBidSource(keywordEntryCreate.getBid_source());
				tableKeywordEntries.setBusinessId(keywordEntryCreate.getBusiness_id());
				tableKeywordEntries.setRfxCategoryId(keywordEntryCreate.getRfx_category_id());
				tableKeywordEntries.setExclusions(keywordEntryCreate.getExclusions());
				tableKeywordEntries.setEffectivedate(currentDateTime());
				tableKeywordEntries.setStatus("Y");
				tableKeywordEntries.setCreatedAt(currentDateTime());
				logger.info("createKeywordEntry..");
				return keywordEntryRepository.save(tableKeywordEntries).getId();
				
			}
			
			 public KeywordEntrySearchResp keywordEntrySearch(KeywordEntrySearch keywordEntrySearch) {
			        int limit = keywordEntrySearch.getLimit();
			        int page = keywordEntrySearch.getPage_no();
			        Pageable pageable = null;
			        Page<TableKeywordEntries> keywordEntries = null;

			        if (keywordEntrySearch.getOrderby() != null && keywordEntrySearch.getOrderby() != Constants.EMPTY) {
			            if (keywordEntrySearch.getSortby() != null && keywordEntrySearch.getSortby() != Constants.EMPTY) {
			                if (keywordEntrySearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                    pageable = PageRequest.of(page, limit, Sort.by(keywordEntrySearch.getOrderby()).ascending());
			                else
			                    pageable = PageRequest.of(page, limit, Sort.by(keywordEntrySearch.getOrderby()).descending());
			            } else {
			                pageable = PageRequest.of(page, limit, Sort.by(keywordEntrySearch.getOrderby()).descending());
			            }

			        } else {
			            if (keywordEntrySearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
			            else
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
			        }
			        // bid_source rfx category exclusions business_id effective
			        if (Constants.isNullOrEmpty(keywordEntrySearch.getBidSource()) && Constants.isNullOrEmpty(keywordEntrySearch.getRfxCategoryId()) && Constants.isNullOrEmpty(keywordEntrySearch.getExclusions()) && Constants.isNullOrEmpty(keywordEntrySearch.getBusinessId())  && Constants.isNullOrEmpty(keywordEntrySearch.getEffectivedate_st())) {
			        	keywordEntries = keywordEntryRepository.findByBidSourceAndRfxCategoryIdAndExclusionsAndBusinessIdAndEffectivedateBetween(keywordEntrySearch.getBidSource(), keywordEntrySearch.getRfxCategoryId(), keywordEntrySearch.getExclusions(), keywordEntrySearch.getBusinessId(), keywordEntrySearch.getEffectivedate_st(), keywordEntrySearch.getEffective_end(), pageable);
			        }
			        // bid_source effective
			        else if (Constants.isNullOrEmpty(keywordEntrySearch.getBidSource()) && Constants.isNullOrEmpty(keywordEntrySearch.getEffectivedate_st())) {
			        	keywordEntries = keywordEntryRepository.findByBidSourceAndEffectivedateBetween(keywordEntrySearch.getBidSource(), keywordEntrySearch.getEffectivedate_st(), keywordEntrySearch.getEffective_end(), pageable);
			        }
			       // rfx category effective
			        else if (Constants.isNullOrEmpty(keywordEntrySearch.getRfxCategoryId()) && Constants.isNullOrEmpty(keywordEntrySearch.getEffectivedate_st())) {
			        	keywordEntries = keywordEntryRepository.findByRfxCategoryIdAndEffectivedateBetween(keywordEntrySearch.getRfxCategoryId(), keywordEntrySearch.getEffectivedate_st(), keywordEntrySearch.getEffective_end(), pageable);
			        }
			        // exclusions effective
			        else if (Constants.isNullOrEmpty(keywordEntrySearch.getExclusions()) && Constants.isNullOrEmpty(keywordEntrySearch.getEffectivedate_st())) {
			        	keywordEntries = keywordEntryRepository.findByExclusionsAndEffectivedateBetween(keywordEntrySearch.getExclusions(), keywordEntrySearch.getEffectivedate_st(), keywordEntrySearch.getEffective_end(), pageable);
			        }
			        //business_id effective
			        else if (Constants.isNullOrEmpty(keywordEntrySearch.getBusinessId()) && Constants.isNullOrEmpty(keywordEntrySearch.getEffectivedate_st())) {
			        	keywordEntries = keywordEntryRepository.findByBusinessIdAndEffectivedateBetween(keywordEntrySearch.getBusinessId(), keywordEntrySearch.getEffectivedate_st(), keywordEntrySearch.getEffective_end(), pageable);
			        }
			        // bid_source rfx category id exclusions business_id 
			        else if (Constants.isNullOrEmpty(keywordEntrySearch.getBidSource()) && Constants.isNullOrEmpty(keywordEntrySearch.getRfxCategoryId()) && Constants.isNullOrEmpty(keywordEntrySearch.getExclusions()) && Constants.isNullOrEmpty(keywordEntrySearch.getBusinessId()) ) {
			        	keywordEntries = keywordEntryRepository.findByBidSourceAndRfxCategoryIdAndExclusionsAndBusinessId(keywordEntrySearch.getBidSource(), keywordEntrySearch.getRfxCategoryId(), keywordEntrySearch.getExclusions(), keywordEntrySearch.getBusinessId(), pageable);
			        }
			        // Only effective
			        else if(Constants.isNullOrEmpty(keywordEntrySearch.getEffectivedate_st()) && Constants.isNullOrEmpty(keywordEntrySearch.getEffective_end())) {
			        	keywordEntries = keywordEntryRepository.findByEffectivedateBetween(keywordEntrySearch.getEffectivedate_st(), keywordEntrySearch.getEffective_end(), pageable);
			        }
			        //only bid_source
			        else if(Constants.isNullOrEmpty(keywordEntrySearch.getBidSource())) {
			        	keywordEntries = keywordEntryRepository.findByBidSourceContaining(keywordEntrySearch.getBidSource(), pageable);
			        }
			        //only exclusions
			        else if(Constants.isNullOrEmpty(keywordEntrySearch.getExclusions())) {
			        	keywordEntries = keywordEntryRepository.findByExclusionsContaining(keywordEntrySearch.getExclusions(), pageable);
			        			
			        	
			        }
			        //Only rfx category id
			        else if(Constants.isNullOrEmpty(keywordEntrySearch.getRfxCategoryId())) {
			        	keywordEntries = keywordEntryRepository.findByRfxCategoryIdContaining(keywordEntrySearch.getRfxCategoryId(),pageable);
			        }
			        else if(Constants.isNullOrEmpty(keywordEntrySearch.getStatus())) {
			        	keywordEntries = keywordEntryRepository.findByStatus(keywordEntrySearch.getStatus(),pageable);
			        }
			        
			        //Only business_id
			        else if(Constants.isNullOrEmpty(keywordEntrySearch.getBusinessId())) {
			        	keywordEntries = keywordEntryRepository.findByBusinessIdContaining(keywordEntrySearch.getBusinessId(),pageable);
			        }
			        else {
			        	keywordEntries = keywordEntryRepository.findAll(pageable);
			        }
			        KeywordEntrySearchResp keywordEntrySearchResp= new KeywordEntrySearchResp();
			        keywordEntrySearchResp.setKeywordEntries(keywordEntries);
			        keywordEntrySearchResp.setMessage("Success");
			        keywordEntrySearchResp.setCode(200);
			        return keywordEntrySearchResp;
			    }
			
			 
			  public ArrayList<BidSourceAndRfxCategoriesResp> getBidSourceAndRfxCategoriesList()
			   {
				   
				  ArrayList<BidSourceAndRfxCategoriesResp> bidSourceAndRfxCategoriesRespList=new   ArrayList<BidSourceAndRfxCategoriesResp>();
				   ArrayList<String>  bidSourceList=(ArrayList<String>) keywordEntryRepository.getDistinctBidSource();
				  for(String bidSource:bidSourceList)
				   {
					  BidSourceAndRfxCategoriesResp bidSourceAndRfxCategoriesResp=new BidSourceAndRfxCategoriesResp();
					  bidSourceAndRfxCategoriesResp.setBidSource(bidSource);
					  System.out.println("bidSource -->"+bidSource);
					  ArrayList<String> categories=(ArrayList<String>) keywordEntryRepository.getDistinctRfxCatrgyByBidSource(bidSource);
					  bidSourceAndRfxCategoriesResp.setRfxCategories(categories);
					  bidSourceAndRfxCategoriesRespList.add(bidSourceAndRfxCategoriesResp);
				   }
				   
				   return bidSourceAndRfxCategoriesRespList;
			   }


			 public boolean documentTitleExist(String document_title)
			 {
				 
				String id= documentEntriesRepository.getIdByTitle(document_title);
				if(Constants.isNullOrEmpty(id)){
					   return true;
					}else{
					   return false;
					}
			 }
			 public boolean documentEntryIdExist(String document_entry_id)
			 { 
				String id= documentEntriesRepository.getIdByDocumentEntryId(document_entry_id);
				if(Constants.isNullOrEmpty(id)){ //NOT NULL TRUE
					   return true;
					}else{
					   return false;
					}
			 }
			 
			 
			   public boolean editDocumentTitleExistByDocumentEntryId( String document_entry_id,String document_title) {
			        String doc_title = documentEntriesRepository.getTitleByDocumentEntryId(document_entry_id);
			        if (doc_title.equalsIgnoreCase(document_title))
			            return true;
			        else
			            return false;
			    }
			 
			   public String saveDocumentEntry(String rfx_document_type_id,String documentTitle,String rfx_type_id,String rfx_category_id,String clientName,MultipartFile uploadFile ) {
			        String document_entry_id_result = "";
			        TableDocumentEntries tableDocumentEntries=new TableDocumentEntries();
			        try {
			        	String document_entry_id=UUID.randomUUID().toString().trim();
			        	String fileName = StringUtils.cleanPath(uploadFile.getOriginalFilename());
			        	Path path = Paths.get(uploadingDir+documentTitle+"_"+fileName);
			        	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			        			.path("rfx/document-entries/")
			        			.path(fileName)
			        			.toUriString();
			        	try {
			        		Files.copy(uploadFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			        	} catch (IOException e) {
			        		e.printStackTrace();
			        	}
			            tableDocumentEntries.setDocumentEntryId(document_entry_id);
			            tableDocumentEntries.setRfxDocumentTypeId(rfx_document_type_id);
			            tableDocumentEntries.setTitle(documentTitle);
			            tableDocumentEntries.setRfxTypeId(rfx_type_id);
			            tableDocumentEntries.setRfxCategoryId(rfx_category_id);
			            tableDocumentEntries.setClientname(clientName);
			            tableDocumentEntries.setAttachmentUrl(fileDownloadUri);
			            tableDocumentEntries.setStatus("Y");
			            tableDocumentEntries.setUpdatedAt(currentDateTime());
			            tableDocumentEntries.setCreatedAt(currentDateTime());
			            document_entry_id_result= documentEntriesRepository.save(tableDocumentEntries).getDocumentEntryId();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        
			        return document_entry_id_result;
			       
			    }
			   
			   public String updateDocumentEntry(String document_entry_id, String rfx_document_type_id,String documentTitle,String rfx_type_id,String rfx_category_id,String clientName,MultipartFile uploadFile ) {
			        String document_entry_id_result = "";
			        TableDocumentEntries tableDocumentEntries=documentEntriesRepository.findByDocumentEntryId(document_entry_id);
			        try {
			        	
			        	String fileName = StringUtils.cleanPath(uploadFile.getOriginalFilename());
			        	Path path = Paths.get(uploadingDir+documentTitle+"_"+fileName);
			        	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			        			.path("rfx/document-entries/")
			        			.path(fileName)
			        			.toUriString();
			        	try {
			        		Files.copy(uploadFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			        	} catch (IOException e) {
			        		e.printStackTrace();
			        	}
			            tableDocumentEntries.setRfxDocumentTypeId(rfx_document_type_id);
			            tableDocumentEntries.setTitle(documentTitle);
			            tableDocumentEntries.setRfxTypeId(rfx_type_id);
			            tableDocumentEntries.setRfxCategoryId(rfx_category_id);
			            tableDocumentEntries.setClientname(clientName);
			            tableDocumentEntries.setAttachmentUrl(fileDownloadUri);
			            tableDocumentEntries.setUpdatedAt(currentDateTime());
			            document_entry_id_result= documentEntriesRepository.save(tableDocumentEntries).getDocumentEntryId();
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        
			        return document_entry_id_result;
			       
			    }
			   
			   public DocumentEntrySearchResp documentEntrySearch(DocumentEntrySearch documentEntrySearch) {
			        int limit = documentEntrySearch.getLimit();
			        int page = documentEntrySearch.getPage_no();
			        Pageable pageable = null;
			        Page<TableDocumentEntries> documentEntries = null;

			        if (documentEntrySearch.getOrderby() != null && documentEntrySearch.getOrderby() != Constants.EMPTY) {
			            if (documentEntrySearch.getSortby() != null && documentEntrySearch.getSortby() != Constants.EMPTY) {
			                if (documentEntrySearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                    pageable = PageRequest.of(page, limit, Sort.by(documentEntrySearch.getOrderby()).ascending());
			                else
			                    pageable = PageRequest.of(page, limit, Sort.by(documentEntrySearch.getOrderby()).descending());
			            } else {
			                pageable = PageRequest.of(page, limit, Sort.by(documentEntrySearch.getOrderby()).descending());
			            }

			        } else {
			            if (documentEntrySearch.getSortby().equalsIgnoreCase(Constants.ASC))
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDAT_DOCUMENT_ENTRY).ascending());
			            else
			                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDAT_DOCUMENT_ENTRY).descending());
			        }
			        
			      
			     
			        System.out.println(documentEntrySearch.getTitle()+" "+documentEntrySearch.getClientname()+" "+documentEntrySearch.getRfxDocumentTypeId());
			        
			       //document_title  rfx_type_id client_name   status
			        if (Constants.isNullOrEmpty(documentEntrySearch.getTitle())  && Constants.isNullOrEmpty(documentEntrySearch.getClientname())  && Constants.isNullOrEmpty(documentEntrySearch.getRfxDocumentTypeId())) {
			        	documentEntries = documentEntriesRepository.findByRfxDocumentTypeIdContainingAndTitleContainingAndClientnameContainingAndStatus(documentEntrySearch.getRfxDocumentTypeId(),documentEntrySearch.getTitle(), documentEntrySearch.getClientname(),documentEntrySearch.getStatus(), pageable);
			        }
			      //only document_title
			         else if(Constants.isNullOrEmpty(documentEntrySearch.getTitle())) {
			     	 documentEntries = documentEntriesRepository.findByTitleContaining(documentEntrySearch.getTitle(), pageable);
			         }
			         else if(Constants.isNullOrEmpty(documentEntrySearch.getRfxDocumentTypeId()) && Constants.isNullOrEmpty(documentEntrySearch.getTitle()) && Constants.isNullOrEmpty(documentEntrySearch.getStatus()) ) {
			        	documentEntriesRepository.findByRfxDocumentTypeIdContainingAndTitleContainingAndStatus(documentEntrySearch.getRfxDocumentTypeId(), documentEntrySearch.getTitle(), documentEntrySearch.getStatus(), pageable);
				     }
			         else if(Constants.isNullOrEmpty(documentEntrySearch.getRfxDocumentTypeId())  && Constants.isNullOrEmpty(documentEntrySearch.getStatus()) ) {
			        	documentEntriesRepository.findByRfxDocumentTypeIdContainingAndStatus(documentEntrySearch.getRfxDocumentTypeId(), documentEntrySearch.getStatus(), pageable);
				     }
			         else if(Constants.isNullOrEmpty(documentEntrySearch.getTitle())  && Constants.isNullOrEmpty(documentEntrySearch.getStatus()) ) {
				        	documentEntriesRepository.findByTitleContainingAndStatus(documentEntrySearch.getTitle(), documentEntrySearch.getStatus(), pageable);
					 }
			         else if(Constants.isNullOrEmpty(documentEntrySearch.getClientname())  && Constants.isNullOrEmpty(documentEntrySearch.getStatus()) ) {
				        	documentEntriesRepository.findByClientnameContainingAndStatus(documentEntrySearch.getClientname(), documentEntrySearch.getStatus(), pageable);
					 }
			        //Only client_name
			         else if(Constants.isNullOrEmpty(documentEntrySearch.getClientname())) {
			        	documentEntries = documentEntriesRepository.findByClientnameContaining(documentEntrySearch.getClientname(), pageable);
			        }
			        //Only rfx_document_type_id
			        else if(Constants.isNullOrEmpty(documentEntrySearch.getRfxDocumentTypeId())) {
			        	documentEntries = documentEntriesRepository.findByRfxDocumentTypeIdContaining(documentEntrySearch.getRfxDocumentTypeId(), pageable);
			        }
			        else if(Constants.isNullOrEmpty(documentEntrySearch.getStatus()))
			        {
			        	documentEntries = documentEntriesRepository.findByStatus(documentEntrySearch.getStatus(), pageable);
			        }
			        else {
			        	documentEntries = documentEntriesRepository.findAll(pageable);
			        }
			        DocumentEntrySearchResp documentEntrySearchResp= new DocumentEntrySearchResp();
			        documentEntrySearchResp.setDocumentEntries(documentEntries);
			        documentEntrySearchResp.setMessage("Success");
			        documentEntrySearchResp.setCode(200);
			        return documentEntrySearchResp;
			    }
			   
			   public int addCompanyDetails(CompanyDetailsCreate companyDetailsCreate)
			   {
				   int companyDetailsId=Integer.valueOf(companyDetailsCreate.getCompanyDetailsId());
				   if(!companyDetailsIdExist(companyDetailsId))
				   {
					   TableCompanyDetails tableCompanyDetails=new TableCompanyDetails();
					    createCompanyDetails(companyDetailsCreate, tableCompanyDetails, 0);
					    return 0;
					   
				   }else {
					   TableCompanyDetails tableCompanyDetails=companyDetailsRepository.findById(companyDetailsCreate.getCompanyDetailsId()).get();
					    createCompanyDetails(companyDetailsCreate, tableCompanyDetails, 1);
					    return 1;
				   }
			   }
			   
			   public boolean companyDetailsIdExist(int companyDetailsId)
				 { 
					return companyDetailsRepository.existsById(companyDetailsId);
					
				 }

			    public String setCompanyDetailsStatus(String companyDeatialId, String status) {
			    	companyDetailsRepository.updateStatus(Integer.parseInt(companyDeatialId), status);
			    	logger.info("Company details status changed");
			        if (status.equalsIgnoreCase(Constants.STATUS_Y))
			            return Constants.CMPD_ACTIVATED;
			        else
			            return Constants.CMPD_INACTIVATED;

			    }

			  public int createCompanyDetails(CompanyDetailsCreate companyDetailsCreate,TableCompanyDetails tableCompanyDetails,int code)
			   {
				   tableCompanyDetails.setName(companyDetailsCreate.getName());
				   tableCompanyDetails.setNameDesc(companyDetailsCreate.getNameDesc());
				   tableCompanyDetails.setWebsiteUrl(companyDetailsCreate.getWebsiteUrl());
				   tableCompanyDetails.setWebsiteUrlDesc(companyDetailsCreate.getWebsiteUrlDesc());
				   tableCompanyDetails.setContact(companyDetailsCreate.getContact());
				   tableCompanyDetails.setContactDesc(companyDetailsCreate.getContactDesc());
				   tableCompanyDetails.setLegalStruct(companyDetailsCreate.getLegalStruct());
				   tableCompanyDetails.setLegalStructDesc(companyDetailsCreate.getLegalStructDesc());
				   tableCompanyDetails.setBackground(companyDetailsCreate.getBackground());
				   tableCompanyDetails.setBackgroundDesc(companyDetailsCreate.getBackgroundDesc());
				   tableCompanyDetails.setHistory(companyDetailsCreate.getHistory());
				   tableCompanyDetails.setHistoryDesc(companyDetailsCreate.getHistoryDesc());
				   tableCompanyDetails.setRevenueGrowth(companyDetailsCreate.getRevenueGrowth());
				   tableCompanyDetails.setRevenueGrowthDesc(companyDetailsCreate.getRevenueGrowthDesc());
				   tableCompanyDetails.setKeyStaffProfile(companyDetailsCreate.getKeyStaffProfile());
				   tableCompanyDetails.setKeyStaffProfileDesc(companyDetailsCreate.getKeyStaffProfileDesc());
				   tableCompanyDetails.setHeadQuaterAddress(companyDetailsCreate.getHeadQuaterAddress());
				   tableCompanyDetails.setHeadQuaterAddressDesc(companyDetailsCreate.getHeadQuaterAddressDesc());
				   tableCompanyDetails.setCapabilityReferences(companyDetailsCreate.getCapabilityReferences());
				   tableCompanyDetails.setCapabilityReferencesDesc(companyDetailsCreate.getCapabilityReferencesDesc());
				   tableCompanyDetails.setCompanyClients(companyDetailsCreate.getCompanyClients());
				   tableCompanyDetails.setCompanyClientsDesc(companyDetailsCreate.getCompanyClientsDesc());
				   if(code==0)
				   {
					   tableCompanyDetails.setCreatedAt(currentDateTime());
					   logger.info("Company details created");
				   }else
				   {
					   tableCompanyDetails.setUpdatedAt(currentDateTime());
					   logger.info("Company details updated");
					   
				   }
				   return companyDetailsRepository.save(tableCompanyDetails).getId();
				   
			   }
				 //companydetails search
				 public CompanyDetailsSearchResp companyDetailsSearch(CompanyDetailsSearch companyDetailsSearch)
				 {
					    int limit = companyDetailsSearch.getLimit();
				        int page = companyDetailsSearch.getPage_no();
				        Pageable pageable = null;
				        Page<TableCompanyDetails> companyDetails = null;
				        if (companyDetailsSearch.getOrderby() != null && companyDetailsSearch.getOrderby() != Constants.EMPTY) {
				            if (companyDetailsSearch.getSortby() != null && companyDetailsSearch.getSortby() != Constants.EMPTY) {
				                if (companyDetailsSearch.getSortby().equalsIgnoreCase(Constants.ASC))
				                    pageable = PageRequest.of(page, limit, Sort.by(companyDetailsSearch.getOrderby()).ascending());
				                else
				                    pageable = PageRequest.of(page, limit, Sort.by(companyDetailsSearch.getOrderby()).descending());
				            } else {
				                pageable = PageRequest.of(page, limit, Sort.by(companyDetailsSearch.getOrderby()).descending());
				            }

				        } else {
				            if (companyDetailsSearch.getSortby().equalsIgnoreCase(Constants.ASC))
				                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
				            else
				                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
				        }
				        
				    	
				        if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getHistory()) && Constants.isNullOrEmpty(companyDetailsSearch.getRevenueGrowth() )  && Constants.isNullOrEmpty(companyDetailsSearch.getKeyStaffProfile()) && Constants.isNullOrEmpty(companyDetailsSearch.getHeadQuaterAddress()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getCapabilityReferences()) && Constants.isNullOrEmpty(companyDetailsSearch.getCompanyClients())
				        		){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContainingAndHeadQuaterAddressContainingAndCapabilityReferencesContainingAndCompanyClientsContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(), 
				        			companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground(), companyDetailsSearch.getHistory(), companyDetailsSearch.getRevenueGrowth()  , companyDetailsSearch.getKeyStaffProfile(), companyDetailsSearch.getHeadQuaterAddress(), companyDetailsSearch.getCapabilityReferences() , companyDetailsSearch.getCompanyClients(),pageable);
					    	
				        	
				        }                                                                                                                                                                                                                                           
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getHistory()) && Constants.isNullOrEmpty(companyDetailsSearch.getRevenueGrowth() )  && Constants.isNullOrEmpty(companyDetailsSearch.getKeyStaffProfile()) && Constants.isNullOrEmpty(companyDetailsSearch.getHeadQuaterAddress()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getCapabilityReferences())
				        		){
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContainingAndHeadQuaterAddressContainingAndCapabilityReferencesContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(), 
				        			companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground(), companyDetailsSearch.getHistory(), companyDetailsSearch.getRevenueGrowth()  , companyDetailsSearch.getKeyStaffProfile(), companyDetailsSearch.getHeadQuaterAddress(), companyDetailsSearch.getCapabilityReferences(),pageable);

				        }
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getHistory()) && Constants.isNullOrEmpty(companyDetailsSearch.getRevenueGrowth() )  && Constants.isNullOrEmpty(companyDetailsSearch.getKeyStaffProfile()) && Constants.isNullOrEmpty(companyDetailsSearch.getHeadQuaterAddress()) 
				        		){
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContainingAndHeadQuaterAddressContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(), 
				        			companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground(), companyDetailsSearch.getHistory(), companyDetailsSearch.getRevenueGrowth()  , companyDetailsSearch.getKeyStaffProfile(), companyDetailsSearch.getHeadQuaterAddress(),pageable);
					    	
				        }
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getHistory()) && Constants.isNullOrEmpty(companyDetailsSearch.getRevenueGrowth() )  && Constants.isNullOrEmpty(companyDetailsSearch.getKeyStaffProfile())) 
				        		{
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(), 
				        			companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground(), companyDetailsSearch.getHistory(), companyDetailsSearch.getRevenueGrowth()  , companyDetailsSearch.getKeyStaffProfile(),pageable);
					    	
				        }
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getHistory()) && Constants.isNullOrEmpty(companyDetailsSearch.getRevenueGrowth())) 
				        		{
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(), 
				        			companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground(), companyDetailsSearch.getHistory(), companyDetailsSearch.getRevenueGrowth()  ,pageable);
					    	
				        }
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) 
				        		&& Constants.isNullOrEmpty(companyDetailsSearch.getHistory())) 
				        		{
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(), 
				        			companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground(), companyDetailsSearch.getHistory() ,pageable);
					    }
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) && Constants.isNullOrEmpty(companyDetailsSearch.getBackground())){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(),companyDetailsSearch.getLegalStruct(), companyDetailsSearch.getBackground() ,pageable);
					    } 
				        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())&&	Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct())){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(),companyDetailsSearch.getLegalStruct() ,pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) && Constants.isNullOrEmpty(companyDetailsSearch.getContact())){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContainingAndContactContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(), companyDetailsSearch.getContact(),pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContainingAndWebsiteUrlContaining(companyDetailsSearch.getName(), companyDetailsSearch.getWebsiteUrl(),pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) && Constants.isNullOrEmpty(companyDetailsSearch.getStatus())){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContainingAndStatus(companyDetailsSearch.getName(),companyDetailsSearch.getStatus(), pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getName()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByNameContaining(companyDetailsSearch.getName(),pageable);
					    }
					    else if(Constants.isNullOrEmpty(companyDetailsSearch.getWebsiteUrl()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByWebsiteUrlContaining(companyDetailsSearch.getWebsiteUrl(),pageable);
					    }
					    else if(Constants.isNullOrEmpty(companyDetailsSearch.getContact()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByContactContaining(companyDetailsSearch.getContact(),pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getLegalStruct()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByLegalStructContaining(companyDetailsSearch.getLegalStruct(),pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getBackground()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByBackgroundContaining(companyDetailsSearch.getBackground(),pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getHistory()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByHistoryContaining(companyDetailsSearch.getHistory(),pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getRevenueGrowth()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByRevenueGrowthContaining(companyDetailsSearch.getRevenueGrowth() ,pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getKeyStaffProfile()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByKeyStaffProfileContaining(companyDetailsSearch.getKeyStaffProfile() ,pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getHeadQuaterAddress()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByHeadQuaterAddressContaining(companyDetailsSearch.getHeadQuaterAddress() ,pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getCapabilityReferences()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByCapabilityReferencesContaining(companyDetailsSearch.getCapabilityReferences() ,pageable);
					    }
                        else if(Constants.isNullOrEmpty(companyDetailsSearch.getCompanyClients()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByCompanyClientsContaining(companyDetailsSearch.getCompanyClients() ,pageable);
					    }
                       else if(Constants.isNullOrEmpty(companyDetailsSearch.getStatus()) ){
				        	
				        	companyDetails=companyDetailsRepository.findByStatusContaining(companyDetailsSearch.getStatus() ,pageable);
					    }
                        else
					    {
					    	companyDetails=companyDetailsRepository.findAll(pageable);
					    }
				     
				        	CompanyDetailsSearchResp companyDetailsSearchResp=new CompanyDetailsSearchResp();  
				        	companyDetailsSearchResp.setCompanyDetails(companyDetails);
				        	companyDetailsSearchResp.setMessage("Success");
				        	companyDetailsSearchResp.setCode(200);
					 return companyDetailsSearchResp;
				 }
				 
			//modules
			public int addProductsModules(ProductModuleCreate productModuleCreate)
			{
				//adding new fields

				if(null != productModuleCreate.getIsNewField() && productModuleCreate.getIsNewField())//new field is not, if not insert
				{
					TableProductFieldTypes tableCompanyFields=new TableProductFieldTypes();
					addNewProductFields(tableCompanyFields,productModuleCreate,0);
				}
				
				boolean productModuleId=productModuleRepository.existsById(productModuleCreate.getProductModuleId());
				if( !productModuleId)//if product module is not exist insert
				{
			        TableProductModules tableProductModules=new TableProductModules();
			        addProductModule(productModuleCreate, tableProductModules, 0);
					return 0;
				}else {
					TableProductModules tableProductModules=productModuleRepository.findById(productModuleCreate.getProductModuleId()).get();
					addProductModule(productModuleCreate, tableProductModules, 1);
					return 1;
					
				}
			}
			
			public int addServiceModules(ServiceModuleCreate serviceModuleCreate)
			{
				//adding new fields
				if(serviceModuleCreate.getIsNewField())//new field is not, if not insert
				{
					TableServiceFieldTypes serviceFieldTypes=new TableServiceFieldTypes();
					addNewServiceFields(serviceFieldTypes,serviceModuleCreate,0);
				}
				boolean serviceModuleIdExits=serviceModuleRepository.existsById(serviceModuleCreate.getServiceModuleId());
				if( !serviceModuleIdExits)//if product module is not exist insert
				{
					TableServiceModules tableServiceModules=new TableServiceModules();
			        addServiceModule(serviceModuleCreate, tableServiceModules, 0);
					return 0;
				}else {
					TableServiceModules tableServiceModules=serviceModuleRepository.findById(serviceModuleCreate.getServiceModuleId()).get();
					 addServiceModule(serviceModuleCreate, tableServiceModules, 1);
					return 1;
				}
			}
			
			public void addServiceModule(ServiceModuleCreate serviceModuleCreate,TableServiceModules tableServiceModules,int code)
			{
				tableServiceModules.setServiceId(serviceModuleCreate.getServiceId());
				tableServiceModules.setServiceFieldType(serviceModuleCreate.getServiceFieldType());
				tableServiceModules.setFieldName(serviceModuleCreate.getFieldName());
				tableServiceModules.setFieldData(serviceModuleCreate.getFieldData());
				tableServiceModules.setFieldDescription(serviceModuleCreate.getFieldDescription());
				
				if(code==0)
				{
				tableServiceModules.setStatus("Y");
				logger.info("Adding Service Module..");
				tableServiceModules.setCreatedAt(currentDateTime());
				}else {
				tableServiceModules.setUpdatedAt(currentDateTime());
				logger.info("Updating Service Module ..");
				}
				serviceModuleRepository.save(tableServiceModules);

			}
			public int productModuleDeleted(int productModuleId){
				boolean productModuleExits=productModuleRepository.existsById(productModuleId);
				if(productModuleExits){
					productModuleRepository.deleteById(productModuleId);
					return 1;
				}else{
					return 0;
				}
			}

			public void addProductModule(ProductModuleCreate productModuleCreate,TableProductModules tableProductModules,int code)
			{
				
				tableProductModules.setProductId(productModuleCreate.getProductId());
				tableProductModules.setProductFieldType(productModuleCreate.getProductFieldType());
				tableProductModules.setFieldName(productModuleCreate.getFieldName());
				tableProductModules.setFieldData(productModuleCreate.getFieldData());
				tableProductModules.setFieldDescription(productModuleCreate.getFieldDescription());
				
				if(code==0)
				{
				tableProductModules.setStatus("Y");
				logger.info("Adding Product Module..");
				tableProductModules.setCreatedAt(currentDateTime());
				}else {
					tableProductModules.setUpdatedAt(currentDateTime());
				logger.info("Updating Product Module..");
				}
				productModuleRepository.save(tableProductModules);
				
			}
			
			public int deleteCompanyProduct(int productId){
				boolean productExits=companyProductsRepo.existsById(productId);
				if(productExits){
					companyProductsRepo.deleteById(productId);
					//deleted product module
					productModuleRepository.deleteProductModulesByProductId(productId);
					return 1;
				}else{
					return 0;
				}
			}
				 
			
			public int addCompanyProducts(CompanyProductsCreate companyProductsCreate)
			{
				boolean productExits=companyProductsRepo.existsById(companyProductsCreate.getProductId());
				if(!productExits)//if product not exist insert
				{
			        TableCompanyProducts tableCompanyProducts=new TableCompanyProducts();
					addProducts(companyProductsCreate, tableCompanyProducts, 0);
					return 0;
				}else {
					TableCompanyProducts tableCompanyProducts=companyProductsRepo.findById(companyProductsCreate.getProductId()).get();
					addProducts(companyProductsCreate, tableCompanyProducts, 1);
					return 1;
					
				}
			}
			
			
			//services
			public int addCompanyServices(CompanyServicesCreate companyServicesCreate)
			{
				boolean serviceExits=companyServiceRepo.existsById(companyServicesCreate.getServiceId());
				if(!serviceExits)//if service not exist ,insert
				{
			        TableCompanyServices companyServices=new TableCompanyServices();
					addServices(companyServicesCreate, companyServices, 0);
					return 0;
				}else {
					TableCompanyServices companyServices=companyServiceRepo.findById(companyServicesCreate.getServiceId()).get();
					addServices(companyServicesCreate, companyServices, 1);
					return 1;
				}
			}
			
			
		
		 public void addNewProductFields(TableProductFieldTypes tableProductFieldTypes,ProductModuleCreate productModuleCreate,int code)
		 {
			   
			 tableProductFieldTypes.setProductFieldType(productModuleCreate.getProductFieldType());
			 tableProductFieldTypes.setFieldDescription(productModuleCreate.getFieldDescription());
			
				if(code==0)
				{
					 tableProductFieldTypes.setStatus("Y");
					tableProductFieldTypes.setCreatedAt(currentDateTime());
				}else {
					tableProductFieldTypes.setUpdatedAt(currentDateTime());
				}
				companyFieldsRepository.save(tableProductFieldTypes);
				logger.info("Adding New Product Module Fields..");
		 }
		 
		 public void addNewServiceFields(TableServiceFieldTypes serviceFieldTypes,ServiceModuleCreate serviceModuleCreate,int code)
		 {
			   
			 serviceFieldTypes.setServiceFieldType(serviceModuleCreate.getServiceFieldType());
			 serviceFieldTypes.setFieldDescription(serviceModuleCreate.getFieldDescription());
			
				if(code==0)
				{
					 serviceFieldTypes.setStatus("Y");
					serviceFieldTypes.setCreatedAt(currentDateTime());
				}else {
					serviceFieldTypes.setUpdatedAt(currentDateTime());
				}
				companyServiceFieldsRepo.save(serviceFieldTypes);
				logger.info("Adding New Service Module Fields..");
		 }
		 
		//process
			public int addCompanyProcess(CompanyProcessCreate processCreate)
			{
				Boolean processIDExist=processRepository.existsById(processCreate.getProcessId());
				if(!processIDExist)
				{
					TableCompanyProcess companyProcess=new TableCompanyProcess();
					addProcess(processCreate,companyProcess,0);
					return 0;
				}else {
					TableCompanyProcess companyProcess=processRepository.findById(processCreate.getProcessId()).get();
					addProcess(processCreate,companyProcess,1);
					return 1;
				}
			
			}
			//methodology
			public int addCompanyMethodology(CompanyMethodologyCreate methodologyCreate)
			{
				Boolean methodologyIDExist=methodologyRepo.existsById(methodologyCreate.getMethodologyId());
				if(!methodologyIDExist)
				{
					TableCompanyMethodology companyMethodology=new TableCompanyMethodology();
					addMethodology(methodologyCreate,companyMethodology,0);
					return 0;
				}else {
					
					TableCompanyMethodology companyMethodology=methodologyRepo.findById(methodologyCreate.getMethodologyId()).get();
					addMethodology(methodologyCreate,companyMethodology,1);
					return 1;
				}
			}
			
			//methodology
			public int addCompanySecurity(CompanySecurityCreate securityCreate)
			{
				Boolean securityIDExist=companySecurityRepository.existsById(securityCreate.getSecurityId());
				if(!securityIDExist)
				{
					TableCompanySecurity companySecurity=new TableCompanySecurity();
					addSecurity(securityCreate,companySecurity,0);
					return 0;
				}else {
					
					TableCompanySecurity companySecurity=companySecurityRepository.findById(securityCreate.getSecurityId()).get();
					addSecurity(securityCreate,companySecurity,1);
					return 1;
				}
			}
			
			
			public int addQualityControl(QualityControlCreate qualityControlCreate)
			{
				Boolean qcIDExist=qualityControlRepository.existsById(qualityControlCreate.getQualityControlId());
				if(!qcIDExist)
				{
					TableCompanyQualityControl companyQualityControl=new TableCompanyQualityControl();
					addQualityControl(qualityControlCreate,companyQualityControl,0);
					return 0;
				}else {
					
					TableCompanyQualityControl companyQualityControl=qualityControlRepository.findById(qualityControlCreate.getQualityControlId()).get();
					addQualityControl(qualityControlCreate,companyQualityControl,1);
					return 1;
				}
			}
			
			 public void addSecurity(CompanySecurityCreate securityCreate,TableCompanySecurity companySecurity,int code) {
					
				 companySecurity.setBusinessUnitId(securityCreate.getBusinessUnitId()); 
				 companySecurity.setBusinessUnitTypeId(securityCreate.getBusinessUnitTypeId());
				 companySecurity.setSecurityName(securityCreate.getSecurityName());
				 companySecurity.setSecurityDetails(securityCreate.getSecurityDetails());
				 companySecurity.setCertifications(securityCreate.getCertifications());
				 if(code==0)
				 {
					 companySecurity.setStatus("Y");
					 companySecurity.setCreatedAt(currentDateTime());
				 }else {
					 companySecurity.setUpdatedAt(currentDateTime());
				 }
				 companySecurityRepository.save(companySecurity);
			 }
			
			 public void addMethodology(CompanyMethodologyCreate methodologyCreate,TableCompanyMethodology companyMethodology,int code) {
			
				 companyMethodology.setBusinessUnitId(methodologyCreate.getBusinessUnitId()); 
				 companyMethodology.setBusinessUnitTypeId(methodologyCreate.getBusinessUnitTypeId());
				 companyMethodology.setMethodologyName(methodologyCreate.getMethodologyName());
				 companyMethodology.setMethodologyDetails(methodologyCreate.getMethodologyDetails());
				 companyMethodology.setCertifications(methodologyCreate.getCertifications());
				 if(code==0)
				 {
				 companyMethodology.setStatus("Y");
				 companyMethodology.setCreatedAt(currentDateTime());
				 }else {
				 companyMethodology.setUpdatedAt(currentDateTime());
				 }
				 methodologyRepo.save(companyMethodology);
			 }
				 
			 public void addQualityControl(QualityControlCreate qualityControlCreate,TableCompanyQualityControl companyQualityControl,int code) {
					
				 companyQualityControl.setBusinessUnitId(qualityControlCreate.getBusinessUnitId()); 
				 companyQualityControl.setBusinessUnitTypeId(qualityControlCreate.getBusinessUnitTypeId());
				 companyQualityControl.setQualityControlName(qualityControlCreate.getQualityControlName());
				 companyQualityControl.setQualityControlDetails(qualityControlCreate.getQualityControlDetails());
				 companyQualityControl.setCertifications(qualityControlCreate.getCertifications());
				 if(code==0)
				 {
					 companyQualityControl.setStatus("Y");
					 companyQualityControl.setCreatedAt(currentDateTime());
				 }else {
					 companyQualityControl.setUpdatedAt(currentDateTime());
				 }
				 qualityControlRepository.save(companyQualityControl);
			 }
		 
		 public void addProcess(CompanyProcessCreate processCreate,TableCompanyProcess companyProcess,int code) {
			 companyProcess.setBusinessUnitId(processCreate.getBusinessUnitId());
			 companyProcess.setBusinessUnitTypeId(processCreate.getBusinessUnitTypeId());
			 companyProcess.setProcessName(processCreate.getProcessName());
			 companyProcess.setProcessFunction(processCreate.getProcessFunction());
			 companyProcess.setProcessDetails(processCreate.getProcessDetails());
			 companyProcess.setNigpUnpscCodes(processCreate.getNigpUnpscCodes());
			 companyProcess.setStatus(Constants.STATUS_Y);
			 if(code==0)
			 {
				 companyProcess.setCreatedAt(currentDateTime());
				 logger.info("Adding New Company Process ..");
			 }else {
				 companyProcess.setUpdatedAt(currentDateTime());
				 logger.info("Updating  Company Process ..");
			 }
			 processRepository.save(companyProcess);
			 
		 }
				 
		 
				 
			public void addProducts(CompanyProductsCreate companyProductsCreate,TableCompanyProducts tableCompanyProducts,int code)
			{

					tableCompanyProducts.setBusinessUnitId(companyProductsCreate.getBusinessUnitId());
					tableCompanyProducts.setBusinessUnitTypeId(companyProductsCreate.getBusinessUnitTypeId());
					tableCompanyProducts.setProductName(companyProductsCreate.getProductName());
					tableCompanyProducts.setProductFunction(companyProductsCreate.getProductFunction());
					tableCompanyProducts.setProductDetails(companyProductsCreate.getProductDetails());
					tableCompanyProducts.setNigpUnpscCodes(companyProductsCreate.getNigpUnpscCodes());
				if(code==0)
				{
				tableCompanyProducts.setStatus("Y");
				logger.info("Adding Products..");
				tableCompanyProducts.setCreatedAt(currentDateTime());
				}else {
				tableCompanyProducts.setUpdatedAt(currentDateTime());
				logger.info("Updating Products..");
				}
				companyProductsRepo.save(tableCompanyProducts);


			}
			 
				public void addServices(CompanyServicesCreate companyServicesCreate,TableCompanyServices companyServices,int code)
				{

					companyServices.setBusinessUnitId(companyServicesCreate.getBusinessUnitId());
					companyServices.setBusinessUnitTypeId(companyServicesCreate.getBusinessUnitTypeId());
					companyServices.setServiceName(companyServicesCreate.getServiceName());
					companyServices.setServiceCategory(companyServicesCreate.getServiceCategory());
					companyServices.setServiceDescription(companyServicesCreate.getServiceDescription());
					companyServices.setNigpUnpscCodes(companyServicesCreate.getNigpUnpscCodes());
					if(code==0)
					{
						companyServices.setStatus("Y");
						companyServices.setCreatedAt(currentDateTime());
						logger.info("Adding Services..");
					}else {
						companyServices.setUpdatedAt(currentDateTime());
						logger.info("Updating Services..");
					}
					companyServiceRepo.save(companyServices);
					logger.info("Updated Services..");
					
				}
			
			
			
			public boolean productFieldTypeExist(String companyFieldTypeId)
			{
				boolean companyFieldTypeExists=companyFieldsRepository.existsById(Integer.parseInt(companyFieldTypeId));
				return companyFieldTypeExists;
			}
			
			public boolean serviceFieldTypeExist(String companyFieldTypeId)
			{
				boolean companyFieldTypeExists=companyFieldsRepository.existsById(Integer.parseInt(companyFieldTypeId));
				return companyFieldTypeExists;
			}
			
			
			
			public String productFieldStatus(String productFieldId,String status)
			{
				TableProductFieldTypes tableProductFieldTypess=companyFieldsRepository.findById(Integer.parseInt(productFieldId)).get();
				tableProductFieldTypess.setStatus(status);
				tableProductFieldTypess.setUpdatedAt(currentDateTime());
				companyFieldsRepository.save(tableProductFieldTypess);
				logger.info("Product field status changed");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.PROD_FIELD_ACTIVE;
				}else {
					return Constants.PROD_FIELD_INACTIVE;
				}
				 
			}
			
			public String serviceFieldStatus(String serviceFieldId,String status)
			{
				TableServiceFieldTypes serviceFieldTypes=companyServiceFieldsRepo.findById(Integer.parseInt(serviceFieldId)).get();
				serviceFieldTypes.setStatus(status);
				serviceFieldTypes.setUpdatedAt(currentDateTime());
				companyServiceFieldsRepo.save(serviceFieldTypes);
				logger.info("Service field status changed");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.SERV_FIELD_ACTIVE;
				}else {
					return Constants.SERV_FIELD_INACTIVE;
				}
				 
			}
			
			
			
			public boolean productIdExist(String productId)
			 { 
				return companyProductsRepo.existsById(Integer.parseInt(productId));
			 }
			
			
			public boolean templateIdExist(String templateId)
			 { 
				return wordTemplateRepository.existsById(Integer.parseInt(templateId));
			 }
			
			public boolean productModuleIdExist(String productModuleId)
			 { 
				return productModuleRepository.existsById(Integer.parseInt(productModuleId));
				
			 }
			public boolean serviceModuleIdExist(String serviceModuleId)
			 { 
				return serviceModuleRepository.existsById(Integer.parseInt(serviceModuleId));
				
			 }
			
			public boolean serviceIdExist(String serviceId)
			 { 
				return companyServiceRepo.existsById(Integer.parseInt(serviceId));
				
			 }
			
			public boolean processIdExist(String processId)
			 { 
				return processRepository.existsById(Integer.parseInt(processId));
				
			 }
			

			public boolean methodologyIdExist(String methodologyId)
			 { 
				return methodologyRepo.existsById(Integer.parseInt(methodologyId));
				
			 }
			public boolean qualityControlIdExist(String qualityControlId)
			 { 
				return qualityControlRepository.existsById(Integer.parseInt(qualityControlId));
				
			 }
			public boolean securityIdExist(String securityId)
			 { 
				return companySecurityRepository.existsById(Integer.parseInt(securityId));
				
			 }
			
			public String productModuleStatus(String productModuleId,String status)
			{
				TableProductModules productModules=productModuleRepository.findById(Integer.parseInt(productModuleId)).get();
				productModules.setStatus(status);
				productModules.setUpdatedAt(currentDateTime());
				productModuleRepository.save(productModules);
				logger.info("Product module status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_MOD_ACTIVE;
				}else {
					return Constants.COMPNY_MOD_INACTIVE;
				}
				 
			}
			
			public String serviceModuleStatus(String serviceModuleId,String status)
			{
				TableServiceModules serviceModules=serviceModuleRepository.findById(Integer.parseInt(serviceModuleId)).get();
				serviceModules.setStatus(status);
				serviceModules.setUpdatedAt(currentDateTime());
				serviceModuleRepository.save(serviceModules);
				logger.info("Service module status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.SERV_MOD_ACTIVE;
				}else {
					return Constants.SERV_MOD_INACTIVE;
				}
				 
			}
			
			public String companyProductStatus(String productId,String status)
			{
				TableCompanyProducts tableCompanyProducts=companyProductsRepo.findById(Integer.parseInt(productId)).get();
				tableCompanyProducts.setStatus(status);
				tableCompanyProducts.setUpdatedAt(currentDateTime());
				companyProductsRepo.save(tableCompanyProducts);
				logger.info("Company product status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_PROD_ACTIVE;
				}else {
					return Constants.COMPNY_PROD_INACTIVE;
				}
				 
			}
			
			
			
			
			public String companyServiceStatus(String serviceId,String status)
			{
				TableCompanyServices companyServices=companyServiceRepo.findById(Integer.parseInt(serviceId)).get();
				companyServices.setStatus(status);
				companyServices.setUpdatedAt(currentDateTime());
				companyServiceRepo.save(companyServices);
				logger.info("Company services status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_SERV_ACTIVE;
				}else {
					return Constants.COMPNY_SERV_INACTIVE;
				}
				 
			}
			
			public String companyProcessStatus(String processId,String status)
			{
				TableCompanyProcess companyProcess=processRepository.findById(Integer.parseInt(processId)).get();
				companyProcess.setStatus(status);
				companyProcess.setUpdatedAt(currentDateTime());
				processRepository.save(companyProcess);
				logger.info("Company process status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_PROCSS_ACTIVE;
				}else {
					return Constants.COMPNY_PROCSS_INACTIVE;
				}
				 
			}
			
			public String companyMethodologyStatus(String methodologyId,String status)
			{
				TableCompanyMethodology companyMethodology=methodologyRepo.findById(Integer.parseInt(methodologyId)).get();
				companyMethodology.setStatus(status);
				companyMethodology.setUpdatedAt(currentDateTime());
				methodologyRepo.save(companyMethodology);
				logger.info("Company methodology status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_METHLGY_ACTIVE;
				}else {
					return Constants.COMPNY_METHLGY_INACTIVE;
				}
				 
			}
			
			public String qualityControlStatus(String qualityControlId,String status)
			{
				TableCompanyQualityControl companyQualityControl=qualityControlRepository.findById(Integer.parseInt(qualityControlId)).get();
				companyQualityControl.setStatus(status);
				companyQualityControl.setUpdatedAt(currentDateTime());
				qualityControlRepository.save(companyQualityControl);
				logger.info("Company Quality Control status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_QC_ACTIVE;
				}else {
					return Constants.COMPNY_QC_INACTIVE;
				}
				 
			}
			
			public String changeSecurityStatus(String qualityControlId,String status)
			{
				TableCompanySecurity companySecurity=companySecurityRepository.findById(Integer.parseInt(qualityControlId)).get();
				companySecurity.setStatus(status);
				companySecurity.setUpdatedAt(currentDateTime());
				companySecurityRepository.save(companySecurity);
				logger.info("Company security status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.COMPNY_SECR_ACTIVE;
				}else {
					return Constants.COMPNY_SECR_INACTIVE;
				}
				 
			}
			
			public ProductModuleResp getProductModuleList(Integer productId)
			{
				List<TableProductModules> productModules = new ArrayList<TableProductModules>();
				if(productId !=null){
					productModules = productModuleRepository.findByProductId(productId);
				}else{
					productModules = productModuleRepository.findAll();
				}
				ArrayList<TableProductModules> productModulesListData=new ArrayList<TableProductModules>();
				for(TableProductModules productModulesData:productModules)
				{
					if(productModulesData.getStatus().equalsIgnoreCase(Constants.STATUS_Y))
					{
						productModulesListData.add(productModulesData);
					}
					
				}
				ProductModuleResp productModuleResp=new ProductModuleResp();
				productModuleResp.setProductModules(productModulesListData);
				productModuleResp.setMessage("Success");
				productModuleResp.setCode(200);
				return productModuleResp;
			}
			
			//to do
			public ServiceModuleResp getServiceModuleList()
			{
				ArrayList<TableServiceModules> serviceModules=(ArrayList<TableServiceModules>) serviceModuleRepository.findAll();
				ArrayList<TableServiceModules> serviceModulesListData=new ArrayList<TableServiceModules>();
				for(TableServiceModules serviceModulesData:serviceModules)
				{
					if(serviceModulesData.getStatus().equalsIgnoreCase(Constants.STATUS_Y))
					{
						serviceModulesListData.add(serviceModulesData);
					}
					
				}
				ServiceModuleResp serviceModuleResp=new ServiceModuleResp();
				serviceModuleResp.setServiceModules(serviceModulesListData);
				serviceModuleResp.setMessage("Success");
				serviceModuleResp.setCode(200);
				return serviceModuleResp;
			}
			
			
			public ServiceFieldTypesResp getServiceFieldTypeList()
			{
				ArrayList<TableServiceFieldTypes> serviceFieldTypesList=(ArrayList<TableServiceFieldTypes>) companyServiceFieldsRepo.findAll();
				ArrayList<TableServiceFieldTypes> serviceFieldTypesListData=new ArrayList<TableServiceFieldTypes>();
				for(TableServiceFieldTypes fieldTypeData:serviceFieldTypesList)
				{
					if(fieldTypeData.getStatus().equalsIgnoreCase(Constants.STATUS_Y))
					{
						serviceFieldTypesListData.add(fieldTypeData);
					}
					
				}
				ServiceFieldTypesResp serviceFieldTypesRep=new ServiceFieldTypesResp();
				serviceFieldTypesRep.setServiceFieldTypes(serviceFieldTypesListData);
				serviceFieldTypesRep.setMessage("Success");
				serviceFieldTypesRep.setCode(200);
				return serviceFieldTypesRep;
			}
			
			
		
			public ProductFieldTypesResp getProductFieldTypeList()
			{
				ArrayList<TableProductFieldTypes> companyFieldTypesList=(ArrayList<TableProductFieldTypes>) companyFieldsRepository.findAll();
				ArrayList<TableProductFieldTypes> companyFieldTypesListData=new ArrayList<TableProductFieldTypes>();
				for(TableProductFieldTypes fieldTypeData:companyFieldTypesList)
				{
					if(fieldTypeData.getStatus().equalsIgnoreCase(Constants.STATUS_Y))
					{
						companyFieldTypesListData.add(fieldTypeData);
					}
					
				}
				ProductFieldTypesResp companyFieldTypesRep=new ProductFieldTypesResp();
				companyFieldTypesRep.setCompanyFieldTypes(companyFieldTypesListData);
				companyFieldTypesRep.setMessage("Success");
				companyFieldTypesRep.setCode(200);
				return companyFieldTypesRep;
			}
			
			
			public  CompanyProductSearchResp  companyProductSearch(CompanyProductSearch companyProductSearch)
			{
				int limit = companyProductSearch.getLimit();
		        int page = companyProductSearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableCompanyProducts> tableCompanyProducts = null;
		        if (companyProductSearch.getOrderby() != null && companyProductSearch.getOrderby() != Constants.EMPTY) {
		            if (companyProductSearch.getSortby() != null && companyProductSearch.getSortby() != Constants.EMPTY) {
		                if (companyProductSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(companyProductSearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(companyProductSearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(companyProductSearch.getOrderby()).descending());
		            }

		        } else {
		            if (companyProductSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
		        }
		        
		        
	        if(Constants.isNullOrEmpty(companyProductSearch.getProductName()) 
	        		&& Constants.isNullOrEmpty(companyProductSearch.getProductFunction())  
	        		&& Constants.isNullOrEmpty(companyProductSearch.getProductDetails())&& 
	        		Constants.isNullOrEmpty(companyProductSearch.getNigpUnpscCodes())  &&
	        		Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitId()) &&
	        		Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitTypeId())  
	        		)
            {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContainingAndProductFunctionContainingAndProductDetailsContainingAndNigpUnpscCodesContaining(companyProductSearch.getBusinessUnitId(),companyProductSearch.getBusinessUnitTypeId(),companyProductSearch.getProductName(), companyProductSearch.getProductFunction(), companyProductSearch.getProductDetails(), companyProductSearch.getNigpUnpscCodes(), pageable);
	        }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductName()) 
	        		&& Constants.isNullOrEmpty(companyProductSearch.getProductFunction())  
	        		&& Constants.isNullOrEmpty(companyProductSearch.getProductDetails()) 
	        		&&Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitId()) &&
	        		Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitTypeId())
	        		)
	        {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContainingAndProductFunctionContainingAndProductDetailsContaining(companyProductSearch.getBusinessUnitId(),companyProductSearch.getBusinessUnitTypeId(),companyProductSearch.getProductName(), companyProductSearch.getProductFunction(), companyProductSearch.getProductDetails(), pageable);
	 	    }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductName()) 
	        		&& Constants.isNullOrEmpty(companyProductSearch.getProductFunction())
	        		&&Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitId()) &&
	        		Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitTypeId())
	        		)
	        {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContainingAndProductFunctionContaining(companyProductSearch.getBusinessUnitId(),companyProductSearch.getBusinessUnitTypeId(),companyProductSearch.getProductName(), companyProductSearch.getProductFunction(), pageable);
	 	    }

	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductName()) 
	        		&&Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitId()) &&
	        		Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitTypeId())
	        		)
	        {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContaining(companyProductSearch.getBusinessUnitId(),companyProductSearch.getBusinessUnitTypeId(),companyProductSearch.getProductName(), pageable);
	 	    }
	        
	        else if(Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitId()) &&
	        		Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitTypeId()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(companyProductSearch.getBusinessUnitId(),companyProductSearch.getBusinessUnitTypeId(), pageable);
	 	    }
	        
	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductName()) &&
	        		Constants.isNullOrEmpty(companyProductSearch.getStatus()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByProductNameContainingAndStatus(companyProductSearch.getProductName(),companyProductSearch.getStatus(), pageable);
	 	    }

	        else if(Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitId()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitIdContaining(companyProductSearch.getBusinessUnitId(), pageable);
	 	    }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getBusinessUnitTypeId()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByBusinessUnitTypeIdContaining(companyProductSearch.getBusinessUnitTypeId(), pageable);
	 	    }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductName()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByProductNameContaining(companyProductSearch.getProductName(),pageable);
	 	    }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductFunction()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByProductFunctionContaining(companyProductSearch.getProductFunction(), pageable);
	        }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getProductDetails()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByProductDetailsContaining(companyProductSearch.getProductDetails(), pageable);
	        }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getNigpUnpscCodes()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByProductFunctionContaining(companyProductSearch.getNigpUnpscCodes(), pageable);
	        }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getNigpUnpscCodes()))
	        {
	        	tableCompanyProducts=companyProductsRepo.findByProductFunctionContaining(companyProductSearch.getNigpUnpscCodes(), pageable);
	        }
	        else if(Constants.isNullOrEmpty(companyProductSearch.getStatus())){
	        	tableCompanyProducts=companyProductsRepo.findByStatus(companyProductSearch.getStatus(), pageable);
	 		}
	        else 
	        {
	 			tableCompanyProducts=companyProductsRepo.findAll(pageable);
	 		}
		        CompanyProductSearchResp  companyProductSearchResp=new CompanyProductSearchResp();
		        companyProductSearchResp.setCompanyProducts(tableCompanyProducts);
		        companyProductSearchResp.setCode(Constants.SUCCESS_CODE);
		        companyProductSearchResp.setMessage("Success");
				return companyProductSearchResp;
			}
			
			
			public CompanyMethodologySearchResp companyMethodologySearch(CompanyMethodologySearch methodologySearch){
				
				
				int limit = methodologySearch.getLimit();
		        int page = methodologySearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableCompanyMethodology> companyMethodology = null;
		        if (methodologySearch.getOrderby() != null && methodologySearch.getOrderby() != Constants.EMPTY) {
		            if (methodologySearch.getSortby() != null && methodologySearch.getSortby() != Constants.EMPTY) {
		                if (methodologySearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(methodologySearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(methodologySearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(methodologySearch.getOrderby()).descending());
		            }

		        } else {
		            if (methodologySearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
		        }
		        
		        if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getMethodologyName())  && Constants.isNullOrEmpty(methodologySearch.getMethodologyDetails()) &&  Constants.isNullOrEmpty(methodologySearch.getCertifications()) && Constants.isNullOrEmpty(methodologySearch.getStatus()) )
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndMethodologyNameContainingAndMethodologyDetailsContainingAndCertificationsContainingAndStatus(methodologySearch.getBusinessUnitId(),methodologySearch.getBusinessUnitTypeId(), methodologySearch.getMethodologyName(),methodologySearch.getMethodologyDetails(),methodologySearch.getCertifications(),methodologySearch.getStatus(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getMethodologyName())  && Constants.isNullOrEmpty(methodologySearch.getMethodologyDetails()) &&  Constants.isNullOrEmpty(methodologySearch.getCertifications()) )
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitIdAndBusinessUnitTypeIdAndMethodologyNameAndMethodologyDetailsAndCertifications(methodologySearch.getBusinessUnitId(),methodologySearch.getBusinessUnitTypeId(),methodologySearch.getMethodologyName(),methodologySearch.getMethodologyDetails(),methodologySearch.getCertifications(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getMethodologyName())  && Constants.isNullOrEmpty(methodologySearch.getMethodologyDetails())  )
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndMethodologyNameContainingAndMethodologyDetailsContaining(methodologySearch.getBusinessUnitId(),methodologySearch.getBusinessUnitTypeId(),methodologySearch.getMethodologyName(),methodologySearch.getMethodologyDetails(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getMethodologyName())    )
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndMethodologyNameContaining(methodologySearch.getBusinessUnitId(),methodologySearch.getBusinessUnitTypeId(),methodologySearch.getMethodologyName(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()) )
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(methodologySearch.getBusinessUnitId(),methodologySearch.getBusinessUnitTypeId(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getMethodologyName()) && Constants.isNullOrEmpty(methodologySearch.getStatus()) )
		        {
		        	companyMethodology=methodologyRepo.findByMethodologyNameContainingAndStatus(methodologySearch.getMethodologyName(),methodologySearch.getStatus(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitId()))
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitIdContaining(methodologySearch.getBusinessUnitId(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getBusinessUnitTypeId()))
		        {
		        	companyMethodology=methodologyRepo.findByBusinessUnitTypeIdContaining(methodologySearch.getBusinessUnitTypeId(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getMethodologyName()))
		        {
		        	companyMethodology=methodologyRepo.findByMethodologyNameContaining(methodologySearch.getMethodologyName(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getMethodologyDetails()))
		        {
		        	companyMethodology=methodologyRepo.findByMethodologyDetailsContaining(methodologySearch.getMethodologyDetails(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getCertifications()))
		        {
		        	companyMethodology=methodologyRepo.findByCertificationsContaining(methodologySearch.getCertifications(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(methodologySearch.getStatus())  )
		        {
		        	companyMethodology=methodologyRepo.findByStatus(methodologySearch.getStatus(),pageable);
		        }
		        else {
		        	companyMethodology=methodologyRepo.findAll(pageable);
		        }
		        
		        CompanyMethodologySearchResp methodologySearchResp=new CompanyMethodologySearchResp();
		        methodologySearchResp.setCompanyMethodology(companyMethodology);
		        methodologySearchResp.setMessage("Success");
		        methodologySearchResp.setCode(200);
		        return methodologySearchResp;
				
			}
			
public CompanyQualityControlSearchResp qualityControlSearch(CompanyQualityControlSearch qualityControlSearch){
				
				
				int limit = qualityControlSearch.getLimit();
		        int page = qualityControlSearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableCompanyQualityControl> qualityControl = null;
		        if (qualityControlSearch.getOrderby() != null && qualityControlSearch.getOrderby() != Constants.EMPTY) {
		            if (qualityControlSearch.getSortby() != null && qualityControlSearch.getSortby() != Constants.EMPTY) {
		                if (qualityControlSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(qualityControlSearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(qualityControlSearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(qualityControlSearch.getOrderby()).descending());
		            }

		        } else {
		            if (qualityControlSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
		        }
		        
		        if( Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(qualityControlSearch.getQualityControlName())  && Constants.isNullOrEmpty(qualityControlSearch.getQualityControlDetails()) &&  Constants.isNullOrEmpty(qualityControlSearch.getCertifications()) && Constants.isNullOrEmpty(qualityControlSearch.getStatus()) )
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContainingAndQualityControlDetailsContainingAndCertificationsContainingAndStatus(qualityControlSearch.getBusinessUnitId(),qualityControlSearch.getBusinessUnitTypeId(), qualityControlSearch.getQualityControlName(),qualityControlSearch.getQualityControlDetails(),qualityControlSearch.getCertifications(),qualityControlSearch.getStatus(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(qualityControlSearch.getQualityControlName())  && Constants.isNullOrEmpty(qualityControlSearch.getQualityControlDetails()) &&  Constants.isNullOrEmpty(qualityControlSearch.getCertifications()) )
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContainingAndQualityControlDetailsContainingAndCertificationsContaining(qualityControlSearch.getBusinessUnitId(),qualityControlSearch.getBusinessUnitTypeId(),qualityControlSearch.getQualityControlName(),qualityControlSearch.getQualityControlDetails(),qualityControlSearch.getCertifications(),pageable);
		        }
		        
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(qualityControlSearch.getQualityControlName())  && Constants.isNullOrEmpty(qualityControlSearch.getQualityControlDetails()) )
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContainingAndQualityControlDetailsContaining(qualityControlSearch.getBusinessUnitId(),qualityControlSearch.getBusinessUnitTypeId(),qualityControlSearch.getQualityControlName(),qualityControlSearch.getQualityControlDetails(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitTypeId()) &&  Constants.isNullOrEmpty(qualityControlSearch.getQualityControlName()))
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContaining(qualityControlSearch.getBusinessUnitId(),qualityControlSearch.getBusinessUnitTypeId(),qualityControlSearch.getQualityControlName(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getQualityControlName()))
		        {
		        	qualityControl=qualityControlRepository.findByQualityControlNameContaining(qualityControlSearch.getQualityControlName(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getQualityControlName()) && Constants.isNullOrEmpty(qualityControlSearch.getStatus()))
		        {
		        	qualityControl=qualityControlRepository.findByQualityControlNameContainingAndStatus(qualityControlSearch.getQualityControlName(),qualityControlSearch.getStatus(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitTypeId()))
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(qualityControlSearch.getBusinessUnitId(),qualityControlSearch.getBusinessUnitTypeId(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitId()))
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitIdContaining(qualityControlSearch.getBusinessUnitId(),pageable);
		        }
		        else if( Constants.isNullOrEmpty(qualityControlSearch.getBusinessUnitTypeId()))
		        {
		        	qualityControl=qualityControlRepository.findByBusinessUnitTypeIdContaining(qualityControlSearch.getBusinessUnitTypeId(),pageable);
		        }
		        
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getQualityControlDetails()))
		        {
		        	qualityControl=qualityControlRepository.findByQualityControlDetailsContaining(qualityControlSearch.getQualityControlDetails(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getCertifications()))
		        {
		        	qualityControl=qualityControlRepository.findByCertificationsContaining(qualityControlSearch.getCertifications(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(qualityControlSearch.getStatus()))
		        {
		        	qualityControl=qualityControlRepository.findByStatus(qualityControlSearch.getStatus(),pageable);
		        }
		        else {
		        	qualityControl=qualityControlRepository.findAll(pageable);
		        }
		        CompanyQualityControlSearchResp qualityControlSearchResp=new CompanyQualityControlSearchResp();
		        qualityControlSearchResp.setCompanyQualityControl(qualityControl);
		        qualityControlSearchResp.setMessage("Success");
		        qualityControlSearchResp.setCode(200);
		        return qualityControlSearchResp;
				
			}
			

public CompanySecuritySearchResp companySecuritySearch(CompanySecuritySearch securitySearch){
	
	
	int limit = securitySearch.getLimit();
    int page = securitySearch.getPage_no();
    Pageable pageable = null;
    Page<TableCompanySecurity> companySecurity = null;
    if (securitySearch.getOrderby() != null && securitySearch.getOrderby() != Constants.EMPTY) {
        if (securitySearch.getSortby() != null && securitySearch.getSortby() != Constants.EMPTY) {
            if (securitySearch.getSortby().equalsIgnoreCase(Constants.ASC))
                pageable = PageRequest.of(page, limit, Sort.by(securitySearch.getOrderby()).ascending());
            else
                pageable = PageRequest.of(page, limit, Sort.by(securitySearch.getOrderby()).descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(securitySearch.getOrderby()).descending());
        }

    } else {
        if (securitySearch.getSortby().equalsIgnoreCase(Constants.ASC))
            pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
        else
            pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
    }
    
    if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(securitySearch.getSecurityName())  && Constants.isNullOrEmpty(securitySearch.getSecurityDetails()) &&  Constants.isNullOrEmpty(securitySearch.getCertifications()) && Constants.isNullOrEmpty(securitySearch.getStatus()) )
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContainingAndSecurityDetailsContainingAndCertificationsContainingAndStatus(securitySearch.getBusinessUnitId() ,securitySearch.getBusinessUnitTypeId(),securitySearch.getSecurityName(),securitySearch.getSecurityDetails(),securitySearch.getCertifications(),securitySearch.getStatus(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(securitySearch.getSecurityName())  && Constants.isNullOrEmpty(securitySearch.getSecurityDetails()) &&  Constants.isNullOrEmpty(securitySearch.getCertifications()) )
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContainingAndSecurityDetailsContainingAndCertificationsContaining(securitySearch.getBusinessUnitId(),securitySearch.getBusinessUnitTypeId(),securitySearch.getSecurityName(),securitySearch.getSecurityDetails(),securitySearch.getCertifications(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(securitySearch.getSecurityName())  && Constants.isNullOrEmpty(securitySearch.getSecurityDetails())  )
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContainingAndSecurityDetailsContaining(securitySearch.getBusinessUnitId(),securitySearch.getBusinessUnitTypeId(),securitySearch.getSecurityName(),securitySearch.getSecurityDetails(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()) && Constants.isNullOrEmpty(securitySearch.getSecurityName()))
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContaining(securitySearch.getBusinessUnitId(),securitySearch.getBusinessUnitTypeId(),securitySearch.getSecurityName(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()))
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(securitySearch.getBusinessUnitId(),securitySearch.getBusinessUnitTypeId(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()) && Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()))
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(securitySearch.getBusinessUnitId(),securitySearch.getBusinessUnitTypeId(),pageable);
    }
    
    else if(Constants.isNullOrEmpty(securitySearch.getSecurityName()) && Constants.isNullOrEmpty(securitySearch.getStatus()))
    {
    	companySecurity=companySecurityRepository.findBySecurityNameContainingAndStatus(securitySearch.getSecurityName(),securitySearch.getStatus(),pageable);
    }

    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitId()))
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitIdContaining(securitySearch.getBusinessUnitId(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getBusinessUnitTypeId()))
    {
    	companySecurity=companySecurityRepository.findByBusinessUnitTypeIdContaining(securitySearch.getBusinessUnitTypeId(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getSecurityName()))
    {
    	companySecurity=companySecurityRepository.findBySecurityNameContaining(securitySearch.getSecurityName(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getSecurityDetails()))
    {
    	companySecurity=companySecurityRepository.findBySecurityDetailsContaining(securitySearch.getSecurityDetails(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getCertifications()))
    {
    	companySecurity=companySecurityRepository.findByCertificationsContaining(securitySearch.getCertifications(),pageable);
    }
    else if(Constants.isNullOrEmpty(securitySearch.getStatus()))
    {
    	companySecurity=companySecurityRepository.findByStatus(securitySearch.getStatus(),pageable);
    }
    else {
    	companySecurity=companySecurityRepository.findAll(pageable);
    }
    
    CompanySecuritySearchResp SecuritySearchResp=new CompanySecuritySearchResp();
    SecuritySearchResp.setCompanySecurity(companySecurity);
    SecuritySearchResp.setMessage("Success");
    SecuritySearchResp.setCode(200);
    return SecuritySearchResp;
	
}

			   
			public CompanyProcessSearchResp companyProcessSearch(CompanyProcessSearch processSearch)
			{
				int limit = processSearch.getLimit();
		        int page = processSearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableCompanyProcess> companyProcess = null;
		        if (processSearch.getOrderby() != null && processSearch.getOrderby() != Constants.EMPTY) {
		            if (processSearch.getSortby() != null && processSearch.getSortby() != Constants.EMPTY) {
		                if (processSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(processSearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(processSearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(processSearch.getOrderby()).descending());
		            }

		        } else {
		            if (processSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
		        }
		        
		        
				if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId())&& Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId())  &&  Constants.isNullOrEmpty(processSearch.getProcessName()) && Constants.isNullOrEmpty(processSearch.getProcessFunction()) &&  
						Constants.isNullOrEmpty(processSearch.getStatus()) &&Constants.isNullOrEmpty(processSearch.getProcessDetails()) 
						&& Constants.isNullOrEmpty(processSearch.getNigpUnpscCodes())){
					
					companyProcess=processRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContainingAndProcessDetailsContainingAndNigpUnpscCodesContainingAndStatus
							(processSearch.getBusinessUnitId(),processSearch.getBusinessUnitTypeId(), processSearch.getProcessName(),processSearch.getProcessFunction(),processSearch.getProcessDetails(),processSearch.getNigpUnpscCodes()
									,processSearch.getStatus(),pageable );
					
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId())  &&  Constants.isNullOrEmpty(processSearch.getProcessName()) && Constants.isNullOrEmpty(processSearch.getProcessFunction()) &&  
						Constants.isNullOrEmpty(processSearch.getProcessDetails()) 
						&& Constants.isNullOrEmpty(processSearch.getNigpUnpscCodes())){
					companyProcess=processRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContainingAndProcessDetailsContainingAndNigpUnpscCodesContaining
							(processSearch.getBusinessUnitId(),processSearch.getBusinessUnitTypeId(),processSearch.getProcessName(),processSearch.getProcessFunction(),processSearch.getProcessDetails(),processSearch.getNigpUnpscCodes(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId()) &&Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId()) &&  Constants.isNullOrEmpty(processSearch.getProcessName()) && Constants.isNullOrEmpty(processSearch.getProcessFunction()) &&  
						Constants.isNullOrEmpty(processSearch.getProcessDetails())  )
				{
					companyProcess=processRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContainingAndProcessDetailsContaining(processSearch.getBusinessUnitId(),processSearch.getBusinessUnitTypeId(),processSearch.getProcessName(),processSearch.getProcessFunction(),processSearch.getProcessDetails(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId())  &&Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId())  &&  Constants.isNullOrEmpty(processSearch.getProcessName()) && Constants.isNullOrEmpty(processSearch.getProcessFunction()))
				{
					companyProcess=processRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContaining(processSearch.getBusinessUnitId(),processSearch.getBusinessUnitTypeId(),processSearch.getProcessName(),processSearch.getProcessFunction(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId()) &&Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId()) &&  Constants.isNullOrEmpty(processSearch.getProcessName()) )
				{
					companyProcess=processRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContaining(processSearch.getBusinessUnitId(),processSearch.getBusinessUnitTypeId(),processSearch.getProcessName(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId()) &&Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId()))
				{
					companyProcess=processRepository.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(processSearch.getBusinessUnitId(),processSearch.getBusinessUnitTypeId(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getProcessName()) &&Constants.isNullOrEmpty(processSearch.getStatus()))
				{
					companyProcess=processRepository.findByProcessNameContainingAndStatus(processSearch.getProcessName(),processSearch.getStatus(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitTypeId()))
				{
					companyProcess=processRepository.findByBusinessUnitTypeIdContaining(processSearch.getBusinessUnitTypeId(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getBusinessUnitId()))
				{
					companyProcess=processRepository.findByBusinessUnitIdContaining(processSearch.getBusinessUnitId(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getProcessName()))
				{
					companyProcess=processRepository.findByProcessNameContaining(processSearch.getProcessName(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getProcessFunction())  )
				{
					companyProcess=processRepository.findByProcessFunctionContaining(processSearch.getProcessFunction(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getProcessDetails())  )
				{
					companyProcess=processRepository.findByProcessDetailsContaining(processSearch.getProcessDetails(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getNigpUnpscCodes())  )
				{
					companyProcess=processRepository.findByNigpUnpscCodesContaining(processSearch.getNigpUnpscCodes(),pageable );
				}
				else if(Constants.isNullOrEmpty(processSearch.getStatus())  )
				{
					companyProcess=processRepository.findByStatus(processSearch.getStatus(),pageable );
				}
				else
				{
					companyProcess=processRepository.findAll(pageable);
				}
				
				CompanyProcessSearchResp processSearchResp=new CompanyProcessSearchResp();
				processSearchResp.setCompanyProcess(companyProcess);
				processSearchResp.setMessage("Success");
				processSearchResp.setCode(200);
				
				return processSearchResp;
			}
			

			   
			
			
			
			
			public  CompanyServiceSearchResp  companyServiceSearch(CompanyServiceSearch serviceSearch)
			{
				int limit = serviceSearch.getLimit();
		        int page = serviceSearch.getPage_no();
		        Pageable pageable = null;
		        Page<TableCompanyServices> companyServices = null;
		        if (serviceSearch.getOrderby() != null && serviceSearch.getOrderby() != Constants.EMPTY) {
		            if (serviceSearch.getSortby() != null && serviceSearch.getSortby() != Constants.EMPTY) {
		                if (serviceSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                    pageable = PageRequest.of(page, limit, Sort.by(serviceSearch.getOrderby()).ascending());
		                else
		                    pageable = PageRequest.of(page, limit, Sort.by(serviceSearch.getOrderby()).descending());
		            } else {
		                pageable = PageRequest.of(page, limit, Sort.by(serviceSearch.getOrderby()).descending());
		            }

		        } else {
		            if (serviceSearch.getSortby().equalsIgnoreCase(Constants.ASC))
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).ascending());
		            else
		                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_CREATEDATE).descending());
		        }
		        
		      
		        if(Constants.isNullOrEmpty(serviceSearch.getServiceName()) && 
		        		Constants.isNullOrEmpty(serviceSearch.getServiceCategory()) && 
		        		Constants.isNullOrEmpty(serviceSearch.getServiceDescription()) && 
		        		Constants.isNullOrEmpty(serviceSearch.getNigpUnpscCodes()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()) &&
		        		Constants.isNullOrEmpty(serviceSearch.getStatus()))
		        {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContainingAndServiceDescriptionContainingAndNigpUnpscCodesContainingAndStatus(serviceSearch.getBusinessUnitId(),serviceSearch.getBusinessUnitTypeId(),serviceSearch.getServiceName(), serviceSearch.getServiceCategory(), serviceSearch.getServiceDescription(), serviceSearch.getNigpUnpscCodes(), serviceSearch.getStatus(), pageable);
		        }
		        else if(Constants.isNullOrEmpty(serviceSearch.getServiceName()) && 
		        		Constants.isNullOrEmpty(serviceSearch.getServiceCategory()) && 
		        		Constants.isNullOrEmpty(serviceSearch.getServiceDescription()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()) &&
		        		Constants.isNullOrEmpty(serviceSearch.getNigpUnpscCodes()))
		        {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContainingAndServiceDescriptionContainingAndNigpUnpscCodesContaining(serviceSearch.getBusinessUnitId(),serviceSearch.getBusinessUnitTypeId(),serviceSearch.getServiceName(), serviceSearch.getServiceCategory(), serviceSearch.getServiceDescription(), serviceSearch.getNigpUnpscCodes(), pageable);
				 }
		        else if(Constants.isNullOrEmpty(serviceSearch.getServiceName()) && 
		        		Constants.isNullOrEmpty(serviceSearch.getServiceCategory()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()) &&
		        		Constants.isNullOrEmpty(serviceSearch.getServiceDescription()))
		         {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContainingAndServiceDescriptionContaining(serviceSearch.getBusinessUnitId(),serviceSearch.getBusinessUnitTypeId(),serviceSearch.getServiceName(), serviceSearch.getServiceCategory(), serviceSearch.getServiceDescription(), pageable);
				 }
		        else if(Constants.isNullOrEmpty(serviceSearch.getServiceName()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()) &&
		        		Constants.isNullOrEmpty(serviceSearch.getServiceCategory()))
		         {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContaining(serviceSearch.getBusinessUnitId(),serviceSearch.getBusinessUnitTypeId(), serviceSearch.getServiceName(), serviceSearch.getServiceCategory(), pageable);
				 }
		        
		         else if(Constants.isNullOrEmpty(serviceSearch.getServiceName()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()))
		         {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContaining(serviceSearch.getBusinessUnitId(),serviceSearch.getBusinessUnitTypeId(), serviceSearch.getServiceName(), pageable);
				 }
		         else if( Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()) && Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()))
		         {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(serviceSearch.getBusinessUnitId(),serviceSearch.getBusinessUnitTypeId(), pageable);
				 }
		         else if( Constants.isNullOrEmpty(serviceSearch.getServiceName()) && Constants.isNullOrEmpty(serviceSearch.getStatus()))
		         {
		        	companyServices=companyServiceRepo.findByServiceNameContainingAndStatus(serviceSearch.getServiceName(), serviceSearch.getStatus(), pageable);
		         }
		        
		         else if( Constants.isNullOrEmpty(serviceSearch.getBusinessUnitId()))
		         {
		        	companyServices=companyServiceRepo.findByBusinessUnitIdContaining(serviceSearch.getBusinessUnitId(), pageable);
				 }
		         else if(  Constants.isNullOrEmpty(serviceSearch.getBusinessUnitTypeId()))
		         {
		        	companyServices=companyServiceRepo.findByBusinessUnitTypeIdContaining(serviceSearch.getBusinessUnitTypeId(), pageable);
				 }
		        else if(Constants.isNullOrEmpty(serviceSearch.getServiceName()))
		        {
		        	companyServices=companyServiceRepo.findByServiceNameContaining(serviceSearch.getServiceName(), pageable);
				 }
		        else if(Constants.isNullOrEmpty(serviceSearch.getServiceCategory()) )
		        {
		        	companyServices=companyServiceRepo.findByServiceCategoryContaining(serviceSearch.getServiceCategory(),pageable);
		        }
		        else if(Constants.isNullOrEmpty(serviceSearch.getServiceDescription()))
		        {
		        	companyServices=companyServiceRepo.findByServiceDescriptionContaining(serviceSearch.getServiceDescription(), pageable);
		        }
		        
		        else if(Constants.isNullOrEmpty(serviceSearch.getNigpUnpscCodes()) )
		        {
		        	companyServices=companyServiceRepo.findByNigpUnpscCodesContaining(serviceSearch.getNigpUnpscCodes(),pageable);
		        }
		        
		        else if(Constants.isNullOrEmpty(serviceSearch.getStatus()))
		        {
		        	companyServices=companyServiceRepo.findByStatus(serviceSearch.getStatus(), pageable);
				 }
		        else 
		        {
		        	companyServices=companyServiceRepo.findAll(pageable);
		        }
		     
		        
		        CompanyServiceSearchResp  serviceSearchResp=new CompanyServiceSearchResp();
		        serviceSearchResp.setCompanyServices(companyServices);
		        serviceSearchResp.setCode(Constants.SUCCESS_CODE);
		        serviceSearchResp.setMessage("Success");
				return serviceSearchResp;
			}
			
			
			
			
			public int addOrUpdateWordTemplate(String templateId,String templateName, MultipartFile document,String description) {
				
				System.out.println("comming --->");
				int attatchmentId = 0;
				Path path = Paths.get(uploadingDir);
				try {
					boolean templateExists=wordTemplateRepository.existsById(Integer.parseInt(templateId));
					TableWordTemplateAttachments data=null;
					System.out.println("----"+templateExists);
					if(templateExists)
					{
						data=wordTemplateRepository.findById(Integer.parseInt(templateId)).get();
						data.setUpdatedAt(currentDateTime());
					}else {
						data=new TableWordTemplateAttachments();
						data.setStatus("Y");
						data.setCreatedAt(currentDateTime());
						
					}
					
					Files.createDirectories(path);
					String fileName = StringUtils.cleanPath(document.getOriginalFilename());
					String fileExt = fileName.split("\\.")[fileName.split("\\.").length - 1];
					if (document.getBytes().length != 0 && document.getBytes().length < 2000000) {
						Path filepath = Paths.get(path.toString() + File.separator + templateName + "." + fileExt);
						Files.copy(document.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
						data.setDescr(description);
						data.setFilepath(filepath.toString());
						data.setName(templateName);
						wordTemplateRepository.save(data);
						attatchmentId=data.getId();
					}
				} catch (IOException e) {
					System.out.println(e);
				}

				return attatchmentId;
			}
		  
			public String wordTemplateStatus(String templateId,String status)
			{
				TableWordTemplateAttachments wordTemplateAttachments=wordTemplateRepository.findById(Integer.parseInt(templateId)).get();
				wordTemplateAttachments.setStatus(status);
				wordTemplateAttachments.setUpdatedAt(currentDateTime());
				wordTemplateRepository.save(wordTemplateAttachments);
				logger.info("Word template status changed..");
				if(status.equalsIgnoreCase("Y"))
				{
					return Constants.WORD_TEMP_ACTIVE;
				}else {
					return Constants.WORD_TEMP_INACTIVE;
				}
				 
			}
			

			 public ProposalStatusSearchResp proposalStatusSearch(ProposalStatusSearch proposalStatusSearch) {
					        int limit = proposalStatusSearch.getLimit();
					        int page = proposalStatusSearch.getPage_no();
					        Pageable pageable = null;
					        Page<TableProposalStatus> proposalStatus = null;

					        if (proposalStatusSearch.getOrderby() != null && proposalStatusSearch.getOrderby() != Constants.EMPTY) {
					            if (proposalStatusSearch.getSortby() != null && proposalStatusSearch.getSortby() != Constants.EMPTY) {
					                if (proposalStatusSearch.getSortby().equalsIgnoreCase(Constants.ASC))
					                    pageable = PageRequest.of(page, limit, Sort.by(proposalStatusSearch.getOrderby()).ascending());
					                else
					                    pageable = PageRequest.of(page, limit, Sort.by(proposalStatusSearch.getOrderby()).descending());
					            } else {
					                pageable = PageRequest.of(page, limit, Sort.by(proposalStatusSearch.getOrderby()).descending());
					            }

					        } else {
					            if (proposalStatusSearch.getSortby().equalsIgnoreCase(Constants.ASC))
					                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).ascending());
					            else
					                pageable = PageRequest.of(page, limit, Sort.by(Constants.COL_EFFFECTIVEDATE).descending());
					        }
					        // status name effective code
					        if (Constants.isNullOrEmpty(proposalStatusSearch.getStatus()) && (Constants.isNullOrEmpty(proposalStatusSearch.getName())) && Constants.isNullOrEmpty(proposalStatusSearch.getCode())
					                && Constants.isNullOrEmpty(proposalStatusSearch.getEffectivedate_st())) {
					        	proposalStatus = proposalStatusRepository.findByStatusAndNameAndCodeAndEffectivedateBetween(
					        			proposalStatusSearch.getStatus(), proposalStatusSearch.getName(),proposalStatusSearch.getCode(), proposalStatusSearch.getEffectivedate_st(), proposalStatusSearch.getEffective_end(), pageable);
					        }
					        // status effective
					        else if (Constants.isNullOrEmpty(proposalStatusSearch.getStatus()) && Constants.isNullOrEmpty(proposalStatusSearch.getEffectivedate_st())) {
					        	proposalStatus = proposalStatusRepository.findByStatusAndEffectivedateBetween(proposalStatusSearch.getStatus(), proposalStatusSearch.getEffectivedate_st(), proposalStatusSearch.getEffective_end(), pageable);
					        }
					        //// name effective
					        else if (Constants.isNullOrEmpty(proposalStatusSearch.getName()) && Constants.isNullOrEmpty(proposalStatusSearch.getEffectivedate_st())) {
					        	proposalStatus = proposalStatusRepository.findByNameAndEffectivedateBetween(proposalStatusSearch.getName(), proposalStatusSearch.getEffectivedate_st(), proposalStatusSearch.getEffective_end(), pageable);
					        }
					        
					        //// code effective
					        else if (Constants.isNullOrEmpty(proposalStatusSearch.getCode()) && Constants.isNullOrEmpty(proposalStatusSearch.getEffectivedate_st())) {
					        	proposalStatus = proposalStatusRepository.findByCodeAndEffectivedateBetween(proposalStatusSearch.getCode(), proposalStatusSearch.getEffectivedate_st(), proposalStatusSearch.getEffective_end(), pageable);
					        }
					        // name Status code
					        else if (Constants.isNullOrEmpty(proposalStatusSearch.getName()) && Constants.isNullOrEmpty(proposalStatusSearch.getStatus()) && Constants.isNullOrEmpty(proposalStatusSearch.getCode())) {
					        	proposalStatus = proposalStatusRepository.findByNameAndStatusAndCode(proposalStatusSearch.getName(), proposalStatusSearch.getStatus(),proposalStatusSearch.getCode(), pageable);
					        }
					        // Only effective
					        else if(Constants.isNullOrEmpty(proposalStatusSearch.getEffectivedate_st()) && Constants.isNullOrEmpty(proposalStatusSearch.getEffective_end())) {
					        	proposalStatus = proposalStatusRepository.findByEffectivedateBetween(proposalStatusSearch.getEffectivedate_st(), proposalStatusSearch.getEffective_end(), pageable);
					        }
					        //only status
					        else if(Constants.isNullOrEmpty(proposalStatusSearch.getStatus())) {
					        	proposalStatus = proposalStatusRepository.findByStatus(proposalStatusSearch.getStatus(), pageable);
					        }
					        //Only name
					        else if(Constants.isNullOrEmpty(proposalStatusSearch.getName())) {
					        	proposalStatus = proposalStatusRepository.findByNameContaining(proposalStatusSearch.getName(),pageable);
					        }
					        //Only code
					        else if(Constants.isNullOrEmpty(proposalStatusSearch.getCode())) {
					        	proposalStatus = proposalStatusRepository.findByCodeContaining(proposalStatusSearch.getCode(),pageable);
					        }
					        else {
					        	proposalStatus = proposalStatusRepository.findAll(pageable);
					        }
					        ProposalStatusSearchResp proposalStatusSearchResp= new ProposalStatusSearchResp();
					        proposalStatusSearchResp.setProposalStatus(proposalStatus);
					        proposalStatusSearchResp.setMessage("Success");
					        proposalStatusSearchResp.setCode(200);
					        return proposalStatusSearchResp;
					    }
					 
		
		
			   
}
