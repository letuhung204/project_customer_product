package com.rfx.api.model;

import java.util.List;

import lombok.Data;

@Data
public class BidSourceAndRfxCategoriesResp {

	private String bidSource;
	private List<String> rfxCategories;
}
