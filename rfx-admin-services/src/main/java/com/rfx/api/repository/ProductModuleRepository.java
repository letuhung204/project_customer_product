package com.rfx.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableProductModules;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ProductModuleRepository extends JpaRepository<TableProductModules, Integer>{
    @Modifying
    @Transactional
    @Query(value="delete from product_modules where product_id =:productId",nativeQuery = true)
    void deleteProductModulesByProductId(@Param("productId")int productId );
}
