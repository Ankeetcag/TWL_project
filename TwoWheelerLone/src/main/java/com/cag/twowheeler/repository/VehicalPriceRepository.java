package com.cag.twowheeler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cag.twowheeler.entity.VehicalPrice;

@Repository
public interface VehicalPriceRepository extends JpaRepository<VehicalPrice, String> {

	public VehicalPrice  findByStateAndTypeVehicalvariantNameAndOemVehicalOem(String state,String type,String oem);
	
	@Query(value = "SELECT vehical_priceid FROM vehical_price WHERE vehical_priceid LIKE :vehicalPriceid", nativeQuery = true)
	public List<String> getSimilarVariantpriceId(@Param("vehicalPriceid") String vehicalPriceid);

	 VehicalPrice findByVehicalPriceID(String string);
	
	List<VehicalPrice> findByOemVehicalOem(String oem);
	
	
	List<String> findByState(String state);
}
