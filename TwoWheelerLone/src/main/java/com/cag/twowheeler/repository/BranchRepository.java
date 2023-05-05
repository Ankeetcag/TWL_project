package com.cag.twowheeler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cag.twowheeler.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, String> {

	public List<Branch> findByState(String state);

}
