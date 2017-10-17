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
        return songRepository.findByArtistLikeIgnoreCase("%" + artist + "%");
    }

    @RequestMapping(value = "/song/title/{title}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByTitle(@PathVariable("title") String title) {
         return songRepository.findByTitleLikeIgnoreCase("%" + title + "%");
    }

    @RequestMapping(value = "/song/{query}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public List<Song> findSongsByQuery(@PathVariable("query") String query) {
        return songRepository.findAll(SongSpecs.songWithArtistOrTitleLike(query));
    }

    @RequestMapping(value = "/song", params = { "page", "size" }, method = RequestMethod.GET)
    public Page<Song> findSongsByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("Getting songs for page {} and size {}", page, size);
        return songRepository.findAll(PageRequest.of(page, size));
    }

    @RequestMapping(value = "/song/{query}", params = { "page", "size" }, method = RequestMethod.GET)
    public Page<Song> findSongsByQueryAndPage(@PathVariable("query") String query, @RequestParam("page") int page,
                                              @RequestParam("size") int size) {
        return songRepository.findAll(SongSpecs.songWithArtistOrTitleLike(query), PageRequest.of(page, size));
    }
}
