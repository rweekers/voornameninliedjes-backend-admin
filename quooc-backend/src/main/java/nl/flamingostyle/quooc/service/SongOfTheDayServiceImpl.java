package nl.flamingostyle.quooc.service;

import java.util.List;

import nl.flamingostyle.quooc.domain.SongOfTheDay;

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
public class SongOfTheDayServiceImpl implements SongOfTheDayService {

    protected static final Logger logger = Logger.getLogger("service");

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Retrieves all songsOfTheDay
     *
     * @return a list of songsOfTheDay
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SongOfTheDay> getAll() {
        logger.debug("Retrieving all songsOfTheDay");

        // Create a Hibernate query (HQL)
        Query query = getCurrentSession().createQuery("FROM  SongOfTheDay songOfTheDay order by songOfTheDay.id");

        // Retrieve all
        return query.list();
    }

    /**
     * Retrieves a single songOfTheDay
     * 
     * @return the songOfTheDay
     * @param id the id of the songOfTheDay
     */
    public SongOfTheDay get(Integer id) {
        // Retrieve existing visit first
        logger.debug("Calling get with the id " + id);
        SongOfTheDay songOfTheDay = (SongOfTheDay) getCurrentSession().get(SongOfTheDay.class, id);
        return songOfTheDay;
    }
}
