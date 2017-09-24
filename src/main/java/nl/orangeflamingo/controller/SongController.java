package nl.orangeflamingo.controller;

import nl.orangeflamingo.SongRepository;
import nl.orangeflamingo.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @RequestMapping("/song")
    public Song greeting(@RequestParam(value = "title", defaultValue = "Roxanne") String title) {
        Song song = new Song();
        song.setArtist("The Police");
        song.setTitle(title);
        return song;
    }

    @RequestMapping("/songs")
    public List<Song> allSongs() {
        return songRepository.findAll();
    }
}
