package com.rfx.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableServiceModules;

public interface ServiceModuleRepository extends JpaRepository<TableServiceModules, Integer> {

}
