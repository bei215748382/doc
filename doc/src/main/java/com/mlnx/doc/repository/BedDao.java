package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Bed;

public interface BedDao extends JpaRepository<Bed, Integer>,
JpaSpecificationExecutor<Bed> {

}
