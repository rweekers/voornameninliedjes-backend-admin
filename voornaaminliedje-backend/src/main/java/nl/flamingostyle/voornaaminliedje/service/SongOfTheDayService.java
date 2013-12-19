package nl.flamingostyle.voornaaminliedje.service;

import java.util.List;

import nl.flamingostyle.voornaaminliedje.domain.Song;
import nl.flamingostyle.voornaaminliedje.domain.SongOfTheDay;

public interface SongOfTheDayService {

	public List<SongOfTheDay> getAll();

	public Object get(Integer id);
	
	public void initialize();
	
	public Song getSongOfTheDay();
}