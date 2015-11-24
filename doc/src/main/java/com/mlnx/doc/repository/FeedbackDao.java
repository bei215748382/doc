package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Integer>,
JpaSpecificationExecutor<Feedback> {

}
