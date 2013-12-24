package nl.flamingostyle.voornaaminliedje.service;

import java.util.List;

import nl.flamingostyle.voornaaminliedje.domain.Song;

public interface SongService {

	public Song getRandom();

	public Song getYouCanCallMeAl();

	public List<Song> getAll(int count, int page, String sortingArtist,
			String sortingTitle);

	public List<Song> getAll();

	public List<Song> findByFirstname(String firstname);

	public long getMax();

	public void add(Song song);

	public void delete(Integer id);

	public Object get(Integer id);

	public void edit(Song song);
}