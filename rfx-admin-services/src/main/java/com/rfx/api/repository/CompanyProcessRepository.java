package com.rfx.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableCompanyProcess;

public interface CompanyProcessRepository extends JpaRepository<TableCompanyProcess, Integer>{
	
	
	
	
	
    Page<TableCompanyProcess> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContainingAndProcessDetailsContainingAndNigpUnpscCodesContainingAndStatus(String businessUnitId,String businessUnitTypeId,String processName,
    		String processFunction,String processDetails,String nigpUnpscCodes,String status,Pageable pageable);
    
    Page<TableCompanyProcess> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContainingAndProcessDetailsContainingAndNigpUnpscCodesContaining(String businessUnitId,String businessUnitTypeId,String processName,
    		String processFunction,String processDetails,String nigpUnpscCodes,Pageable pageable);
    
    
    
    
    Page<TableCompanyProcess> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContainingAndProcessDetailsContaining(String businessUnitId,String businessUnitTypeId,String processName,
    		String processFunction,String processDetails,Pageable pageable);
    
    Page<TableCompanyProcess> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContainingAndProcessFunctionContaining(String businessUnitId,String businessUnitTypeId,String processName,String processFunction,Pageable pageable);
    
    Page<TableCompanyProcess> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProcessNameContaining(String businessUnitId,String businessUnitTypeId,String processName,Pageable pageable);
    
    Page<TableCompanyProcess> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining(String businessUnitId,String businessUnitTypeId,Pageable pageable);
    
    Page<TableCompanyProcess> findByBusinessUnitIdContaining(String businessUnitId,Pageable pageable);
    
    Page<TableCompanyProcess> findByBusinessUnitTypeIdContaining(String businessUnitTypeId,Pageable pageable);
    
    Page<TableCompanyProcess> findByProcessNameContainingAndStatus(String processName,String status,Pageable pageable);
    
    Page<TableCompanyProcess> findByProcessNameContaining(String processName,Pageable pageable);
    
    Page<TableCompanyProcess> findByProcessFunctionContaining(String processFunction,Pageable pageable);
    
    Page<TableCompanyProcess> findByProcessDetailsContaining(String processDetails,Pageable pageable);
    
    Page<TableCompanyProcess> findByNigpUnpscCodesContaining(String nigpUnpscCodes,Pageable pageable);
    
    Page<TableCompanyProcess> findByStatus(String nigpUnpscCodes,Pageable pageable);
    
}
