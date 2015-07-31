package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Song;

public interface RedisSongService {

	public void addLink(String userId, String test);
	
	public void addSong(Song song);
	
	public List<Song> getSongs();
}
