package nl.flamingostyle.quooc.service;

import nl.flamingostyle.quooc.domain.Song;

public interface SongService {
	public Song getRandom();
	public Song getById(Long id);
	public void save(Song song);
}
