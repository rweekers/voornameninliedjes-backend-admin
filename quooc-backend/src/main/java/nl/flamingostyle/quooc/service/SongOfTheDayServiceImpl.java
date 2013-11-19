package nl.flamingostyle.quooc.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import nl.flamingostyle.quooc.domain.Song;
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

	@Autowired
	SongService songService;

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
		Query query = getCurrentSession().createQuery(
				"FROM  SongOfTheDay songOfTheDay order by songOfTheDay.id");

		// Retrieve all
		return query.list();
	}

	/**
	 * Initializes the list with random songs per day
	 */
	public void initialize() {
		int numberOfSongsOfTheDay = getAll().size();
		logger.debug("Aantal songsOfTheDay in database: "
				+ numberOfSongsOfTheDay);

		if (numberOfSongsOfTheDay == 0) {

			logger.debug("Initalizing list with songs of the day...");
			List<Song> songs = songService.getAll();
			Collections.shuffle(songs);
			Calendar calendarDay = Calendar.getInstance();

			for (Song song : songs) {
				Date day = new Date(calendarDay.getTimeInMillis());
				SongOfTheDay songOfTheDay = new SongOfTheDay();
				songOfTheDay.setSong(song);
				songOfTheDay.setDay(day);
				calendarDay.add(Calendar.DATE, 1);
				getCurrentSession().save(songOfTheDay);
			}
		} else {
			logger.debug("Initialize not necessary, already songsOfTheDay.");
		}
	}

	/**
	 * Returns the song for today
	 * 
	 * @param today today
	 * @return the song that is due for today
	 */
	public Song getSongOfTheDay() {
		Date today = new Date(Calendar.getInstance().getTimeInMillis());
		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery(
				"FROM  SongOfTheDay songOfTheDay where songOfTheDay.day = :today");
		query.setParameter("today", today);
		SongOfTheDay songOfTheDay = (SongOfTheDay) query.list().get(0);
		return songOfTheDay.getSong();
	}
	
	/**
	 * Retrieves a single songOfTheDay
	 * 
	 * @return the songOfTheDay
	 * @param id
	 *            the id of the songOfTheDay
	 */
	public SongOfTheDay get(Integer id) {
		// Retrieve existing visit first
		logger.debug("Calling get with the id " + id);
		SongOfTheDay songOfTheDay = (SongOfTheDay) getCurrentSession().get(
				SongOfTheDay.class, id);
		return songOfTheDay;
	}
}
