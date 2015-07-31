package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RedisSongServiceImpl implements RedisSongService {

	private static final Logger LOGGER = Logger
			.getLogger(RedisSongServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// inject the actual template
	@Autowired
	private RedisTemplate<String, Object> template;

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, Song> listOps;

	public void addLink(String list, String value) {
		LOGGER.info("Adding link for key " + list + " and value " + value);
		// listOps.leftPush(list, value);
	}

	public void addSong(Song song) {
		// Retrieve existing song 'You can call me Al'
		// Song s = (Song) getCurrentSession().get(Song.class, 12071);
		String queryString = "FROM  Song song order by song.title ASC";
		Query query = getCurrentSession().createQuery(queryString);
		query.setFirstResult(0);
		// query.setMaxResults(25);
		List<Song> songs = query.list();
		for (Song s : songs) {
			LOGGER.debug("Gotten song " + s.getTitle());
			listOps.leftPush("songs", s);
		}
	}

	public List<Song> getSongs() {
		List<Song> songs = listOps.range("songs", 0, -1);
		return songs;
	}
}
