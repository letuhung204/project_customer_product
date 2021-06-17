package com.rfx.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rfx.api.model.TableKeywordEntries;
import com.rfx.api.model.TableKeywordEntries;

public interface KeywordEntryRepository extends JpaRepository<TableKeywordEntries, Integer> {
	
    Page<TableKeywordEntries> findByBidSourceAndRfxCategoryIdAndExclusionsAndBusinessIdAndEffectivedateBetween(String bid_source, String rfx_category_id,String exclusions,String business_id,  String effectivedate_st, String effective_end, Pageable pageable);
	
    //bid_source effective
    Page<TableKeywordEntries> findByBidSourceAndEffectivedateBetween(String bid_source, String effectivedate_st, String effectivedate_end, Pageable pageable);
  
    //rfx category effective
    Page<TableKeywordEntries> findByRfxCategoryIdAndEffectivedateBetween(String rfx_category_id, String effectivedate_st, String effectivedate_end, Pageable pageable);
  
    //business_id effective
    Page<TableKeywordEntries> findByBusinessIdAndEffectivedateBetween(String business_id, String effectivedate_st, String effectivedate_end, Pageable pageable);
	
    //Exclusions effective
    Page<TableKeywordEntries> findByExclusionsAndEffectivedateBetween(String exclusions, String effectivedate_st, String effectivedate_end, Pageable pageable);
	
    
    //bid_source keyword  exclusions business_id 
    Page<TableKeywordEntries> findByBidSourceAndRfxCategoryIdAndExclusionsAndBusinessId(String bid_source, String rfx_category_id,String exclusions,String business_id, Pageable pageable);
    
    Page<TableKeywordEntries> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);

    
    Page<TableKeywordEntries> findByBidSourceContaining(String bid_source, Pageable pageable);
    
    Page<TableKeywordEntries> findByRfxCategoryIdContaining(String rfx_category_id, Pageable pageable);
    
    Page<TableKeywordEntries> findByExclusionsContaining(String exclusions, Pageable pageable);
    
    Page<TableKeywordEntries> findByBusinessIdContaining(String business_id, Pageable pageable);
    
    Page<TableKeywordEntries> findByStatus(String status, Pageable pageable);
    
    
    @Query(value = "select distinct bid_source from keyword_entries", nativeQuery = true)
	List<String> getDistinctBidSource(); 
    
    
    @Modifying
    @Transactional
    @Query("UPDATE keyword_entries SET status = :status WHERE keyword_entry_id = :keywordEntryId")
    int updateStatus(@Param("keywordEntryId") String keywordEntryId, @Param("status") String status);
	
	
	@Query(value = "SELECT distinct rfx_category_id FROM keyword_entries WHERE bid_source=:bid_source ", nativeQuery = true)
	List<String> getDistinctRfxCatrgyByBidSource(@Param("bid_source") String bid_source);
    
	
	@Query(value = "SELECT id FROM keyword_entries WHERE bid_source=?1 and rfx_category_id=?2 and exclusions=?3 and business_id=?4", nativeQuery = true)
	String keywordEntryExist(String bid_source,String rfx_category_id,String exclusion,String business_id);
	
	
	@Query(value = "SELECT id FROM keyword_entries WHERE keyword_entry_id=?1", nativeQuery = true)
	String getIdByKeywordEntryId(String keyword_entry_id);
	
	@Modifying
    @Transactional
    @Query("update  keyword_entries set bid_source=:bid_source,rfx_category_id =:rfx_category_id,exclusions =:exclusions,business_id =:business_id,updated_at =:updated_at where keyword_entry_id=:keyword_entry_id")
    int updateKeywordEntry(@Param("keyword_entry_id") String keyword_entry_id, @Param("bid_source") String bid_source, @Param("rfx_category_id") String rfx_category_id,@Param("exclusions") String exclusions,@Param("business_id") String business_id,@Param("updated_at") String updated_at);

	
	
}
