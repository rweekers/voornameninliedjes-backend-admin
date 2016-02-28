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

    private static final Logger LOGGER = Logger
            .getLogger(SearchInstructionServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    VisitService visitService;

    /**
     * Retrieves all searchInstructions
     * 
     * @return a list of searchInstructions
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SearchInstruction> getAll() {
        LOGGER.debug("Retrieving all searchInstructions");

        Session session = sessionFactory.openSession();

        // Create a Hibernate query (HQL)
        Query query = session
                .createQuery("FROM  SearchInstruction searchInstruction order by searchInstruction.id desc");

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
        LOGGER.debug("Calling get searchInstruction with the id " + id);
        Session session = sessionFactory.openSession();
        SearchInstruction searchInstruction = (SearchInstruction) session.get(
                SearchInstruction.class, id);
        LOGGER.debug("Gotten searchInstruction "
                + searchInstruction.getArgument());
        LOGGER.debug("Visit is " + searchInstruction.getVisit().getBrowser());
        return searchInstruction;
    }

    /**
     * Adds a new searchInstruction
     * 
     * @param searchInstruction
     *            the searchInstruction to add
     */
    public SearchInstruction add(SearchInstruction searchInstruction,
            Integer visitId) {
        LOGGER.debug("Adding new searchInstruction");

        UserAgentStringParser parser = UADetectorServiceFactory
                .getResourceModuleParser();
        ReadableUserAgent agent = parser
                .parse(searchInstruction.getUserAgent());
        searchInstruction.setBrowser(agent.getProducer() + " "
                + agent.getName() + " "
                + agent.getVersionNumber().toVersionString());
        searchInstruction.setOperatingSystem(agent.getOperatingSystem()
                .getProducer() + " " + agent.getOperatingSystem().getName());

        // Retrieve session from Hibernate
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Visit visit = (Visit) session.get(Visit.class, visitId);
        searchInstruction.setVisit(visit);
        // Save
        session.save(searchInstruction);
        session.getTransaction().commit();
        return searchInstruction;
    }
}
