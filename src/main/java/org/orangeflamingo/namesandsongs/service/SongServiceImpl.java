package org.orangeflamingo.namesandsongs.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Songs
 * 
 */
@Service
@Transactional
public class SongServiceImpl implements SongService {

	private static final Logger LOGGER = Logger
			.getLogger(SongServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private static final int AL = 12070;
	private static final int MAX_PAGE = 50;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Gets the maximum number of songs from the database
	 * 
	 * @return the max number of songs
	 */
	@Override
	public long getMax() {
		LOGGER.debug("Getting max number of songs");

		Query query = getCurrentSession().createQuery(
				"SELECT COUNT(*) FROM  Song song");

		// Retrieve all
		return (Long) query.list().get(0);
	}

	/**
	 * Returns "You Can Call Me All" from the database (for developing
	 * purposes).
	 * 
	 * @return The song "You Can Call Me All" from the database
	 */
	@Override
	public Song getYouCanCallMeAl() {

		// Retrieve existing song 'You can call me Al'
		Song song = (Song) getCurrentSession().get(Song.class, AL);
		LOGGER.debug("Gotten song " + song.getTitle());

		return song;
	}

	/**
	 * Retrieves all songs ordered or limited
	 * 
	 * @return a list of songs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Song> getAll(Integer count, Integer page, String sortingArtist,
			String sortingTitle, String filterArtist, String filterTitle) {
		LOGGER.debug("Retrieving all songs with filterArtist " + filterArtist
				+ " and filterTitle " + filterTitle);

		int c;

		if (count == null || count > MAX_PAGE) {
			c = MAX_PAGE;
		} else {
			c = count;
		}

		int offset = 0;

		if (page != null) {
			offset = page * c;
		}

		// Create a Hibernate query (HQL)
		String queryString = "FROM  Song song WHERE 1 = 1 ";

		if (filterArtist != null && filterArtist.trim() != "") {
			queryString = queryString + " AND lower(song.artist) like :artist";
		}

		if (filterTitle != null && filterTitle.trim() != "") {
			queryString = queryString + " AND lower(song.title) like :title";
		}

		if (sortingArtist != null || sortingTitle != null) {
			queryString = queryString + " order by ";
		}

		if (sortingArtist != null && !"".equals(sortingArtist.trim())) {
			queryString = queryString + " song.artist " + sortingArtist;
		}
		if (sortingTitle != null && !"".equals(sortingTitle.trim())) {
			if (sortingArtist != null && !"".equals(sortingArtist.trim())) {
				queryString = queryString + ", ";
			}
			queryString = queryString + " song.title  " + sortingTitle;
		}

		if (sortingArtist == null && sortingTitle == null) {
			queryString = queryString + " order by song.title ASC";
		}

		LOGGER.debug("QueryString: " + queryString);
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryString);
		LOGGER.debug("Query1: " + query.getQueryString());
		if (filterArtist != null && filterArtist.trim() != "") {
			query.setParameter("artist", "%" + filterArtist.toLowerCase() + "%");
		}
		if (filterTitle != null && filterTitle.trim() != "") {
			query.setParameter("title", "%" + filterTitle.toLowerCase() + "%");
		}
		LOGGER.debug("Query2: " + query.getQueryString());

		query.setFirstResult(offset);
		query.setMaxResults(c);

		return query.list();
	}

	/**
	 * Gets the maximum number of songs from the database with the given filter
	 * 
	 * @return the max number of songs
	 */
	@Override
	public long getCount(String filterArtist, String filterTitle) {
		LOGGER.debug("Getting max number of songs with artist " + filterArtist
				+ " and title " + filterTitle);

		// Create a Hibernate query (HQL)
		String queryString = "SELECT COUNT(1) FROM  Song song WHERE 1 = 1 ";

		if (filterArtist != null && filterArtist.trim() != "") {
			queryString = queryString + " AND lower(song.artist) like :artist";
		}

		if (filterTitle != null && filterTitle.trim() != "") {
			queryString = queryString + " AND lower(song.title) like :title";
		}

		Query query = getCurrentSession().createQuery(queryString);
		if (filterArtist != null && filterArtist.trim() != "") {
			query.setParameter("artist", "%" + filterArtist.toLowerCase() + "%");
		}
		if (filterTitle != null && filterTitle.trim() != "") {
			query.setParameter("title", "%" + filterTitle.toLowerCase() + "%");
		}
		long result = (Long) query.list().get(0);
		LOGGER.debug("Number of songs is " + result);
		return result;
	}

	/**
	 * Retrieves all songs
	 * 
	 * @return a list of songs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Song> getAll(Integer count) {
		LOGGER.debug("Retrieving all songs");

		// Create a Hibernate query (HQL)
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("FROM  Song song order by song.firstname");

		if (count != null) {
			query.setMaxResults(count);
		}

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single song
	 */
	public Song get(Integer id) {
		// Retrieve existing song first
		LOGGER.debug("Calling getSong() with the id " + id);
		Session session = sessionFactory.openSession();
		Song song = (Song) session.get(Song.class, id);
		LOGGER.debug("Gotten song " + song.getTitle());
		return song;
	}

	/**
	 * Searches songs with a certain firstname
	 * 
	 * @param name
	 *            the firstname to search for
	 * @return a list with songs with the firstname
	 */
	@SuppressWarnings("unchecked")
	public List<Song> findByFirstname(String firstname) {
		LOGGER.debug("Finding songs with firstname " + firstname);

		String firstnameLowerCaseWithWildcards = "%" + firstname.toLowerCase()
				+ "%";

		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery(
				"FROM  Song where lower(firstname) like :firstname");
		query.setParameter("firstname", firstnameLowerCaseWithWildcards);

		return query.list();
	}

	/**
	 * Adds a new song
	 */
	public Song add(Song song) {
		LOGGER.debug("Adding new song");
		// Retrieve session from Hibernate and save song
		getCurrentSession().save(song);
		return song;
	}

	/**
	 * Deletes an existing song
	 * 
	 * @param id
	 *            the id of the existing song
	 */
	public void delete(Integer id) {
		LOGGER.debug("Deleting existing song");

		// Retrieve session from Hibernate
		Session session = sessionFactory.openSession();
		// Retrieve existing song first
		Song song = (Song) session.get(Song.class, id);
		// Delete
		session.delete(song);
	}

	/**
	 * Edits an existing song
	 */
	public void update(Song song) {
		LOGGER.debug("Editing existing song");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Retrieve existing song via id
		Song existingSong = (Song) session.get(Song.class, song.getId());
		// Assign updated values to this song
		existingSong.setArtist(song.getArtist());
		existingSong.setTitle(song.getTitle());
		existingSong.setFirstname(song.getFirstname());
		existingSong.setDateModified(new Timestamp(System.currentTimeMillis()));
		existingSong.setUserModified(song.getUserModified());
		existingSong.setBackground(song.getBackground());
		existingSong.setYoutube(song.getYoutube());
		// Save updates
		session.save(existingSong);
	}
}
