package com.rfx.api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ProductFieldTypesResp {

	private ArrayList<TableProductFieldTypes> companyFieldTypes;
	private String message;
	private int code;
	
}
