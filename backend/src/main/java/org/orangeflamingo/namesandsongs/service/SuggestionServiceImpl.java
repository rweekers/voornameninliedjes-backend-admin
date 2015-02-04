package org.orangeflamingo.namesandsongs.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Suggestion;
import org.orangeflamingo.namesandsongs.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Suggestions
 * 
 */
@Service
@Transactional
public class SuggestionServiceImpl {
	private static final Logger LOGGER = Logger
			.getLogger(SuggestionServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private VisitService visitService;

	@Autowired
	private SongService songService;

	public Suggestion addSuggestion(Suggestion suggestion, int visitId) {
		LOGGER.debug("Adding new suggestion");
		// Retrieve session from Hibernate and save song
		Session session = sessionFactory.openSession();
		suggestion.setVisit((Visit) visitService.get(visitId));
		session.save(suggestion);
		return suggestion;
	}

	/**
	 * Retrieves all suggestions
	 * 
	 * @return a list of suggestions
	 */
	@SuppressWarnings("unchecked")
	public List<Suggestion> getAll() {
		LOGGER.debug("Retrieving all suggestion");

		// Create a Hibernate query (HQL)
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("FROM  Suggestion suggestion order by suggestion.id desc");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single suggestion
	 */
	public Suggestion get(Integer id) {
		// Retrieve existing song first
		LOGGER.debug("Calling getSuggestion() with the id " + id);
		Session session = sessionFactory.openSession();
		return (Suggestion) session.get(Suggestion.class, id);
	}

	/**
	 * Edits an existing suggestion
	 */
	public void update(Suggestion suggestion) {
		LOGGER.debug("Editing existing suggestion");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Retrieve existing suggestion via id
		Suggestion existingSuggestion = (Suggestion) session.get(Suggestion.class,
				suggestion.getId());
		existingSuggestion.setBackground(suggestion.getBackground());
		existingSuggestion.setComment(suggestion.getComment());
		existingSuggestion.setEmail(suggestion.getEmail());
		existingSuggestion.setStatus(suggestion.getStatus());
		existingSuggestion.setYoutube(suggestion.getYoutube());
		existingSuggestion.setUser(suggestion.getUser());
		existingSuggestion.setResponse(suggestion.getResponse());
		existingSuggestion
				.setResponsedate(new Timestamp(System.currentTimeMillis()));
		// TODO Add song to list of songs from suggestion
		// Assign updated values to this remark
		LOGGER.info("Updating remark " + existingSuggestion);
		// Save updates
		session.save(existingSuggestion);
	}
}
