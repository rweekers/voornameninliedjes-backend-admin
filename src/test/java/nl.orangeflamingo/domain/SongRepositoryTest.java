package nl.orangeflamingo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    public void testAllSongs() {
        List<Song> allSongs = songRepository.findAll();
        assertEquals(3, allSongs.size());
    }

    @Test
    public void testPagination() {
        Page<Song> firstPageSongs = songRepository.findAll(PageRequest.of(0, 1));
        assertEquals(1, firstPageSongs.getSize());
    }
}