package org.orangeflamingo.voornaaminliedje.service;

import java.util.List;

import org.orangeflamingo.voornaaminliedje.domain.Song;
import org.orangeflamingo.voornaaminliedje.domain.SongOfTheDay;

public interface SongOfTheDayService {

	public List<SongOfTheDay> getAll();

	public Object get(Integer id);
	
	public void initialize();
	
	public Song getSongOfTheDay();
}