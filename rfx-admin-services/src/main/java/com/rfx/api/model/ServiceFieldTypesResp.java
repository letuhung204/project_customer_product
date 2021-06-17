package com.rfx.api.model;

import java.util.ArrayList;

import lombok.Data;
@Data
public class ServiceFieldTypesResp {
	private ArrayList<TableServiceFieldTypes> serviceFieldTypes;
	private String message;
	private int code;
}
