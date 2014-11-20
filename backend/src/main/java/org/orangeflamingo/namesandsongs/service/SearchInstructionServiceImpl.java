package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.SearchInstruction;
import org.orangeflamingo.namesandsongs.domain.Visit;
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
	
	@Autowired
	VisitService visitService;

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
	public SearchInstruction add(SearchInstruction searchInstruction, Integer visitId) {
		logger.debug("Adding new searchInstruction");

		UserAgentStringParser parser = UADetectorServiceFactory
				.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(searchInstruction.getUserAgent());
		searchInstruction.setBrowser(agent.getProducer() + " " + agent.getName() + " "
				+ agent.getVersionNumber().toVersionString());
		searchInstruction.setOperatingSystem(agent.getOperatingSystem().getProducer() + " "
				+ agent.getOperatingSystem().getName());
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// searchInstruction.setVisit(visit);
		// Visit v = (Visit)visitService.get(visitId);
		Visit visit = (Visit) session.get(Visit.class, visitId);
		searchInstruction.setVisit(visit);
		// Save
		session.save(searchInstruction);
		session.getTransaction().commit();
		return searchInstruction;
	}
}
