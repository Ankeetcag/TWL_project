package com.cag.twowheeler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cag.twowheeler.entity.StateAbbreviation;

public interface StateAbbreviationRepository extends JpaRepository<StateAbbreviation, String>{

}
