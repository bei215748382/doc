package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Hospital;

public interface HospitalDao extends JpaRepository<Hospital, Integer>,
JpaSpecificationExecutor<Hospital> {

}
