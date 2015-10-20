package com.scu.book.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scu.book.shop.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Integer>,
JpaSpecificationExecutor<Doctor> {

}
