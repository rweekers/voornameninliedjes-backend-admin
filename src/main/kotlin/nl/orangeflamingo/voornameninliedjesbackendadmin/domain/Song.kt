package nl.orangeflamingo.voornameninliedjesbackend.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "Songs")
data class Song(
        @Id
        val id: String,
        val artist: String,
        val title: String,
        val name: String,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Europe/Amsterdam")
        val dateInserted: Instant,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Europe/Amsterdam")
        val dateModified: Instant?,

        val userInserted: String,
        val userModified: String?
)