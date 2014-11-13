package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.SearchInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing SearchInstructions
 * 
 */
@Service
@Transactional
public class SearchInstructionServiceImpl implements SearchInstructionService {

	protected static final Logger logger = Logger
			.getLogger(SearchInstructionServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Retrieves all searchInstructions
	 * 
	 * @return a list of searchInstructions
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SearchInstruction> getAll() {
		logger.debug("Retrieving all searchInstructions");

		// Create a Hibernate query (HQL)
		Query query = getCurrentSession()
				.createQuery(
						"FROM  SearchInstruction searchInstruction order by searchInstruction.id desc");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single searchInstruction
	 * 
	 * @return the searchInstruction
	 * @param id
	 *            the id of the searchInstruction
	 */
	public SearchInstruction get(Integer id) {
		// Retrieve existing searchInstruction first
		logger.debug("Calling get searchInstruction with the id " + id);
		SearchInstruction searchInstruction = (SearchInstruction) getCurrentSession()
				.get(SearchInstruction.class, id);
		logger.debug("Gotten searchInstruction "
				+ searchInstruction.getArgument());
		return searchInstruction;
	}

	/**
	 * Adds a new searchInstruction
	 * 
	 * @param searchInstruction
	 *            the searchInstruction to add
	 */
	public void add(SearchInstruction searchInstruction) {
		logger.debug("Adding new searchInstruction");

		// Retrieve session from Hibernate
		Session session = getCurrentSession();
		// Save
		session.save(searchInstruction);
	}
}
