package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Integer>,
JpaSpecificationExecutor<Doctor> {

}
