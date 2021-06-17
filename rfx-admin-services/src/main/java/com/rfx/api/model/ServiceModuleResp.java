package com.rfx.api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ServiceModuleResp {

	private ArrayList<TableServiceModules> serviceModules;
	private String message;
	private int code;
}
