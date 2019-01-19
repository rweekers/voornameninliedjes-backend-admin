package nl.orangeflamingo.voornameninliedjesbackend.domain

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
        val dateInserted: Instant,
        val dateModified: Instant?,
        val userInserted: String,
        val userModified: String?
)