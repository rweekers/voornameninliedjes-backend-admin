package nl.orangeflamingo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import nl.orangeflamingo.SongRepository;
import nl.orangeflamingo.domain.Song;
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

    @RequestMapping("song/{id}")
    @ResponseBody
    @JsonView(View.Detail.class)
    public Song getSongById(@PathVariable long id) {
        return songRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Song not found for id " + id));
    }
}
