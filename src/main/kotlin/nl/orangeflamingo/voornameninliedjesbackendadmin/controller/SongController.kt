package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.*
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.FlickrPhotoDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.SongDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.SourceDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.WikimediaPhotoDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.SongRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.time.Instant


@RestController
@RequestMapping("/api")
class SongController {
    private val log = LoggerFactory.getLogger(SongController::class.java)

    @Autowired
    private lateinit var songRepository: SongRepository

    val restTemplate: RestTemplate = RestTemplate()

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs")
    @CachePut(value = ["songs"])
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getSongs(): List<SongDto> {
        return songRepository.findAll(/*Sort(Sort.Direction.ASC, "name")*/).map { convertToDto(it) }.map { enrichSong(it) }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs", params = ["name"])
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getSongsByName(@RequestParam(name = "name") name: String): List<SongDto> {
        return songRepository.findAllByNameContainingIgnoreCaseOrderByName(name).map { convertToDto(it) }.map { enrichSong(it) }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs", params = ["fist-character"])
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getSongsWithNameStartingWith(@RequestParam(name = "fist-character") firstCharacter: String): List<SongDto> {
        return songRepository.findAllByNameStartsWithIgnoreCaseOrderByName(firstCharacter).map { convertToDto(it) }.map { enrichSong(it) }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs/{id}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getSongById(@PathVariable("id") id: String): SongDto {
        return songRepository.findById(id).map { convertToDto(it) }.map { enrichSong(it) }.orElseThrow { RuntimeException("Song with $id not found") }
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
            val songDB = songFromDb.get()
            songDB.artist = song.artist
            songDB.title = song.title
            songDB.name = song.name
            songDB.status = SongStatus.valueOf(song.status)
            songDB.background = song.background
            songDB.youtube = song.youtube
            songDB.spotify = song.spotify
            songDB.wikimediaPhotos = song.wikimediaPhotos.map { w -> convertToDomain(w) }.toMutableSet()
            songDB.flickrPhotos = song.flickrPhotos.toMutableSet()
            songDB.sources = song.sources.map { s -> convertToDomain(s) }.toMutableSet()
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
        val songOptional = songRepository.findById(id)
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
        return Song(id = null, artist = songDto.artist, title = songDto.title, name = songDto.name, background = songDto.background, youtube = songDto.youtube, spotify = songDto.spotify, sources = songDto.sources.map { s -> convertToDomain(s) }.toMutableSet(), status = status, logs = logs)
    }

    private fun convertToDto(song: Song): SongDto {
        return SongDto(
                id = song.id,
                artist = song.artist,
                title = song.title,
                name = song.name,
                artistImage = "https://ak9.picdn.net/shutterstock/videos/24149239/thumb/1.jpg",
                background = song.background,
                youtube = song.youtube,
                spotify = song.spotify,
                status = song.status.name,
                wikimediaPhotos = song.wikimediaPhotos.map { w -> convertToDto(w) }.toSet(),
                flickrPhotos = song.flickrPhotos,
                sources = song.sources.map { s -> convertToDto(s) }.toSet(),
                logs = song.logs
        )
    }

    private fun convertToDto(wikimediaPhoto: WikimediaPhoto): WikimediaPhotoDto {
        return WikimediaPhotoDto(wikimediaPhoto.url, wikimediaPhoto.attribution)
    }

    private fun convertToDomain(wikimediaPhotoDto: WikimediaPhotoDto): WikimediaPhoto {
        return WikimediaPhoto(wikimediaPhotoDto.url, wikimediaPhotoDto.attribution)
    }

    private fun convertToDomain(sourceDto: SourceDto): Source {
        return Source(url = sourceDto.url, name = sourceDto.name)
    }

    private fun convertToDto(source: Source): SourceDto {
        return SourceDto(url = source.url, name = source.name)
    }

    private fun enrichSong(song: SongDto): SongDto {
        val wikimediaPhoto = song.wikimediaPhotos.firstOrNull()
        if (wikimediaPhoto != null) {
            return song.copy(artistImage = wikimediaPhoto.url)
        }
        val flickrPhotoId = song.flickrPhotos.firstOrNull()
        if (flickrPhotoId != null) {
            val photo = restTemplate.getForObject("https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=9676a28e9cb321d2721e813055abb6dc&format=json&nojsoncallback=true&photo_id=" + flickrPhotoId, FlickrPhotoDto::class.java)?.photo


            if (photo != null) {
                return song.copy(artistImage = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_c.jpg")
            }
        }
        return song
    }
}
