package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Domain;

public interface DomainDao extends JpaRepository<Domain, Integer>,
JpaSpecificationExecutor<Domain> {

}
