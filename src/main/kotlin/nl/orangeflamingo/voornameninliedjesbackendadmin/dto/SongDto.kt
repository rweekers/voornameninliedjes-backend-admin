package nl.orangeflamingo.voornameninliedjesbackendadmin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonView
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.LogEntry
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.WikimediaPhoto
import java.util.*

data class SongDto(

        val id: String?,

        val artist: String,

        val title: String,

        val name: String,

        val background: String?,

        val youtube: String?,

        val spotify: String?,

        val status: String,

        val wikimediaPhotos: Set<WikimediaPhotoDto> = setOf(),

        val flickrPhotos: Set<String> = setOf(),

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