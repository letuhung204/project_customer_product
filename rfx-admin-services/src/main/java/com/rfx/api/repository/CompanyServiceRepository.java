package com.rfx.api.repository;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.rfx.api.model.TableCompanyServices;


public interface CompanyServiceRepository extends JpaRepository<TableCompanyServices, Integer>{

	

	Page<TableCompanyServices> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContainingAndServiceDescriptionContainingAndNigpUnpscCodesContainingAndStatus(String businessUnitId , String businessUnitTypeId,String serviceName,String serviceCategory,String serviceDescription,String nigpUnpscCodes,String status,Pageable pageable);
	
	Page<TableCompanyServices> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContainingAndServiceDescriptionContainingAndNigpUnpscCodesContaining(String businessUnitId , String businessUnitTypeId,String serviceName,String serviceCategory,String serviceDescription,String nigpUnpscCodes,Pageable pageable);
	
    Page<TableCompanyServices> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContainingAndServiceDescriptionContaining(String businessUnitId , String businessUnitTypeId,String serviceName,String serviceCategory,String serviceDescription ,Pageable pageable);
    
    Page<TableCompanyServices> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContainingAndServiceCategoryContaining(String businessUnitId , String businessUnitTypeId,String serviceName,String serviceCategory,Pageable pageable);
    
    Page<TableCompanyServices> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndServiceNameContaining(String businessUnitId , String businessUnitTypeId,String serviceName,Pageable pageable);
    
    Page<TableCompanyServices> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(String businessUnitId , String businessUnitTypeId,Pageable pageable);
    
    Page<TableCompanyServices> findByBusinessUnitIdContaining(String businessUnitId ,Pageable pageable);
    
    Page<TableCompanyServices> findByBusinessUnitTypeIdContaining(String businessUnitTypeId,Pageable pageable);
    
    Page<TableCompanyServices> findByServiceNameContaining(String serviceName ,Pageable pageable);
    
    Page<TableCompanyServices> findByServiceNameContainingAndStatus(String serviceName ,String status ,Pageable pageable);
    
    Page<TableCompanyServices> findByServiceDescriptionContaining(String serviceDescription ,Pageable pageable);
    
    Page<TableCompanyServices> findByServiceCategoryContaining(String serviceCategory,Pageable pageable);
    
    Page<TableCompanyServices> findByNigpUnpscCodesContaining(String nigpUnpscCodes,Pageable pageable);
    
	Page<TableCompanyServices> findByStatus(String status,Pageable pageable);
	
}
