package com.rfx.api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CompanyFieldTypesRep {

	private ArrayList<TableProductFieldTypes> companyFieldTypes;
	private String message;
	private int code;
	
}
