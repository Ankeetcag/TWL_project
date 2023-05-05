package com.cag.twowheeler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cag.twowheeler.entity.VehicalOem;

@Repository
public interface VehicalOemRepository extends JpaRepository<VehicalOem, Integer> {
          List<VehicalOem> findByVehicalOem(String name);
}
