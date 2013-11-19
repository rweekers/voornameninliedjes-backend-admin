package nl.flamingostyle.quooc.service;

import java.util.List;

import nl.flamingostyle.quooc.domain.Song;
import nl.flamingostyle.quooc.domain.SongOfTheDay;

public interface SongOfTheDayService {

	public List<SongOfTheDay> getAll();

	public Object get(Integer id);
	
	public void initialize();
	
	public Song getSongOfTheDay();
}