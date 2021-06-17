package com.rfx.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.rfx.api.model.TableDocumentEntries;
import com.rfx.api.model.TableKeywordEntries;

public interface DocumentEntriesRepository extends JpaRepository<TableDocumentEntries, Integer>{

	//document_title  rfx_type_id clientname rfx_category_id
	TableDocumentEntries findById(int id);
	
	
    Page<TableDocumentEntries> findByRfxDocumentTypeIdContainingAndTitleContainingAndClientnameContainingAndStatus(String rfx_document_type_id,String title,String clientname,String status, Pageable pageable);
	
    Page<TableDocumentEntries> findByRfxDocumentTypeIdContainingAndTitleContainingAndStatus(String rfx_document_type_id,String title,String status, Pageable pageable);
	
    Page<TableDocumentEntries> findByRfxDocumentTypeIdContainingAndStatus(String rfx_document_type_id,String status, Pageable pageable);
	

    Page<TableDocumentEntries> findByTitleContainingAndStatus( String title,String status, Pageable pageable);
    
    Page<TableDocumentEntries> findByClientnameContainingAndStatus( String clientname,String status, Pageable pageable);
    
    
    Page<TableDocumentEntries> findByTitleContaining( String title, Pageable pageable);
      
    Page<TableDocumentEntries> findByClientnameContaining(String clientname, Pageable pageable);
    
    Page<TableDocumentEntries> findByRfxDocumentTypeIdContaining(String rfx_document_type_id, Pageable pageable);
    
    Page<TableDocumentEntries> findByStatus(String status, Pageable pageable);
     
	@Query(value = "SELECT * FROM document_entries WHERE lower(document_entry_id)= lower(:document_entry_id)", nativeQuery = true)
	TableDocumentEntries findByDocumentEntryId(String document_entry_id);
	
    @Query(value = "SELECT id FROM document_entries WHERE lower(title)= lower(:title)", nativeQuery = true)
    String getIdByTitle(@Param("title") String title);
    
    @Query(value = "SELECT id FROM document_entries WHERE lower(document_entry_id)= lower(:document_entry_id)", nativeQuery = true)
    String getIdByDocumentEntryId(@Param("document_entry_id") String document_entry_id);
    
    @Query(value = "SELECT title FROM document_entries WHERE lower(document_entry_id) = lower(:document_entry_id)", nativeQuery = true)
    String getTitleByDocumentEntryId(@Param("document_entry_id") String document_entry_id);
    
    
    
   

}
