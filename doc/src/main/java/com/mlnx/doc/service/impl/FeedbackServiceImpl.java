package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.Feedback;
import com.mlnx.doc.repository.FeedbackDao;
import com.mlnx.doc.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Feedback> list(Pageable pageable) {

		return feedbackDao.findAll(pageable);
	}

	@Override
	public void save(Feedback city) {
		feedbackDao.save(city);
	}

	@Override
	public Feedback get(Integer id) {
		return feedbackDao.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		feedbackDao.delete(id);

	}

	@Override
	public List<Feedback> findAll() {
		return feedbackDao.findAll();
	}

}
