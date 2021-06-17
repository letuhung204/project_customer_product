
package com.rfx.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rfx.api.model.TableBusinessUnit;

import java.util.List;


public interface BusinessUnitRepository extends JpaRepository<TableBusinessUnit, Integer> {

    Page<TableBusinessUnit> findByNameContainingAndStatusAndCodeAndType(String name, String status,String code,String type, Pageable pageable);

    Page<TableBusinessUnit> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    Page<TableBusinessUnit> findByCodeContainingAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableBusinessUnit> findByTypeContainingAndEffectivedateBetween(String type, String effectivedate_st, String effectivedate_end, Pageable pageable);
 
    Page<TableBusinessUnit> findByNameContainingAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableBusinessUnit> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);

    //having
    @Query(value = "SELECT id FROM business_units WHERE lower(name)= lower(:name)", nativeQuery = true)
    String getIdByBusinessName(@Param("name") String name);

    @Query(value = "SELECT name FROM business_units WHERE business_id=?1", nativeQuery = true)
    String getNameByBusinessId(String business_id);

    @Modifying
    @Transactional
    @Query("UPDATE business_units bu SET bu.name = :name, bu.description = :description, bu.code=:code, bu.type=:type, bu.effectivedate=:effectivedate, bu.updated_at=:updatedAt WHERE bu.business_id = :business_id")
    int updateBusinessUnit(@Param("business_id") String business_id, @Param("name") String name, @Param("description") String description, @Param("code") String code,@Param("type") String type, @Param("effectivedate") String effectivedate, @Param("updatedAt") String updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE business_units bu SET bu.status = :status WHERE bu.business_id = :business_id")
    int updateStatus(@Param("business_id") String business_id, @Param("status") String status);

    //having
    @Query(value = "SELECT business_id FROM business_units WHERE code=?1", nativeQuery = true)
    String getBidByCode(String code);
    
    Page<TableBusinessUnit> findByNameContainingAndStatus(String name,String status, Pageable pageable);
    
    List<TableBusinessUnit> findListByStatusContaining(String status);

    Page<TableBusinessUnit> findByStatusContaining(String status, Pageable pageable);

    Page<TableBusinessUnit> findByNameContaining(String name, Pageable pageable);
    
    Page<TableBusinessUnit> findByCodeContaining(String code, Pageable pageable);
    
    Page<TableBusinessUnit> findByTypeContaining(String type, Pageable pageable);

    Page<TableBusinessUnit> findByStatusAndNameAndCodeAndTypeAndEffectivedateBetween(String status, String name,String code,String type,  String effectivedate_st, String effective_end, Pageable pageable);
}

