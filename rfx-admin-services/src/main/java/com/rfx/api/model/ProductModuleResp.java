package com.rfx.api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ProductModuleResp {
	private ArrayList<TableProductModules> productModules;
	private String message;
	private int code;	
}
