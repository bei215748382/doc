package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>,
JpaSpecificationExecutor<Order> {

}
