package com.rfx.api.repository;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableCompanySecurity;
public interface CompanySecurityRepository extends JpaRepository<TableCompanySecurity, Integer>{


	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContainingAndSecurityDetailsContainingAndCertificationsContainingAndStatus( String businessUnitId, String businessUnitTypeId,String securityName,  String securityDetails,  String certifications,String status,Pageable pageable);
	
	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContainingAndSecurityDetailsContainingAndCertificationsContaining(String businessUnitId, String businessUnitTypeId,String securityName,  String securityDetails,  String certifications,Pageable pageable);

	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContainingAndSecurityDetailsContaining(String businessUnitId, String businessUnitTypeId,String securityName,  String securityDetails,Pageable pageable);

	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityNameContaining(String businessUnitId, String businessUnitTypeId, String securityName,Pageable pageable);
	
	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndSecurityDetailsContaining(String businessUnitId, String businessUnitTypeId, String securityDetails,Pageable pageable);
	
	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndCertificationsContaining(String businessUnitId, String businessUnitTypeId,String certifications,Pageable pageable);

	Page<TableCompanySecurity> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndStatus(String businessUnitId, String businessUnitTypeId,String status,Pageable pageable);
	
	Page<TableCompanySecurity>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(String businessUnitId, String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanySecurity>  findByBusinessUnitIdContaining( String businessUnitId,Pageable pageable);
	
	Page<TableCompanySecurity>  findByBusinessUnitTypeIdContaining(String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanySecurity>  findBySecurityNameContaining(String securityName,Pageable pageable);
	
	Page<TableCompanySecurity>  findBySecurityNameContainingAndStatus(String securityName,String status, Pageable pageable);
	
	Page<TableCompanySecurity>  findBySecurityDetailsContaining(String securityDetails,Pageable pageable);
	
	Page<TableCompanySecurity>  findByCertificationsContaining( String certifications,Pageable pageable);
	
	Page<TableCompanySecurity>  findByStatus( String status,Pageable pageable);
	
}
