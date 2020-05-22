package nl.orangeflamingo.voornameninliedjesbackendadmin.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.LogEntry
import java.time.Instant
import java.util.*

data class SongDto(

        val id: String?,

        val artist: String,

        val title: String,

        val name: String,

        val artistImage: String?,

        val background: String?,

        val youtube: String?,

        val spotify: String?,

        val status: String,

        val wikimediaPhotos: Set<WikimediaPhotoDto> = setOf(),

        val flickrPhotos: Set<String> = setOf(),

        val sources: Set<SourceDto> = setOf(),

        @JsonIgnoreProperties(allowGetters = true)
        val logs: List<LogEntry> = listOf()

) {
    override fun toString(): String {
        return "Song(name=$artist, code=$title"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SongDto)
            return false
        return artist == other.artist && title == other.title && name == other.name
    }

    override fun hashCode(): Int {
        return Objects.hash(artist, title, name)
    }
}

data class WikimediaPhotoDto(
        val url: String,
        val attribution: String
)

data class PhotoDto(
        val farm: String,
        val server: String,
        val id: String,
        val secret: String
)

data class FlickrPhotoDto(
        val photo: PhotoDto?
)

data class SourceDto(
        val url: String,
        val name: String
)

data class LogEntryDto(
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Europe/Amsterdam")
        val date: Instant = Instant.now(),
        val user: String
)