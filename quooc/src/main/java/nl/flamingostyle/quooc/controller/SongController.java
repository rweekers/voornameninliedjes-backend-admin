package nl.flamingostyle.quooc.controller;

import nl.flamingostyle.quooc.domain.Song;
import nl.flamingostyle.quooc.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class SongController {
	
	SongService songService;
	
	@Autowired
	public SongController(SongService songService) {
		this.songService = songService;
	}

	@RequestMapping("song/random")
	@ResponseBody
	public Song randomSong() {
		return songService.getRandom();
	}
	
	@RequestMapping("song/{id}")
	@ResponseBody
	public Song getById(@PathVariable Long id) {
		return songService.getById(id);
	}
	
	/* same as above method, but is mapped to
	 * /api/song?id= rather than /api/song/{id}
	 */
	@RequestMapping(value="song", params="id")
	@ResponseBody
	public Song getByIdFromParam(@RequestParam("id") Long id) {
		return songService.getById(id);
	}
	
	/**
	 * Saves new song. Spring automatically binds the name?
	 * and age? parameters in the request to the song argument
	 * @param song
	 * @return String indicating success or failure of save
	 */
	@RequestMapping(value="song", method=RequestMethod.POST)
	@ResponseBody
	public String saveSong(Song song) {
		songService.save(song);
		return "Saved song: " + song.toString();
	}
}
