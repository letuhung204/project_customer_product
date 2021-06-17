package com.rfx.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableCompanyMethodology;

public interface CompanyMethodologyRepo extends JpaRepository<TableCompanyMethodology, Integer>{

	Page<TableCompanyMethodology>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndMethodologyNameContainingAndMethodologyDetailsContainingAndCertificationsContainingAndStatus(String businessUnitId,String businessUnitTypeId, String methodologyName,  String methodologyDetails,  String certifications,String status,Pageable pageable);
	
	Page<TableCompanyMethodology>  findByBusinessUnitIdAndBusinessUnitTypeIdAndMethodologyNameAndMethodologyDetailsAndCertifications(String businessUnitId,String businessUnitTypeId, String methodologyName,  String methodologyDetails,  String certifications,Pageable pageable);

	Page<TableCompanyMethodology>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndMethodologyNameContainingAndMethodologyDetailsContaining(String businessUnitId,String businessUnitTypeId, String methodologyName,  String methodologyDetails,Pageable pageable);

	Page<TableCompanyMethodology>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndMethodologyNameContaining(String businessUnitId,String businessUnitTypeId, String methodologyName,Pageable pageable);

	Page<TableCompanyMethodology>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(String businessUnitId,String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanyMethodology>  findByBusinessUnitIdContaining(String businessUnitId,Pageable pageable);
	
	Page<TableCompanyMethodology>  findByBusinessUnitTypeIdContaining(String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanyMethodology>  findByMethodologyNameContaining( String methodologyName,Pageable pageable);
	
	Page<TableCompanyMethodology>  findByMethodologyDetailsContaining( String methodologyDetails,Pageable pageable);
	
	Page<TableCompanyMethodology>  findByCertificationsContaining(String certifications,Pageable pageable);
	
	Page<TableCompanyMethodology> findByMethodologyNameContainingAndStatus( String methodologyName,String status,Pageable pageable);

	Page<TableCompanyMethodology> findByStatus(String status,Pageable pageable);
}
