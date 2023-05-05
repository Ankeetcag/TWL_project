package com.cag.twowheeler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cag.twowheeler.entity.VehicleModal;

public interface VehicleModalRepository extends JpaRepository<VehicleModal, String>{
	VehicleModal findByVehicleModelName(String modalName);
	
	@Query(value = "SELECT vehicle_model_id FROM vehicle_modal WHERE vehicle_model_id LIKE :modelID", nativeQuery = true)
	public List<String> getSimilarModalId(@Param("modelID") String modelID);
}
