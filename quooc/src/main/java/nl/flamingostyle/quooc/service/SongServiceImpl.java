package nl.flamingostyle.quooc.service;

import java.util.Random;

import nl.flamingostyle.quooc.domain.Song;

import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	// List<Song> songs = new ArrayList<Song>();
	Song[] songs = {new Song("Roxanne", "Police", "Roxanne"), new Song("Hey Jude", "The Beatles", "Jude"), new Song("Ruby", "Kaiser Chiefs", "Ruby")}; 
	
	@Override
	public Song getRandom() {
		Random random = new Random();
		return songs[random.nextInt(songs.length)];
	}

	@Override
	public Song getById(Long id) {
		Song song = songs[id.intValue()];
		return song;
	}
	
	@Override
	public void save(Song song) {
		// Save song to database ...
	}

}
