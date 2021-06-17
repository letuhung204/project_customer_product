package com.rfx.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import com.rfx.api.model.TableRfxStatuses;
import com.rfx.api.model.TableRfxStatuses;

public interface RfxStatusesRepository  extends JpaRepository<TableRfxStatuses, Integer> {
	    Page<TableRfxStatuses> findByNameAndStatus(String name, String status, Pageable pageable);
	    
	    Page<TableRfxStatuses> findByNameAndStatusAndCode(String name, String status,String code, Pageable pageable);

	    Page<TableRfxStatuses> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);

	    Page<TableRfxStatuses> findByCodeAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);
	    
	    Page<TableRfxStatuses> findByNameAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);

	    Page<TableRfxStatuses> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);
	    
	    @Query(value = "SELECT id FROM rfx_statuses WHERE lower(name)= lower(:name)", nativeQuery = true)
	    String getIdByRfxStatusName(@Param("name") String name);
	    
	    

	    @Query(value = "SELECT name FROM rfx_statuses WHERE lower(rfx_status_id) = lower(:rfx_status_id)", nativeQuery = true)
	    String getNameByRfxStatusId(@Param("rfx_status_id") String rfx_status_id);

	    @Modifying
	    @Transactional
	    @Query("UPDATE rfx_statuses bu SET bu.name = :name, bu.description = :description, bu.code=:code, bu.effectivedate=:effectivedate, bu.updated_at=:updatedAt WHERE bu.rfx_status_id = :rfx_status_id")
	    int updateRfxStatus(@Param("rfx_status_id") String rfx_status_id, @Param("name") String name, @Param("description") String description, @Param("code") String code, @Param("effectivedate") String effectivedate, @Param("updatedAt") String updatedAt);

	    @Modifying
	    @Transactional
	    @Query("UPDATE rfx_statuses bu SET bu.status = :status WHERE bu.rfx_status_id = :rfx_status_id")
	    int updateStatus(@Param("rfx_status_id") String rfx_status_id, @Param("status") String status);

	    //having
	    @Query(value = "SELECT rfx_status_id FROM rfx_statuses WHERE code=?1", nativeQuery = true)
	    String getRfxStatusIdByCode(String code);
	    
	    List<TableRfxStatuses> findListByStatus(String status);

	    Page<TableRfxStatuses> findByStatus(String status, Pageable pageable);

	    Page<TableRfxStatuses> findByNameContaining(String name, Pageable pageable);
	    
	    Page<TableRfxStatuses> findByCodeContaining(String code, Pageable pageable);

	    Page<TableRfxStatuses> findByStatusAndNameAndCodeAndEffectivedateBetween(String status, String name,String code, String effectivedate_st, String effective_end, Pageable pageable);

}
