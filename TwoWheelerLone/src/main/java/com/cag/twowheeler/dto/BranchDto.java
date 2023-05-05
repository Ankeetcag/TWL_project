package com.cag.twowheeler.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchDto {
	
	private String branchID;

	private String branchName;

	private String region;

	private String area;

	private String state;

}
