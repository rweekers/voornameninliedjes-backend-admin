package org.orangeflamingo.namesandsongs.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Item;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Item
 * 
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	private static final Logger LOGGER = Logger
			.getLogger(ItemServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SongService songService;

	public Item addItem(Item item, String user) {
		LOGGER.debug("Adding new item");
		// Retrieve session from Hibernate and save song
		Session session = sessionFactory.openSession();
		item.setUser(user);
		session.save(item);
		return item;
	}

	/**
	 * Retrieves all suggestions
	 * 
	 * @return a list of suggestions
	 */
	@SuppressWarnings("unchecked")
	public List<Item> getAll() {
		LOGGER.debug("Retrieving all items");

		// Create a Hibernate query (HQL)
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("FROM  Item item order by item.id desc");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single item
	 */
	public Item get(Integer id) {
		// Retrieve existing song first
		LOGGER.debug("Calling getItem() with the id " + id);
		Session session = sessionFactory.openSession();
		return (Item) session.get(Item.class, id);
	}

	/**
	 * Edits an existing item
	 */
	public void update(Item item, Integer songId) {
		LOGGER.info("Editing existing item with title "
				+ item.getTitle());

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Retrieve existing item via id
		Item existingItem = (Item) session.get(
				Item.class, item.getId());
		existingItem.setTitle(item.getTitle());
		existingItem.setStory(item.getStory());
		existingItem.setType(item.getType());
		existingItem.setStatus(item.getStatus());
		existingItem.setDate(new Timestamp(System
				.currentTimeMillis()));
		if (songId != null) {
			existingItem.getSongs().add((Song) songService.get(songId));
		}
		// TODO Add song to list of songs from suggestion
		// Assign updated values to this remark
		LOGGER.info("Updating item " + existingItem);
		// Save updates
		session.save(existingItem);
	}

	/**
	 * Unlinks a given song from a suggestion
	 * 
	 * @param suggestion The suggestion from which the song neends to be unlinked
	 * @param songId The id of the song that needs to be unlinked
	 */
	public void removeSong(Item item, Integer songId) {
		Session session = sessionFactory.getCurrentSession();
		Item existingItem = (Item) session.get(
				Item.class, item.getId());
		
		if (existingItem.getSongs().size() > 0) {
			LOGGER.info("Removing song with id " + songId);

			// existingSuggestion.getSongs().remove((Song) songService.get(songId));
			Song song = (Song)session.get(Song.class, songId);
			existingItem.getSongs().remove(song);
			session.save(existingItem);
		}
	}
}
