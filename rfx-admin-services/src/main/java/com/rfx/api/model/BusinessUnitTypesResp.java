package com.rfx.api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BusinessUnitTypesResp {
	private ArrayList<TableBusinessUnitTypes> businessUnitTypes;
	private String message;
	private int code;
}
