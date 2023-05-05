package com.cag.twowheeler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VehicalsAllData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//property only use for Showing Vehicle Data not using while edit {@param}used for ID
	private String vehicleId;
	private String vehicleModel;
	private String vehicleVariant;
	private String vehicalState;
	private String vehicalOem;
	private double vehicleMaxLoanAmount;
	private double vehicalOnRoadPrice; 

}
