package nl.orangeflamingo.voornameninliedjesbackend.controller

import nl.orangeflamingo.voornameninliedjesbackend.domain.Song
import nl.orangeflamingo.voornameninliedjesbackend.repository.SongRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class SongController {

    @Autowired
    private lateinit var songRepository: SongRepository

    @GetMapping("/songs")
    fun getSongs(): List<Song> {
        return songRepository.findAll()
    }

    @GetMapping("/songs/{id}")
    fun getSongById(@PathVariable("id") id: String): Song {
        return songRepository.findById(id).orElseThrow { RuntimeException("Song with $id not found") }
    }

    @PostMapping("/songs")
    fun newSong(@RequestBody newSong: Song): Song {
        return songRepository.save(newSong)
    }

    @PutMapping("/songs/{id}")
    fun replaceRockstar(@RequestBody song: Song, @PathVariable id: String): Song {
        assert(song.id == id)
        return songRepository.save(song)
    }

    @DeleteMapping("/songs/{id}")
    fun deleteSong(@PathVariable id: String) {
        songRepository.deleteById(id)
    }
}
