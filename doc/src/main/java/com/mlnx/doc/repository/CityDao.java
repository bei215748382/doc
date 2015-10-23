package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.City;

public interface CityDao extends JpaRepository<City, Integer>,
JpaSpecificationExecutor<City> {

}
