package com.cag.twowheeler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cag.twowheeler.dto.AllVehicleOem;
import com.cag.twowheeler.dto.InseartVehicle;
import com.cag.twowheeler.dto.VehicalsAllData;
import com.cag.twowheeler.dto.VehiclePriceDetail;
import com.cag.twowheeler.responce.responce;
import com.cag.twowheeler.service.TwoWheelerService;

/**
 * @author Ankeet
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cag1/twowheelerlone")
public class TwoWheelerController {

	@Autowired
	private TwoWheelerService service;

	/**
	 * API is Use To Give Vehicle price
	 */
	@GetMapping("/getprice")
	public ResponseEntity<responce> getVehicalPrice(@RequestParam String vehicalState, @RequestParam String vehicalType,
			@RequestParam String vehicalOem) {
		 VehiclePriceDetail priceDetail = service.getPrice(vehicalState, vehicalType, vehicalOem);
		if (priceDetail !=null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().error("false").message("vehical found").data(priceDetail).build());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(responce.builder().error("True").data("Given Data Incorrect").message("vehical Not found").build());
	}
	

	/**
	 * @ This APi Use To Give All Vehicle Data(Sorted Base on Vehicle_Name )
	 */
	@GetMapping("/vehicaldata")
	public ResponseEntity<responce> getVehicalData() {
		List<VehicalsAllData> allVehicalData = service.getAllVehicalData();
		if (!allVehicalData.isEmpty())
			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().error("false").data(allVehicalData).message("All vehical Data").build());

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(responce.builder().error("True").data(allVehicalData).message("vehical Data Not Exist").build());
	}

	/**
	 * @ Update API
	 */
	@PutMapping("/editdata")
	public ResponseEntity<responce> editvehicleData(@RequestParam String variantID,@RequestBody VehicalsAllData data) {
		Boolean responces = service.editVehicleData(variantID,data);
		if (responces)
			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().error("False").data("Updated...!").message("Data Updated sucessfully").build());

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(responce.builder().error("True").message("Data Not Updated \n Vehicle Not Exist").build());
	}

	/**
	 * @ Vehicle_OEM DropDown
	 */
	@GetMapping("/alloems")
	public ResponseEntity<responce> oemDropdown() {
		List<AllVehicleOem> oem = service.getOem();
		if (!oem.isEmpty())
			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().error("False").message("All Vehical_Oems").data(oem).build());

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(responce.builder().error("True").message("Vehical_Oems Not Found").build());
	}

	/**
	 * @Vehicle Delete API
	 */
	@DeleteMapping("/deletedata")
	public ResponseEntity<responce> deleteData(int id) {
		String msg = service.deleteData(id);
		return ResponseEntity.status(HttpStatus.OK).body(responce.builder().error("Read Message").message(msg).build());
	}

	/*
	 * 
	 *  New Vehicle Data Add 
	 */
	@PostMapping("/insertvehicle")
	public ResponseEntity<responce> insertVehical(@RequestBody InseartVehicle vehicle) {
		String message = service.addVehicle(vehicle);
		if (message.equals("Vehical Alrady Exist with Given State"))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(responce.builder().error("True").message(message).data("--").build());
		else if (message.equals("New Vehicle Data Add Successfully")) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().error("True").message(message).data("--").build());
		}else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().error("True").message("Existing Vehicle Data Add Successfully").data("--").build());
		}

	}

}
