package com.rfx.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableBusinessUnitTypes;

public interface BusinessUnitTypeRepository extends JpaRepository<TableBusinessUnitTypes, Integer>{

}
