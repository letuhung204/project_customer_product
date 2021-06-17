package com.rfx.api.repository;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.rfx.api.model.TableCompanyProducts;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProductsRepo extends JpaRepository<TableCompanyProducts, Integer>{

	
	Page<TableCompanyProducts> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContainingAndProductFunctionContainingAndProductDetailsContainingAndNigpUnpscCodesContaining( String businessUnitId , String businessUnitTypeId,String productName,String productFunction,String productDetails,String nigpUnpscCodes,Pageable pageable);
	
	Page<TableCompanyProducts> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContainingAndProductFunctionContainingAndProductDetailsContaining( String businessUnitId , String businessUnitTypeId,String productName,String productFunction,String productDetails,Pageable pageable);
	
	Page<TableCompanyProducts> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContainingAndProductFunctionContaining( String businessUnitId , String businessUnitTypeId,String productName,String productFunction,Pageable pageable);
	
	Page<TableCompanyProducts> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContainingAndProductNameContaining( String businessUnitId , String businessUnitTypeId,String productName,Pageable pageable);
	
	Page<TableCompanyProducts> findByBusinessUnitIdContainingAndBusinessUnitTypeIdContaining( String businessUnitId , String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanyProducts> findByProductNameContainingAndStatus(String productName,String status,Pageable pageable);
	
	Page<TableCompanyProducts> findByBusinessUnitTypeIdContaining(String businessUnitTypeId,Pageable pageable);
	
	Page<TableCompanyProducts> findByBusinessUnitIdContaining( String businessUnitId ,Pageable pageable);
	
	Page<TableCompanyProducts> findByProductNameContaining(String productName,Pageable pageable);
	
	Page<TableCompanyProducts> findByProductFunctionContaining(String productFunction,Pageable pageable);
	
	Page<TableCompanyProducts> findByProductDetailsContaining(String productDetails,Pageable pageable);
	
	Page<TableCompanyProducts>  findByNigpUnpscCodesContaining(String nigpUnpscCodes,Pageable pageable);
	
	Page<TableCompanyProducts>  findByStatus(String status,Pageable pageable);
	
}
