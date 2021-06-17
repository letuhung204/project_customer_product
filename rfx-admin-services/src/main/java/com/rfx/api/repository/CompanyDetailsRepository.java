package com.rfx.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rfx.api.model.TableCompanyDetails;

public interface CompanyDetailsRepository extends JpaRepository<TableCompanyDetails, Integer>{
	
	 
	
	Page<TableCompanyDetails>  findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContainingAndHeadQuaterAddressContainingAndCapabilityReferencesContainingAndCompanyClientsContaining(String name,String websiteUrl,String contact,String legalStruct,String background,String history,String revenueGrowth,String keyStaffProfile,String headQuaterAddress,String capabilityReferences,String companyClients, Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContainingAndHeadQuaterAddressContainingAndCapabilityReferencesContaining(String name,String websiteUrl,String contact,String legalStruct,String background,String history,String revenueGrowth,String keyStaffProfile,String headQuaterAddress,String capabilityReferences,Pageable pageable);

	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContainingAndHeadQuaterAddressContaining(String name,String websiteUrl,String contact,String legalStruct,String background,String history,String revenueGrowth,String keyStaffProfile,String headQuaterAddress,Pageable pageable);

	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContainingAndKeyStaffProfileContaining(String name,String websiteUrl,String contact,String legalStruct,String background,String history,String revenueGrowth,String keyStaffProfile,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContainingAndRevenueGrowthContaining(String name,String websiteUrl,String contact,String legalStruct,String background,String history,String revenueGrowth,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContainingAndHistoryContaining(String name,String websiteUrl,String contact,String legalStruct,String background,String history,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContainingAndBackgroundContaining(String name,String websiteUrl,String contact,String legalStruct,String background,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContainingAndLegalStructContaining(String name,String websiteUrl,String contact,String legalStruct,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContainingAndContactContaining(String name,String websiteUrl,String contact,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndWebsiteUrlContaining(String name,String websiteUrl,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContainingAndStatus(String name,String status,Pageable pageable);
	
	Page<TableCompanyDetails> findByNameContaining(String name,Pageable pageable);
	
	Page<TableCompanyDetails> findByWebsiteUrlContaining(String websiteUrl,Pageable pageable);
	
	Page<TableCompanyDetails> findByContactContaining(String contact,Pageable pageable);
	
	Page<TableCompanyDetails> findByLegalStructContaining(String legalStruct,Pageable pageable);
	
	Page<TableCompanyDetails> findByBackgroundContaining(String background,Pageable pageable);
	
	Page<TableCompanyDetails> findByHistoryContaining(String history,Pageable pageable);
	
	Page<TableCompanyDetails> findByRevenueGrowthContaining(String revenueGrowth,Pageable pageable);
	
	Page<TableCompanyDetails> findByKeyStaffProfileContaining(String keyStaffProfile,Pageable pageable);
	
	Page<TableCompanyDetails> findByHeadQuaterAddressContaining(String headQuaterAddress,Pageable pageable);
	
	Page<TableCompanyDetails> findByCapabilityReferencesContaining(String capabilityReferences,Pageable pageable);
	
	Page<TableCompanyDetails> findByCompanyClientsContaining(String companyClients,Pageable pageable);
	
	Page<TableCompanyDetails> findByStatusContaining(String status,Pageable pageable);
	
	

	
	
	
	
	
	@Modifying
	@Transactional
	@Query(value="update company_details set status =:status where id =:companyId")
	void updateStatus(@Param("companyId")Integer companyId,@Param("status")String status);


}
