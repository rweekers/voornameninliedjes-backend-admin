package nl.orangeflamingo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import nl.orangeflamingo.domain.Song;
import nl.orangeflamingo.domain.SongRepository;
import nl.orangeflamingo.domain.SongSpecs;
import nl.orangeflamingo.domain.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @RequestMapping("/song")
    @JsonView(View.Summary.class)
    public List<Song> allSongs() {
        return songRepository.findAll();
    }

    @RequestMapping("/song/{id}")
    @JsonView(View.Detail.class)
    public Song getSongById(@PathVariable long id) {
        return songRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Song not found for id " + id));
    }

    @RequestMapping(value = "/song/artist/{artist}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByArtist(@PathVariable("artist") String artist) {
        return songRepository.findByArtistLikeIgnoreCase("%" + artist + "%");
    }

    @RequestMapping(value = "/song/title/{title}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByTitle(@PathVariable("title") String title) {
         return songRepository.findByTitleLikeIgnoreCase("%" + title + "%");
    }

    @RequestMapping(value = "/song/search/{query}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByQuery(@PathVariable("query") String query) {
        return songRepository.findAll(SongSpecs.songWithArtistOrTitleLike(query));
    }
}
