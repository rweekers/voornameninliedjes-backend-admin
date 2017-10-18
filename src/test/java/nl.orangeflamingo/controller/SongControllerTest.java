package nl.orangeflamingo.controller;

import nl.orangeflamingo.domain.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongControllerTest {

    @Autowired
    private SongController songController;

    @Test
    public void testFindSongById() {
        Song song = songController.getSongById(1L);
        assertEquals("Dolly Parton", song.getArtist());
        assertEquals("Jolene", song.getTitle());
    }

    @Test
    public void testFindSongsByArtist() {
        List<Song> songsByArtist = songController.findSongsByArtist("Beatles");
        assertEquals(2, songsByArtist.size());
    }

    @Test
    public void testFindSongsByTitle() {
        List<Song> songsByTitle = songController.findSongsByTitle("Jolene");
        assertEquals(1, songsByTitle.size());
    }

    @Test
    public void testFindSongsByQuery() {
        List<Song> songsByQuery = songController.findSongsByQuery("olly");
        assertEquals(2, songsByQuery.size());
    }

    @Test
    public void testFindSongsByPage() {
        Page<Song> songsByPage = songController.findSongsByPage(0, 1);
        assertEquals(1, songsByPage.getSize());
    }

    @Test
    public void testFindSongsBySpecificationAndPage() {
        Page<Song> songsBySpecificationAndPage = songController.findSongsByQueryAndPage("olly", 0, 1);
        assertEquals(1, songsBySpecificationAndPage.getSize());
        assertEquals(2, songsBySpecificationAndPage.getTotalPages());
    }
}