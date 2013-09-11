package nl.flamingostyle.quooc.service;

import java.util.Random;

import nl.flamingostyle.quooc.domain.Song;

import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	String[] names = {"Nikolaus Otto", "Robert Ford", "Gottlieb Daimler", "Lt. General Masaharu Homma"};

	@Override
	public Song getRandom() {
		Song song = new Song();
		song.setName(randomName());
		song.setAge(randomAge());
		return song;
	}

	@Override
	public Song getById(Long id) {
		Song song = new Song();
		song.setName(names[id.intValue()]);
		song.setAge(50);
		return song;
	}
	
	@Override
	public void save(Song song) {
		// Save song to database ...
	}
	
	private Integer randomAge() {
		Random random = new Random();
		return 10 + random.nextInt(100);
	}

	private String randomName() {
		Random random = new Random();
		return names[random.nextInt(names.length)];
	}

}
