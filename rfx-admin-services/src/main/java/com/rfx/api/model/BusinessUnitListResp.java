package com.rfx.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BusinessUnitListResp {
	private List<TableBusinessUnit> businessUnitList;

}
