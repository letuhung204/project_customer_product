package com.rfx.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import com.rfx.api.model.TableRfxTypes;

public interface RfxTypesRepository extends JpaRepository<TableRfxTypes, Integer> {
	
    Page<TableRfxTypes> findByNameContainingAndStatusAndCode(String name, String status,String code, Pageable pageable);

    Page<TableRfxTypes> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    Page<TableRfxTypes> findByCodeContainingAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableRfxTypes> findByNameContainingAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableRfxTypes> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);
    
    @Query(value = "SELECT id FROM rfx_types WHERE lower(name)= lower(:name)", nativeQuery = true)
    String getIdByRfxTypesName(@Param("name") String name);
    
    @Query(value = "SELECT count(*) as ids_count FROM rfx_types WHERE lower(name)= lower(:name)", nativeQuery = true)
    int getCountByRfxTypesUserName(@Param("name") String name);

    @Query(value = "SELECT name FROM rfx_types WHERE lower(rfx_type_id) = lower(:rfx_type_id)", nativeQuery = true)
    String getNameByRfxTypesId(@Param("rfx_type_id") String rfx_type_id);

    @Modifying
    @Transactional
    @Query("UPDATE rfx_types bu SET bu.name = :name, bu.description = :description, bu.code=:code, bu.effectivedate=:effectivedate, bu.updated_at=:updatedAt WHERE bu.rfx_type_id = :rfx_type_id")
    int updateRfxTypes(@Param("rfx_type_id") String rfx_type_id, @Param("name") String name, @Param("description") String description, @Param("code") String code, @Param("effectivedate") String effectivedate, @Param("updatedAt") String updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE rfx_types bu SET bu.status = :status WHERE bu.rfx_type_id = :rfx_type_id")
    int updateStatus(@Param("rfx_type_id") String rfx_type_id, @Param("status") String status);

    //having
    @Query(value = "SELECT rfx_type_id FROM rfx_types WHERE code=?1", nativeQuery = true)
    String getRfxTypeIdByCode(String code);
    
    List<TableRfxTypes> findListByStatus(String status);

    Page<TableRfxTypes> findByStatus(String status, Pageable pageable);
    

    Page<TableRfxTypes> findByCodeContaining(String code, Pageable pageable);

    Page<TableRfxTypes> findByNameContaining(String name, Pageable pageable);

    Page<TableRfxTypes> findByStatusAndNameContainingAndCodeAndEffectivedateBetween(String status, String name,String code, String effectivedate_st, String effective_end, Pageable pageable);



}
