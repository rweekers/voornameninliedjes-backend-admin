package nl.orangeflamingo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import nl.orangeflamingo.domain.Song;
import nl.orangeflamingo.domain.SongRepository;
import nl.orangeflamingo.domain.SongSpecs;
import nl.orangeflamingo.domain.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    private static final int MAX_SIZE = 100;

    private final SongRepository songRepository;

    private final Logger log = LoggerFactory.getLogger(SongController.class);

    @Autowired
    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @RequestMapping("/song/{id}")
    @JsonView(View.Detail.class)
    public Song getSongById(@PathVariable long id) {
        return songRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Song not found for id " + id));
    }

    @RequestMapping(value = "/song/artist/{artist}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByArtist(@PathVariable("artist") String artist) {
        List<Song> songsByArtist = songRepository.findByArtistLikeIgnoreCase("%" + artist + "%");
        return songsByArtist.subList(0, getMaxSize(songsByArtist.size()));
    }

    @RequestMapping(value = "/song/title/{title}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByTitle(@PathVariable("title") String title) {
        List<Song> songsByTitle = songRepository.findByTitleLikeIgnoreCase("%" + title + "%");
        return songsByTitle.subList(0, getMaxSize(songsByTitle.size()));
    }

    @RequestMapping(value = "/song/query/{query}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByQuery(@PathVariable("query") String query) {
        List<Song> songsByQuery = songRepository.findAll(SongSpecs.songWithArtistOrTitleLike(query));
        return songsByQuery.subList(0, getMaxSize(songsByQuery.size()));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public List<Song> allSongs() {
        List<Song> songs = songRepository.findAll().subList(0, MAX_SIZE);
        log.info("Getting {} songs", songs.size());
        return songs;
    }

    @RequestMapping(value = "/song", params = { "page", "size" }, method = RequestMethod.GET)
    public Page<Song> findSongsByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("Getting songs for page {} and size {}", page, size);
        return songRepository.findAll(PageRequest.of(page, size > MAX_SIZE ? MAX_SIZE : size));
    }

    @RequestMapping(value = "/song/query/{query}", params = { "page", "size" }, method = RequestMethod.GET)
    public Page<Song> findSongsByQueryAndPage(@PathVariable("query") String query, @RequestParam("page") int page,
                                              @RequestParam("size") int size) {
        return songRepository.findAll(SongSpecs.songWithArtistOrTitleLike(query), PageRequest.of(page, size > MAX_SIZE ? MAX_SIZE : size));
    }

    private int getMaxSize(int size) {
        return size > MAX_SIZE ? MAX_SIZE : size;
    }
}
