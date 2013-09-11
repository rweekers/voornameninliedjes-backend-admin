package nl.flamingostyle.quooc.service;

import java.util.Random;

import nl.flamingostyle.quooc.domain.Song;

import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	String[] titles = {"Roxanne", "Hey Jude", "Anne", "Ruby"};
	String[] artists = {"Police", "The Beatles", "Herman van Veen", "Kaiser Chiefs"};
	String[] firstNames = {"Roxanne", "Jude", "Anne", "Ruby"};

	@Override
	public Song getRandom() {
		Song song = new Song();
		song.setTitle(randomTitle());
		song.setArtist(randomArtist());
		song.setFirstName(randomFirstName());
		return song;
	}

	@Override
	public Song getById(Long id) {
		Song song = new Song();
		song.setTitle(titles[id.intValue()]);
		song.setArtist(titles[id.intValue()]);
		song.setFirstName(titles[id.intValue()]);
		return song;
	}
	
	@Override
	public void save(Song song) {
		// Save song to database ...
	}
	
	private String randomTitle() {
		Random random = new Random();
		return titles[random.nextInt(titles.length)];
	}
	
	private String randomArtist() {
		Random random = new Random();
		return artists[random.nextInt(artists.length)];
	}
	
	private String randomFirstName() {
		Random random = new Random();
		return firstNames[random.nextInt(firstNames.length)];
	}

}
