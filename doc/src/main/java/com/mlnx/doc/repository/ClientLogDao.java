package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.ClientLog;

public interface ClientLogDao extends JpaRepository<ClientLog, Integer>,
		JpaSpecificationExecutor<ClientLog> {

}
