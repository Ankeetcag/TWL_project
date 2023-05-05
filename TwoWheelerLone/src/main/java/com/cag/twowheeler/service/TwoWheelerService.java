package com.cag.twowheeler.service;

import java.util.List;

import com.cag.twowheeler.dto.AllVehicleOem;
import com.cag.twowheeler.dto.InseartVehicle;
import com.cag.twowheeler.dto.VehicalsAllData;
import com.cag.twowheeler.dto.VehiclePriceDetail;

public interface TwoWheelerService {
	

	VehiclePriceDetail getPrice(String vehicalState , String vehicalType, String vehicalOem);

	List<VehicalsAllData> getAllVehicalData();

	List<AllVehicleOem> getOem();

	Boolean editVehicleData(String id,VehicalsAllData data);

	String deleteData(int id);

	String addVehicle(InseartVehicle vehicle);
}
