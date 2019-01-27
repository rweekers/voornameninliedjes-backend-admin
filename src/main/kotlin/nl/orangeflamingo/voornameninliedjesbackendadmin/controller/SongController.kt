package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Audit
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Song
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.SongStatus
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.SongDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.SongRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class SongController {

    @Autowired
    private lateinit var songRepository: SongRepository

    @GetMapping("/songs")
    fun getSongs(): List<SongDto> {
        return songRepository.findAll().map { convertToDto(it) }
    }

    @GetMapping("/songs/{id}")
    fun getSongById(@PathVariable("id") id: String): SongDto {
        return songRepository.findById(id).map { convertToDto(it) }.orElseThrow { RuntimeException("Song with $id not found") }
    }

    @PostMapping("/songs")
    fun newSong(@RequestBody newSong: SongDto): SongDto {
        return convertToDto(songRepository.save(convert(newSong)))
    }

    @PutMapping("/songs/{id}")
    fun replaceSong(@RequestBody song: SongDto, @PathVariable id: String): SongDto {
        assert(song.id == id)
        return convertToDto(songRepository.save(convert(song)))
    }

    @DeleteMapping("/songs/{id}")
    fun deleteSong(@PathVariable id: String) {
        songRepository.deleteById(id)
    }

    private fun convert(songDto: SongDto): Song {
        val audit = Audit(userInserted = songDto.userInserted, dateInserted = songDto.dateInserted, userModified = songDto.userModified, dateModified = songDto.dateModified)
        val status = SongStatus.valueOf(songDto.status)
        return Song(id = null, artist = songDto.artist, title = songDto.title, name = songDto.name, background = songDto.background, youtube = songDto.youtube, status = status, audit = audit)
    }

    private fun convertToDto(song: Song): SongDto {
        return SongDto(song.id, song.artist, song.title, song.name, song.background, song.youtube, song.status.name, song.audit.dateInserted, song.audit.dateModified, song.audit.userInserted, song.audit.userModified)
    }
}
