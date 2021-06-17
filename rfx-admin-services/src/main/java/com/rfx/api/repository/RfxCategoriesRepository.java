package com.rfx.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rfx.api.model.TableBusinessUnit;
import com.rfx.api.model.TableRfxCategories;

public interface RfxCategoriesRepository extends JpaRepository<TableRfxCategories, Integer>  {
	
	Page<TableRfxCategories> findByNameAndStatusAndCode(String name, String status,String code, Pageable pageable);

    Page<TableRfxCategories> findByStatusAndEffectivedateBetween(String status, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableRfxCategories> findByNameAndEffectivedateBetween(String name, String effectivedate_st, String effectivedate_end, Pageable pageable);
    
    Page<TableRfxCategories> findByCodeAndEffectivedateBetween(String code, String effectivedate_st, String effectivedate_end, Pageable pageable);

    Page<TableRfxCategories> findByEffectivedateBetween(String effectivedate_st, String effective_end, Pageable pageable);
    
    

    //having
    @Query(value = "SELECT id FROM rfx_categories WHERE lower(name)= lower(:name)", nativeQuery = true)
    String getIdByRfxCategoryName(@Param("name") String name);
    
  

    @Query(value = "SELECT name FROM rfx_categories WHERE lower(rfx_category_id) = lower(:rfx_category_id)", nativeQuery = true)
    String getNameByRfxCategoriesId(@Param("rfx_category_id") String rfx_category_id);

    @Modifying
    @Transactional
    @Query("UPDATE rfx_categories bu SET bu.name = :name, bu.description = :description, bu.code=:code, bu.effectivedate=:effectivedate, bu.updated_at=:updatedAt WHERE bu.rfx_category_id = :rfx_category_id")
    int updateRfxCategories(@Param("rfx_category_id") String rfx_category_id, @Param("name") String name, @Param("description") String description, @Param("code") String code, @Param("effectivedate") String effectivedate, @Param("updatedAt") String updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE rfx_categories bu SET bu.status = :status WHERE bu.rfx_category_id = :rfx_category_id")
    int updateStatus(@Param("rfx_category_id") String rfx_category_id, @Param("status") String status);

    //having
    @Query(value = "SELECT rfx_category_id FROM rfx_categories WHERE code=?1", nativeQuery = true)
    String getRfxCategoryIdByCode(String code);
    
    List<TableRfxCategories> findListByStatus(String status);

    Page<TableRfxCategories> findByStatus(String status, Pageable pageable);

    Page<TableRfxCategories> findByNameContaining(String name, Pageable pageable);
    
    Page<TableRfxCategories> findByCodeContaining(String code, Pageable pageable);

    Page<TableRfxCategories> findByStatusAndNameAndCodeAndEffectivedateBetween(String status,String name,String code, String effectivedate_st, String effective_end, Pageable pageable);

}
