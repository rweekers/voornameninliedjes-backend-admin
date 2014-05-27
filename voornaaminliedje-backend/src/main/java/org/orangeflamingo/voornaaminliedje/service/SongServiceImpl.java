package org.orangeflamingo.voornaaminliedje.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.voornaaminliedje.domain.Song;
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

	protected static final Logger logger = Logger
			.getLogger(SongServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

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
		logger.debug("Getting max number of songs");

		Query query = getCurrentSession().createQuery(
				"SELECT COUNT(*) FROM  Song song");

		// Retrieve all
		return (Long) query.list().get(0);
	}

	/**
	 * Returns a random song from the database.
	 * 
	 * @return A random song from the database
	 */
	@Override
	public Song getRandom() {
		// Retrieve existing song 'You can call me Al'
		// TODO Implement with songOfTheDay
		int id = 12070;
		Song song = (Song) getCurrentSession().get(Song.class, id);
		logger.debug("Gotten song from getRandom(): " + song.getTitle());

		return song;
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
		int id = 12070;
		Song song = (Song) getCurrentSession().get(Song.class, id);
		logger.debug("Gotten song " + song.getTitle());

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
		logger.info("Retrieving all songs with params");

		if (count == null || count > 25) {
			count = 25;
		}

		int offset = 0;

		if (page != null) {
			offset = page * count;
		}

		// Create a Hibernate query (HQL)
		String queryString = "FROM  Song song WHERE 1 = 1 ";

		if (filterArtist != null && filterArtist.trim() != "") {
			queryString = queryString + " AND lower(song.artist) like :artist";
		}

		if (filterTitle != null && filterTitle.trim() != "") {
			queryString = queryString + " AND lower(song.title) like :title";
		}

		if (sortingArtist != null) {
			queryString = queryString + " order by song.artist "
					+ sortingArtist;
		} else {
			if (sortingTitle != null) {
				queryString = queryString + " order by song.title  "
						+ sortingTitle;
			}
		}

		Query query = getCurrentSession().createQuery(queryString);
		if (filterArtist != null && filterArtist.trim() != "") {
			query.setParameter("artist", "%" + filterArtist.toLowerCase() + "%");
		}
		if (filterTitle != null && filterTitle.trim() != "") {
			query.setParameter("title", "%" + filterTitle.toLowerCase() + "%");
		}

		query.setFirstResult(offset);
		query.setMaxResults(count);

		List<Song> songs = query.list();
		return songs;
	}

	/**
	 * Retrieves all songs
	 * 
	 * @return a list of songs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Song> getAll() {
		logger.info("Retrieving all songs");

		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery(
				"FROM  Song song order by song.firstname");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single song
	 */
	public Song get(Integer id) {
		// Retrieve existing song first
		logger.debug("Calling getSong() with the id " + id);
		Song song = (Song) getCurrentSession().get(Song.class, id);
		logger.debug("Gotten song " + song.getTitle());
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
		logger.debug("Finding songs with firstname " + firstname);

		String firstnameLowerCaseWithWildcards = "%" + firstname.toLowerCase()
				+ "%";

		// Create a Hibernate query (HQL)
		// Query query =
		// getCurrentSession().createQuery("FROM  Song where lower(firstname) like '%"
		// + firstnameLowerCase + "%'");
		Query query = getCurrentSession().createQuery(
				"FROM  Song where lower(firstname) like :firstname");
		query.setParameter("firstname", firstnameLowerCaseWithWildcards);

		return query.list();
	}

	/**
	 * Adds a new song
	 */
	public void add(Song song) {
		logger.debug("Adding new song");
		// Retrieve session from Hibernate and save song
		getCurrentSession().save(song);
	}

	/**
	 * Deletes an existing song
	 * 
	 * @param id
	 *            the id of the existing song
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing song");

		// Retrieve session from Hibernate
		// Session session = sessionFactory.getCurrentSession();
		// Retrieve existing song first
		// Song song = (Song) session.get(Song.class, id);
		// Delete
		// session.delete(song);
	}

	/**
	 * Edits an existing song
	 */
	public void update(Song song) {
		logger.debug("Editing existing song");

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
