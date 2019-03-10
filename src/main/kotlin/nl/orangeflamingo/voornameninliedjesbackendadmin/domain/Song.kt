package nl.orangeflamingo.voornameninliedjesbackendadmin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import javax.annotation.Generated

@CompoundIndex(name = "song_uq_idx", def = "{'artist': 1, 'title': 1, 'name': 1}", unique = true)
@Document(collection = "Songs")
data class Song(
        @Id
        @Generated
        val id: String?,

        @Field("artist")
        var artist: String,

        @Field("title")
        var title: String,

        @Field("name")
        var name: String,

        var background: String?,

        var youtube: String?,

        var spotify: String?,

        var status: SongStatus,

        val logs: MutableList<LogEntry> = mutableListOf()
) {
    override fun toString(): String {
        return "Song(name=$artist, code=$title"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Song)
            return false
        return artist == other.artist && title == other.title && name == other.name
    }

    override fun hashCode(): Int {
        return Objects.hash(artist, title, name)
    }
}