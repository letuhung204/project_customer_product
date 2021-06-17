

package com.rfx.api.utils;

public class Constants {

	public static final String JWT_TOKEN="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmcmFuY2lzamViYXJzb25qQGdtYWlsLmNvbSIsImV4cCI6MTYwMjI3MDQ0NywiaWF0IjoxNjAyMjYzMjQ3fQ.XPKZSUbY33Fj5A81k0LqOkjyA5I8lwVZtx3w63-XoMnOST1jIVlVoLXAHCymHLITSZIL-4drqZ4JUKnc0UCoYQ";
	
    public static final String EMPTY = "";
    public static final String SOMETHING_WRONG = "Something went wrong, pls try again later";
    public static final String CREATE_SUCCESS = "Business Unit created successfully...";
    public static final String UPDATED_SUCCESS = "Business Unit updated successfully...";
    public static final String INVALID_BUSINESS_ID = "Invalid business unit id found...";
    public static final String INVALID_AUTHENTICATION = "Invalid username/password found.";
    public static final String CONTACT_ADMIN = "You dont have rights to access business unit module, Contact admin";
    public static final String PARAMS_EMPTY = "One of the input params is empty";
    public static final String BU_NAME_EXIST = "Business unit name already exist";
    public static final String EMAIL_EXIST = "Email id already exist";
    public static final String BU_ACTIVATED = "Business unit activated successfully...";
    public static final String BU_INACTIVATED = "Business unit inactivated successfully...";

    public static final String INVALID_BUSINESSUNIT_TYPE= "Invalid business unit type id found...";
    public static final String BU_TYPE_ACTIVATED = "Business unit type activated successfully...";
    public static final String BU_TYPE_INACTIVATED = "Business unit type inactivated successfully...";
    
    public static final int ROLE_ADMIN = 1;
    public static final String COL_EFFFECTIVEDATE = "effectivedate";
    public static final String COL_CREATEDATE="createdAt";
    public static final String CODE_EXIST = "Business unit code already exist";
    public static final String ASC = "ASC";
    public static final String STATUS_Y = "Y";
    public static final int ROLE_RECRUITER = 2;
    public static final int ROLE_SUBMITTER = 3;
    public static final int ROLE_TEAMLEAD = 4;
    public static final int PAGE_ID = 1;
    public static final String READ = "RD";
    public static final String READWRITE = "RD/WR";
    public static final int READ_WRITE_CODE = 101;
    public static final int READ_CODE = 100;
    public static final int INVALID_PERMISSION_CODE = 90;

    //Rfx Categories
    public static final String CREATE_SUCCESS_RFX_CATEGORY = "Rfx category created successfully...";
    public static final String UPDATED_SUCCESS_RFX_CATEGORY = "Rfx category updated successfully...";
    public static final String INVALID_RFX_CATEGORY_ID = "Invalid rfx category id found...";
    public static final String CONTACT_ADMIN_RFX_CATEGORY = "You dont have rights to access rfx categories module, Contact admin";
    public static final String RFX_CATG_NAME_EXIST = "Rfx category name already exist";
    public static final String RFX_CATG_ACTIVATED = "Rfx category activated successfully...";
    public static final String RFX_CATG_INACTIVATED = "Rfx category inactivated successfully...";
    public static final String RFX_CATEG_CODE_EXIST = "Rfx category code already exist";
    
    //Rfx Types
    public static final String CREATE_SUCCESS_RFX_TYPE = "Rfx types created successfully...";
    public static final String UPDATED_SUCCESS_RFX_TYPE = "Rfx types updated successfully...";
    public static final String INVALID_RFX_TYPE_ID = "Invalid rfx types id found...";
    public static final String CONTACT_ADMIN_RFX_TYPE = "You dont have rights to access rfx types module, Contact admin";
    public static final String RFX_TYPE_NAME_EXIST = "Rfx type name already exist";
    public static final String RFX_TYPE_ACTIVATED = "Rfx type activated successfully...";
    public static final String RFX_TYPE_INACTIVATED = "Rfx type inactivated successfully...";
    public static final String RFX_TYPE_CODE_EXIST = "Rfx type code already exist";
   
    
    //Rfx Statuses
    public static final String CREATE_SUCCESS_RFX_STATUS = "Rfx status created successfully...";
    public static final String UPDATED_SUCCESS_RFX_STATUS = "Rfx status updated successfully...";
    public static final String INVALID_RFX_STATUS_ID = "Invalid rfx status id found...";
    public static final String CONTACT_ADMIN_RFX_STATUS = "You dont have rights to access rfx status module, Contact admin";
    public static final String RFX_STATUS_NAME_EXIST = "Rfx status name already exist";
    public static final String RFX_STATUS_ACTIVATED = "Rfx status activated successfully...";
    public static final String RFX_STATUS_INACTIVATED = "Rfx status inactivated successfully...";
    public static final String RFX_STATUS_CODE_EXIST = "Rfx status code already exist";
    
    
    //Rfx Documnet Types
    public static final String CREATE_SUCCESS_RFX_DOC_TYPE = "Rfx document type created successfully...";
    public static final String UPDATED_SUCCESS_DOC_TYPE = "Rfx document type updated successfully...";
    public static final String INVALID_RFX_DOC_TYPE_ID = "Invalid rfx document type id found...";
    public static final String CONTACT_ADMIN_RFX_DOC_TYPE = "You dont have rights to access rfx document type module, Contact admin";
    public static final String RFX_DOC_TYPE_NAME_EXIST = "Rfx document type name already exist";
    public static final String RFX_DOC_TYPE_ACTIVATED = "Rfx document type activated successfully...";
    public static final String RFX_DOC_TYPE_INACTIVATED = "Rfx document type inactivated successfully...";
    public static final String RFX_DOC_TYPE_CODE_EXIST = "Rfx document type code already exist";
    
    
    //Rfx Proposal Documnet Types
    public static final String CREATE_SUCCESS_PRO_DOC_TYPE = "Proposal document type created successfully...";
    public static final String UPDATED_SUCCESS_PRO_DOC_TYPE = "Proposal document type updated successfully...";
    public static final String INVALID_PRO_DOC_TYPE_ID = "Invalid proposal document type id found...";
    public static final String CONTACT_ADMIN_PRO_DOC_TYPE = "You dont have rights to access proposal document type module, Contact admin";
    public static final String PRO_DOC_TYPE_NAME_EXIST = "Proposal document type name already exist";
    public static final String PRO_DOC_TYPE_ACTIVATED = "Proposal document type activated successfully...";
    public static final String PRO_DOC_TYPE_INACTIVATED = "Proposal document type inactivated successfully...";
    public static final String PRO_DOC_TYPE_CODE_EXIST = "Proposal document type code already exist";
    
    
    
    //Rfx Proposal Documnet Types
    public static final String CREATE_SUCCESS_PRO_STAT = "Proposal status created successfully...";
    public static final String UPDATED_SUCCESS_PRO_STAT = "Proposal status updated successfully...";
    public static final String INVALID_PRO_STAT_ID = "Invalid Proposal status id found...";
    public static final String CONTACT_ADMIN_PRO_STAT = "You dont have rights to access status module, Contact admin";
    public static final String PRO_STAT_NAME_EXIST = "Proposal status name already exist";
    public static final String PRO_STAT_ACTIVATED = "Proposal status activated successfully...";
    public static final String PRO_STAT_INACTIVATED = "Proposal status inactivated successfully...";
    public static final String PRO_STAT_CODE_EXIST = "Proposal status code already exist";
    
    
    
    //Content Entries
    public static final String CONTACT_ADMIN_CONTENT_ENTRY = "You dont have rights to access content entries module, Contact admin";
    public static final String CREATE_SUCCESS_CONTENT_ENTRY = "Content entry created successfully...";
    public static final String INVALID_CONTENT_ENTRY_ID = "Invalid content entry id found...";
    public static final String UPDATED_SUCCESS_CONTENT_ENTRY = "Content entry updated successfully...";

    public static final String CE_STATUS_ACTIVATED = "Content Entry status activated successfully...";
    public static final String CE_STATUS_INACTIVATED = "Content Entry status inactivated successfully...";
    
    //keyword Entries
    public static final String CONTACT_ADMIN_KEYWORD_ENTRY="You dont have rights to access keyword entries module, Contact admin";
    public static final String CREATE_SUCCESS_KEYWORD_ENTRY = "Keyword entry created successfully...";
    public static final String UPDATED_SUCCESS_KEYWORD_ENTRY = "Keyword entry updated successfully...";
    public static final String BIDSOURCE_AND_KEYWORD_ADDED = "Keyword entry for the given Bid source added successfully...";
    public static final String BIDSOURCE_FOR_KEYWORD_EXIST="Keyword for the given Bid source already exist!";
    public static final String BIDSOURCE_FOR_KEYWORD_INVALID="Keyword for the given Bid source is invalid!";
    public static final String INVALID_KEYWORD_ENTRY_ID="Invalid Keyword Entry id";
    public static final String KEYWORD_ENTRY_EXIST="Keyword entry already exist!";
    public static final String KE_STATUS_ACTIVATED = "Keyword Entry status activated successfully...";
    public static final String KE_STATUS_INACTIVATED = "Keyword Entry status inactivated successfully...";
    
    
    //Document Entries
    public static final String DOCUMENT_ENTRY_TITLE_EXIST="Document Title already exist";
    public static final String INVALID_DOCUMENT_ENTRY_ID="Invalid Document Entry id";
    public static final String CREATE_SUCCESS_DOCUMENT_ENTRY = "Document entry created successfully...";
    public static final String UPDATE_SUCCESS_DOCUMENT_ENTRY = "Document entry updated successfully...";
    public static final String CONTACT_ADMIN_DOCUMENT_ENTRY="You dont have rights to access document entries module, Contact admin";
    public static final String COL_CREATEDAT_DOCUMENT_ENTRY = "created_at";
    
    
    public static final String COMPNY_DETAILS_CRT= "Company details created successfully!";
    public static final String COMPNY_DETAILS_UPDT= "Company details updated successfully!";
    public static final String CONTACT_ADMIN_COMPNY_DETAILS="You dont have rights to access company details module, Contact admin";
    public static final String INVALID_COMPANY_ID="Invalid company id found";
    public static final String CMPD_ACTIVATED = "Company details activated successfully...";
    public static final String CMPD_INACTIVATED = "Company details inactivated successfully...";
    
    
    public static final String CONTACT_ADMIN_COMPNY_CAPABIL="You dont have rights to access company capabilities module, Contact admin";
    
    public static final String COMPNY_PROD_ADDED="Product added successfully!";
    public static final String COMPNY_PROD_UPTD="Product updated successfully!";
    public static final String COMPNY_PROD_ACTIVE="Product activated successfully";
    public static final String COMPNY_PROD_INACTIVE="Product inactivated successfully";
    public static final String COMPNY_PROD_ID_INVALID="Invalid product id found";
    public static final String COMPNY_PROD_DELETED="Product deleted successfully!";

    public static final String COMPNY_PROD_MODULE_DELETED="Product Module deleted successfully!";

    public static final String COMPNY_SERV_ADDED="Service added successfully!";
    public static final String COMPNY_SERV_UPTD="Service updated successfully!";
    public static final String COMPNY_SERV_ACTIVE="Service activated successfully";
    public static final String COMPNY_SERV_INACTIVE="Service inactivated successfully";
    public static final String COMPNY_SERV_ID_INVALID="Invalid service id found";
    
    public static final String COMPNY_PROCSS_ADDED="Process added successfully!";
    public static final String COMPNY_PROCSS_UPTD="Process updated successfully!";
    public static final String COMPNY_PROCSS_ACTIVE="Process activated successfully";
    public static final String COMPNY_PROCSS_INACTIVE="Process inactivated successfully";
    public static final String COMPNY_PROCSS_ID_INVALID="Invalid Process id found";
    

    public static final String COMPNY_METHLGY_ADDED="Methodology added successfully!";
    public static final String COMPNY_METHLGY_UPTD="Methodology updated successfully!";
    public static final String COMPNY_METHLGY_ACTIVE="Methodology activated successfully";
    public static final String COMPNY_METHLGY_INACTIVE="Methodology inactivated successfully";
    public static final String COMPNY_METHLGY_ID_INVALID="Invalid Methodology id found";
    
    
    public static final String COMPNY_SECR_ADDED="Security added successfully!";
    public static final String COMPNY_SECR_UPTD="Security updated successfully!";
    public static final String COMPNY_SECR_ACTIVE="Security activated successfully";
    public static final String COMPNY_SECR_INACTIVE="Security inactivated successfully";
    public static final String COMPNY_SECR_ID_INVALID="Invalid Security id found";
    
    public static final String COMPNY_QC_ADDED="Quality Control added successfully!";
    public static final String COMPNY_QC_UPTD="Quality Control updated successfully!";
    public static final String COMPNY_QC_ACTIVE="Quality Control activated successfully";
    public static final String COMPNY_QC_INACTIVE="Quality Control inactivated successfully";
    public static final String COMPNY_QC_ID_INVALID="Invalid Quality Control id found";
    
    
    
    public static final String SERV_MOD_ADDED="Service Module added successfully!";
    public static final String SERV_MOD_UPTD="Service Module updated successfully!";
    public static final String SERV_MOD_ACTIVE="Service Module activated successfully";
    public static final String SERV_MOD_INACTIVE="Service Module  inactivated successfully";
    public static final String SERV_MOD_ID_INVALID="Invalid Service Module id found";
    
    public static final String COMPNY_MOD_ADDED="Product Module added successfully!";
    public static final String COMPNY_MOD_UPTD="Product Module updated successfully!";
    public static final String COMPNY_MOD_ACTIVE="Product Module activated successfully";
    public static final String COMPNY_MOD_INACTIVE="Product Module  inactivated successfully";
    public static final String COMPNY_MOD_ID_INVALID="Invalid Product Module id found";
    
    public static final String WORD_TEMP_ADDED="Word Template added successfully!";
    public static final String WORD_TEMP_UPTD="Word Template updated successfully!";
    public static final String WORD_TEMP_DELETE="Word Template deleted successfully!";
    public static final String WORD_TEMP_ACTIVE="Word Template activated successfully";
    public static final String WORD_TEMP_INACTIVE="Word Template  inactivated successfully";
    public static final String WORD_TEMP_ID_INVALID="Invalid Word Template id found";
    public static final String CONTACT_ADMIN_WORD_TEMP="You dont have rights to access word template module, Contact admin";
    
    public static final String INVALID_PROD_FIELD_ID="Invalid product field id found!";
    public static final String PROD_FIELD_ACTIVE="Product field activated successfully";
    public static final String PROD_FIELD_INACTIVE="Product field inactivated successfully";
    public static final String UNABLE_TO_GET_TYPE="Unable to get field type";
    
    public static final String INVALID_SERV_FIELD_ID="Invalid  service field id found!";
    public static final String SERV_FIELD_ACTIVE="Service field activated successfully";
    public static final String SERV_FIELD_INACTIVE="Service field inactivated successfully";


    
    
    
    //ErrorCodes
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE_400 = 400;
    public static final int ERROR_CODE_403 = 403;
    public static final int ERROR_CODE_204 = 204;
    
    // True returs not empty or not null
    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return true;
        return false;
    }

}
