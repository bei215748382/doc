package com.mlnx.doc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.doc.entity.ClientLog;
import com.mlnx.doc.repository.ClientLogDao;
import com.mlnx.doc.service.ClientLogService;
import com.mlnx.doc.util.EnumCollection.ResponseCode;
import com.mlnx.doc.util.Response;

@Service
public class ClientLogServiceImpl implements ClientLogService {

	@Autowired
	private ClientLogDao clientLogDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ClientLog> list(Pageable pageable) {

		return clientLogDao.findAll(pageable);
	}

	@Override
	public Response save(ClientLog clientLog) {
		clientLogDao.save(clientLog);
		return new Response(ResponseCode.ADD_LOG_SUCCESS);
	}

	@Override
	public ClientLog get(Integer id) {
		return clientLogDao.findOne(id);
	}

	@Override
	public Response delete(Integer id) {
		clientLogDao.delete(id);
return new Response(ResponseCode.DELETE_LOG_SUCCESS);
	}

	@Override
	public List<ClientLog> findAll() {
		return clientLogDao.findAll();
	}

}
