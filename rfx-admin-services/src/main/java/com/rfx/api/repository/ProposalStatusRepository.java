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
import com.rfx.api.model.TableProposalStatus;

public interface ProposalStatusRepository extends JpaRepository<TableProposalStatus, Integer>{

	
	 @Query(value = "SELECT * FROM proposal_status WHERE lower(proposal_status_id)= lower(:proposal_status_id)", nativeQuery = true)
	 TableProposalStatus getInfoProposalStatusId(@Param("proposal_status_id") String proposalStatusId);
	
	
	 @Query(value = "SELECT id FROM proposal_status WHERE lower(proposal_status_id)= lower(:proposal_status_id)", nativeQuery = true)
	 String getIdByProposalStatusId(@Param("proposal_status_id") String proposalStatusId);
	 
	 @Query(value = "SELECT id FROM proposal_status WHERE lower(name)= lower(:name)", nativeQuery = true)
	 String getIdByProposalStatusName(@Param("name") String name);
	 
	 @Query(value = "SELECT proposal_status_id FROM proposal_status WHERE code=?1", nativeQuery = true)
	 String getProposalStatusIdByCode(String code);
	 
	 
	 @Query(value = "SELECT name FROM proposal_status WHERE lower(proposal_status_id) = lower(:proposal_status_id)", nativeQuery = true)
	 String getNameByProposalStatusId(@Param("proposal_status_id") String proposalStatusId);
	 
    @Modifying
    @Transactional
    @Query("UPDATE proposal_status  SET  status = :status WHERE proposal_status_id = :proposal_status_id")
    int updateStatus(@Param("proposal_status_id") String proposal_status_id, @Param("status") String status);

    List<TableProposalStatus> findListByStatus(String status);

    Page<TableProposalStatus> findByStatus(String status, Pageable pageable);

    Page<TableProposalStatus> findByNameContaining(String name, Pageable pageable);

    Page<TableProposalStatus> findByCodeContaining(String code, Pageable pageable);

    Page<TableProposalStatus> findByStatusAndNameAndCodeAndEffectivedateBetween(String status, String name,String code,  String effectivedate_st, String effective_end, Pageable pageable);
	
    Page<TableProposalStatus> findByNameAndStatusAndCode(String name, String status,String code, Pageable pageable);

    Page<TableProposalStatus> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableProposalStatus> findByNameAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    Page<TableProposalStatus> findByCodeAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableProposalStatus> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);

	 
	 
}
