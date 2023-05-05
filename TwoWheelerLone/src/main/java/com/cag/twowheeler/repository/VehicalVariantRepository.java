package com.cag.twowheeler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cag.twowheeler.entity.VehicalVariant;

@Repository
public interface VehicalVariantRepository extends JpaRepository<VehicalVariant, String> {

	@Query(value = "SELECT variant_id FROM vehical_variant WHERE variant_id LIKE :variantId", nativeQuery = true)
	public List<String> getSimilarVariantId(@Param("variantId") String variantId);
	
	public List<VehicalVariant> findByOemVehicalOem(String oem); //type
}
