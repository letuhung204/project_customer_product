package com.rfx.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rfx.api.model.TableBusinessUnit;
import com.rfx.api.model.TableContentEntries;
import com.rfx.api.model.TableRfxTypes;


public interface ContentEntriesRepository extends JpaRepository<TableContentEntries, Integer>{

	//name topic keyword  rfx_category_id effective
	Page<TableContentEntries> findByNameContainingAndTopicContainingAndKeywordContainingAndRfxCategoryIdContainingAndEffectivedateBetween(String name, String topic,String keyword,String rfx_category_id,  String effectivedate_st, String effective_end, Pageable pageable);
		
    //name effective
    Page<TableContentEntries> findByNameContainingAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);
  
    //topic effective
    Page<TableContentEntries> findByTopicContainingAndEffectivedateBetween(String topic, String effectivedate_st, String effectivedate_end, Pageable pageable);
  
    //keyword effective
    Page<TableContentEntries> findByKeywordContainingAndEffectivedateBetween(String keyword, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    //rfx_category_id effective
    Page<TableContentEntries> findByRfxCategoryIdContainingAndEffectivedateBetween(String rfx_category_id, String effectivedate_st, String effectivedate_end, Pageable pageable);
	
    
    //name topic keyword rfx_category_id
    Page<TableContentEntries> findByNameContainingAndTopicContainingAndKeywordContainingAndRfxCategoryIdContaining(String name, String topic,String keyword,String rfx_category_id, Pageable pageable);
    
    Page<TableContentEntries> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);

    Page<TableContentEntries> findByNameContainingAndStatus(String name,String status, Pageable pageable);
    
    Page<TableContentEntries> findByNameContaining(String name, Pageable pageable);
    
    Page<TableContentEntries> findByTopicContaining(String topic, Pageable pageable);
    
    Page<TableContentEntries> findByKeywordContaining(String keyword, Pageable pageable);
    
    Page<TableContentEntries> findByRfxCategoryIdContaining(String rfx_category_id, Pageable pageable);
    
    Page<TableContentEntries> findByStatus(String status, Pageable pageable);
    
   
    @Query(value = "SELECT id FROM content_entries WHERE content_entry_id=?1", nativeQuery = true)
    String getIdByContentEntryId(String content_entry_id);
  
    @Modifying
    @Transactional
    @Query("update content_entries ce set ce.name=:name,ce.keyword=:keyword,ce.topic=:topic,ce.content=:content,ce.effectivedate=:effectivedate,ce.rfx_category_id=:rfx_category_id,ce.updated_at=:updated_at  where ce.content_entry_id=:content_entry_id  ")
    int updateContentEntry(@Param("content_entry_id") String content_entry_id, @Param("name") String name, @Param("keyword") String keyword, @Param("topic") String topic, @Param("content") String content, @Param("effectivedate") String effectivedate,@Param("rfx_category_id") String rfx_category_id,@Param("updated_at") String updated_at);

    @Modifying
    @Transactional
    @Query("UPDATE content_entries SET status = :status WHERE content_entry_id = :contentEntryId")
    int updateStatus(@Param("contentEntryId") String contentEntryId, @Param("status") String status);
    
	
}
