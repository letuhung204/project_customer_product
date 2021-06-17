package com.rfx.api.repository;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableCompanyQualityControl;

public interface QualityControlRepository extends JpaRepository<TableCompanyQualityControl, Integer>{
	


      
	
	
	Page<TableCompanyQualityControl>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContainingAndQualityControlDetailsContainingAndCertificationsContainingAndStatus(String businessUnitId, String businessUnitTypeId, String qualityControlName,  String qualityControlDetails,  String certifications,String status,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContainingAndQualityControlDetailsContainingAndCertificationsContaining(String businessUnitId, String businessUnitTypeId,String qualityControlName,  String qualityControlDetails,  String certifications,Pageable pageable);

	Page<TableCompanyQualityControl>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContainingAndQualityControlDetailsContaining(String businessUnitId, String businessUnitTypeId, String qualityControlName,  String qualityControlDetails,Pageable pageable);

	Page<TableCompanyQualityControl>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndQualityControlNameContaining(String businessUnitId, String businessUnitTypeId, String qualityControlName,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(String businessUnitId, String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByBusinessUnitTypeIdContaining( String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByBusinessUnitIdContaining( String businessUnitId,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByQualityControlNameContainingAndStatus(  String qualityControlName,String status ,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByQualityControlNameContaining(  String qualityControlName,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByQualityControlDetailsContaining( String qualityControlDetails,Pageable pageable);
	
	Page<TableCompanyQualityControl>  findByCertificationsContaining(String certifications,Pageable pageable);
	
    Page<TableCompanyQualityControl> findByStatus(String status,Pageable pageable);
}
