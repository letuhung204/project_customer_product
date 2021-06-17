package com.rfx.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rfx.api.model.TableBidSourceAndKeyword;

public interface BidSourceAndKeywordRepository extends JpaRepository<TableBidSourceAndKeyword, Integer>{
	
	@Query(value = "SELECT id FROM bid_source_and_keywords_info WHERE bid_source=?1 and rfx_category_id=?2 and exclusions=?3", nativeQuery = true)
	String getIdByBidSouceAndRfxCategoryIdAndExclusion(String bid_source,String rfx_category_id,String exclusion);

}
