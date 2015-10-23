package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Integer>,
JpaSpecificationExecutor<Patient> {

}
