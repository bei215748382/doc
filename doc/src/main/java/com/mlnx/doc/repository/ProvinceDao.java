package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Province;

public interface ProvinceDao extends JpaRepository<Province, Integer>,
JpaSpecificationExecutor<Province> {

}
