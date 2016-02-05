package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.State;

public interface StateDao extends JpaRepository<State, Integer>,
JpaSpecificationExecutor<State> {

}
