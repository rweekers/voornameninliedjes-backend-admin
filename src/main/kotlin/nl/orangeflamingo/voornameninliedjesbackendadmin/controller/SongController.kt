package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.LogEntry
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Song
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.SongStatus
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.SongDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.SongRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/api")
class SongController {
    private val log = LoggerFactory.getLogger(SongController::class.java)

    @Autowired
    private lateinit var songRepository: SongRepository

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getSongs(): List<SongDto> {
        return songRepository.findAll().map { convertToDto(it) }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs/{id}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getSongById(@PathVariable("id") id: String): SongDto {
        return songRepository.findById(id).map { convertToDto(it) }.orElseThrow { RuntimeException("Song with $id not found") }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/songs/{user}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun newSong(@RequestBody newSong: SongDto, @PathVariable user: String): SongDto {
        val logEntry = LogEntry(Instant.now(), user)
        return convertToDto(songRepository.save(convert(newSong, mutableListOf(logEntry))))
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/songs/{user}/{id}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun replaceSong(@RequestBody song: SongDto, @PathVariable user: String, @PathVariable id: String): SongDto {
        assert(song.id == id)
        val logEntry = LogEntry(Instant.now(), user)
        val songFromDb = songRepository.findById(id)
        if (songFromDb.isPresent) {
            var songDB = songFromDb.get()
            songDB.artist = song.artist
            songDB.title = song.title
            songDB.name = song.name
            songDB.status = SongStatus.valueOf(song.status)
            songDB.background = song.background
            songDB.youtube = song.youtube
            songDB.spotify = song.spotify
            songDB.flickrPhotos = song.flickrPhotos.toMutableSet()
            songDB.logs.add(logEntry)
            songRepository.save(songDB)
            return convertToDto(songDB)
        }
        return convertToDto(songRepository.save(convert(song, mutableListOf(logEntry))))
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/songs/{user}/{id}/{flickrId}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun addFlickrPhoto(@PathVariable user: String, @PathVariable id: String, @PathVariable flickrId: String) {
        var songOptional = songRepository.findById(id)
        if (songOptional.isPresent) {
            val song = songOptional.get()
            val logEntry = LogEntry(Instant.now(), user)
            song.logs.add(logEntry)
            song.flickrPhotos.add(flickrId)
            songRepository.save(song)
        } else {
            log.warn("Song with id $id not found")
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/songs/{id}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun deleteSong(@PathVariable id: String) {
        songRepository.deleteById(id)
    }

    private fun convert(songDto: SongDto, logs: MutableList<LogEntry>): Song {
        val status = SongStatus.valueOf(songDto.status)
        return Song(id = null, artist = songDto.artist, title = songDto.title, name = songDto.name, background = songDto.background, youtube = songDto.youtube, spotify = songDto.spotify, status = status, logs = logs)
    }

    private fun convertToDto(song: Song): SongDto {
        return SongDto(song.id, song.artist, song.title, song.name, song.background, song.youtube, song.spotify, song.status.name, song.flickrPhotos, song.logs)
    }
}
