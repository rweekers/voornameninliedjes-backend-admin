package nl.flamingostyle.quooc.service;

import java.util.List;
import java.util.Random;

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
 	
	public Song getRandom() {
		logger.info("Calling getRandom()...");
		// Search with random number between 12065 and 12824 (max and min id from song)
		return get(randInt(12065, 12824));
	}
	
	/**
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	private int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
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
	 * Retrieves a single song
	 */
	public Song get( Integer id ) {
		// Retrieve existing song first
		logger.info("Calling get with the id " + id);
		Song song = (Song) getCurrentSession().get(Song.class, id);
		logger.info("Gotten song " + song.getTitle());
		return song;
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
