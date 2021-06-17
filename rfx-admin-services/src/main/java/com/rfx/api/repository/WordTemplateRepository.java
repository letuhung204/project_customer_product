package com.rfx.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableWordTemplateAttachments;

public interface WordTemplateRepository extends JpaRepository<TableWordTemplateAttachments, Integer>{

	Page<TableWordTemplateAttachments>  findByNameAndDescrAndStatus(String name,String descr,String status,Pageable pageable);

    Page<TableWordTemplateAttachments>  findByNameAndDescr(String name,String descr,Pageable pageable);

	Page<TableWordTemplateAttachments>  findByNameContaining(String name,Pageable pageable);
	
	Page<TableWordTemplateAttachments>  findByDescrContaining(String descr,Pageable pageable);
	
	Page<TableWordTemplateAttachments>  findByStatus(String status,Pageable pageable);
	


}
