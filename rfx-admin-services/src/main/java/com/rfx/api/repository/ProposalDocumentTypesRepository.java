package com.rfx.api.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.rfx.api.model.TableProposalDocumentTypes;


public interface ProposalDocumentTypesRepository extends JpaRepository<TableProposalDocumentTypes, Integer> {
	
	
	
	Page<TableProposalDocumentTypes> findByNameAndStatusAndCode(String name, String status,String code, Pageable pageable);

    Page<TableProposalDocumentTypes> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableProposalDocumentTypes> findByNameAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    Page<TableProposalDocumentTypes> findByCodeAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableProposalDocumentTypes> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);

    //having
    @Query(value = "SELECT id FROM proposal_document_types WHERE lower(name)= lower(:name)", nativeQuery = true)
    String getIdByProposalDocumentTypeName(@Param("name") String name);
    
  

    @Query(value = "SELECT name FROM proposal_document_types WHERE lower(proposal_document_type_id) = lower(:proposal_document_type_id)", nativeQuery = true)
    String getNameByProposalDocumentTypeId(@Param("proposal_document_type_id") String proposal_document_type_id);

    @Modifying
    @Transactional
    @Query("UPDATE proposal_document_types bu SET bu.name = :name, bu.description = :description, bu.code=:code, bu.effectivedate=:effectivedate, bu.updated_at=:updatedAt WHERE bu.proposal_document_type_id = :proposal_document_type_id")
    int updateProposalDocumentType(@Param("proposal_document_type_id") String proposal_document_type_id, @Param("name") String name, @Param("description") String description, @Param("code") String code, @Param("effectivedate") String effectivedate, @Param("updatedAt") String updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE proposal_document_types bu SET bu.status = :status WHERE bu.proposal_document_type_id = :proposal_document_type_id")
    int updateStatus(@Param("proposal_document_type_id") String proposal_document_type_id, @Param("status") String status);

    //having
    @Query(value = "SELECT proposal_document_type_id FROM proposal_document_types WHERE code=?1", nativeQuery = true)
    String getProposalDocumentIdByCode(String code);
    
    List<TableProposalDocumentTypes> findListByStatus(String status);

    Page<TableProposalDocumentTypes> findByStatus(String status, Pageable pageable);

    Page<TableProposalDocumentTypes> findByNameContaining(String name, Pageable pageable);

    Page<TableProposalDocumentTypes> findByCodeContaining(String code, Pageable pageable);

    Page<TableProposalDocumentTypes> findByStatusAndNameAndCodeAndEffectivedateBetween(String status, String name,String code,  String effectivedate_st, String effective_end, Pageable pageable);
	
	
}
