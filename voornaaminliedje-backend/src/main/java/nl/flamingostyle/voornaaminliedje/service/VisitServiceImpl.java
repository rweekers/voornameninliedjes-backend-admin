package nl.flamingostyle.voornaaminliedje.service;

import java.util.List;

import nl.flamingostyle.voornaaminliedje.domain.Visit;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Songs
 * 
 */
@Service
@Transactional
public class VisitServiceImpl implements VisitService {

	protected static final Logger logger = Logger.getLogger("service");

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Retrieves all visits
	 * 
	 * @return a list of visits
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> getAll() {
		logger.debug("Retrieving all visits");

		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery(
				"FROM  Visit visit order by visit.id");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single visit
	 * 
	 * @return the visit
	 * @param id
	 *            the id of the visit
	 */
	public Visit get(Integer id) {
		// Retrieve existing visit first
		logger.debug("Calling get with the id " + id);
		Visit visit = (Visit) getCurrentSession().get(Visit.class, id);
		logger.debug("Gotten visit " + visit.getIpAddress());
		return visit;
	}

	/**
	 * Adds a new visit
	 * 
	 * @param visit
	 *            the visit to add
	 */
	public void add(Visit visit) {
		logger.debug("Adding new visit");

		// Retrieve session from Hibernate
		Session session = getCurrentSession();
		// Save
		session.save(visit);
	}
}
