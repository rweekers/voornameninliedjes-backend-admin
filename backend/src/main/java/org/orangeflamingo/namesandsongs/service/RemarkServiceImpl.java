package org.orangeflamingo.namesandsongs.service;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Remarks
 * 
 */
@Service
@Transactional
public class RemarkServiceImpl implements RemarkService {

	protected static final Logger logger = Logger
			.getLogger(SongServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Remark addRemark(Remark remark) {
		logger.debug("Adding new remark");
		// Retrieve session from Hibernate and save song
		Session session = sessionFactory.openSession();
		session.save(remark);
		return remark;
	}
}
