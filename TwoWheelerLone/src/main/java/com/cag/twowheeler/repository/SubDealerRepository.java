package com.cag.twowheeler.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cag.twowheeler.entity.SubDealer;

public interface SubDealerRepository extends JpaRepository<SubDealer, String> {

	@Query(value = "SELECT sub_dealerid FROM sub_dealer WHERE sub_dealerid LIKE :dealerId", nativeQuery = true)
	public List<String> getSimilarDealerId(@Param("dealerId") String dealerId);

	@Query(value = "SELECT * FROM two_wheeler_lone_database_updated.sub_dealer where expiry_date = :date", nativeQuery = true)
	public List<SubDealer> getData(@Param("date") String date);
}
