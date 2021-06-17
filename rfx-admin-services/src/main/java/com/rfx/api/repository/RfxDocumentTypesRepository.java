package com.rfx.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rfx.api.model.TableRfxDocumentTypes;


public interface RfxDocumentTypesRepository extends JpaRepository<TableRfxDocumentTypes, Integer> {
	Page<TableRfxDocumentTypes> findByNameAndStatusAndCode(String name, String status, String code, Pageable pageable);

    Page<TableRfxDocumentTypes> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableRfxDocumentTypes> findByCodeAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    Page<TableRfxDocumentTypes> findByNameAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableRfxDocumentTypes> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);

    //having
    @Query(value = "SELECT id FROM rfx_document_types WHERE lower(name)= lower(:name)", nativeQuery = true)
    String getIdByRfxDocumentTypeName(@Param("name") String name);
    
  

    @Query(value = "SELECT name FROM rfx_document_types WHERE lower(rfx_document_type_id) = lower(:rfx_document_type_id)", nativeQuery = true)
    String getNameByRfxDocumentTypeId(@Param("rfx_document_type_id") String rfx_document_type_id);

    @Modifying
    @Transactional
    @Query("UPDATE rfx_document_types bu SET bu.name = :name, bu.description = :description, bu.code=:code, bu.effectivedate=:effectivedate, bu.updated_at=:updatedAt WHERE bu.rfx_document_type_id = :rfx_document_type_id")
    int updateRfxDocumentType(@Param("rfx_document_type_id") String rfx_document_type_id, @Param("name") String name, @Param("description") String description, @Param("code") String code, @Param("effectivedate") String effectivedate, @Param("updatedAt") String updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE rfx_document_types bu SET bu.status = :status WHERE bu.rfx_document_type_id = :rfx_document_type_id")
    int updateStatus(@Param("rfx_document_type_id") String rfx_document_type_id, @Param("status") String status);

    //having
    @Query(value = "SELECT rfx_document_type_id FROM rfx_document_types WHERE code=?1", nativeQuery = true)
    String getRfxDocumentTypeIdByCode(String code);
    
    List<TableRfxDocumentTypes> findListByStatus(String status);

    Page<TableRfxDocumentTypes> findByStatus(String status, Pageable pageable);

    Page<TableRfxDocumentTypes> findByNameContaining(String name, Pageable pageable);
    
    Page<TableRfxDocumentTypes> findByCodeContaining(String code, Pageable pageable);

    Page<TableRfxDocumentTypes> findByStatusAndNameAndCodeAndEffectivedateBetween(String status, String name,String code, String effectivedate_st, String effective_end, Pageable pageable);

}
