package nl.orangeflamingo.controller;

import nl.orangeflamingo.domain.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongControllerTest {

    @Autowired
    private SongController songController;

    @Test
    public void test() {
        List<Song> allSongs = songController.findSongsByArtist("Dolly Parton");
        assertEquals(1, allSongs.size());
    }
}