package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Doctor_doctor;

public interface Doctor_doctorDao extends JpaRepository<Doctor_doctor, Integer>,
JpaSpecificationExecutor<Doctor_doctor> {

}
