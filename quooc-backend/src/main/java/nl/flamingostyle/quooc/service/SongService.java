package nl.flamingostyle.quooc.service;

import java.util.List;

import nl.flamingostyle.quooc.domain.Song;

public interface SongService {
	
	public Song getRandom();
	
	public Song getYouCanCallMeAll();

	public List<Song> getAll();

	public List<Song> getAllPagination(int max, int offset);
	
	public List<Song> findByFirstname(String firstname);
        
        public long getMax();

	public void add(Song song);

	public void delete(Integer id);

	public Object get(Integer id);

	public void edit(Song song);
}