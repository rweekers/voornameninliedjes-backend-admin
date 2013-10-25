package nl.flamingostyle.quooc.service;

import java.util.List;

import nl.flamingostyle.quooc.domain.Song;

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
public class SongServiceImpl implements SongService {

	protected static Logger logger = Logger.getLogger("service");
	
 	@Autowired
	private SessionFactory sessionFactory;
	
 	private Session getCurrentSession() {  
        return sessionFactory.getCurrentSession();  
    }  
 	
 	/**
 	 * Returns a random song from the database. 
 	 * 
 	 * @return A random song from the database 
 	 */
	public Song getRandom() {
		// List<Song> songs = getAll();
		// Random randomizer = new Random();
		// Song song = songs.get(randomizer.nextInt(songs.size()));
		// logger.debug("Random song retrieved: " + song.getArtist() + " - " + song.getTitle());
		
		// Retrieve existing song 'You can call me Al'
		int id = 12070;
		Song song = (Song) getCurrentSession().get(Song.class, id);
		logger.debug("Gotten song " + song.getTitle());
		
		return song;
	}
	
	/**
	 * Retrieves all songs
	 * 
	 * @return a list of songs
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getAll() {
		logger.debug("Retrieving all songs");
		
		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery("FROM  Song");
		
		// Retrieve all
		return  query.list();
	}
	
    /**
	 * Retrieves all songs
	 * 
	 * @return a list of songs
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getAllPagination(int max, int offset) {
		logger.debug("Retrieving all songs");
		
		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery("FROM  Song");
		query.setFirstResult(offset);
		query.setMaxResults(max);
		
		// Retrieve all
		return  query.list();
	}

	/**
	 * Retrieves a single song
	 */
	public Song get( Integer id ) {
		// Retrieve existing song first
		logger.debug("Calling get with the id " + id);
		Song song = (Song) getCurrentSession().get(Song.class, id);
		logger.debug("Gotten song " + song.getTitle());
		return song;
	}
	
	/**
	 * Searches songs with a certain firstname
	 * 
	 * @param name the firstname to search for 
	 * @return a list with songs with the firstname
	 */
	@SuppressWarnings("unchecked")
	public List<Song> findByFirstname(String firstname) {
		logger.debug("Finding songs with firstname " + firstname);
		
		String firstnameLowerCaseWithWildcards = "%" + firstname.toLowerCase() + "%";
		
		// Create a Hibernate query (HQL)
		// Query query = getCurrentSession().createQuery("FROM  Song where lower(firstname) like '%" + firstnameLowerCase + "%'");
		Query query = getCurrentSession().createQuery("FROM  Song where lower(firstname) like :firstname");
		query.setParameter("firstname", firstnameLowerCaseWithWildcards);
		
		return query.list();
	}
	
	/**
	 * Adds a new song
	 */
	public void add(Song song) {
		logger.debug("Adding new song");
		
		// Retrieve session from Hibernate
		// Session session = sessionFactory.getCurrentSession();
		
		// Save
		// session.save(song);
	}
	
	/**
	 * Deletes an existing song
	 * @param id the id of the existing song
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
	public void edit(Song song) {
		logger.debug("Editing existing song");
		
		// Retrieve session from Hibernate
		// Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing song via id
		// Song existingSong = (Song) session.get(Song.class, song.getId());
		
		// Assign updated values to this song
		// existingSong.setArtist(song.getArtist());
		// existingSong.setTitle(song.getTitle());
		// existingSong.setFirstname(song.getFirstname());

		// Save updates
		// session.save(existingSong);
	}
}
