package nl.orangeflamingo.voornameninliedjesbackendadmin.repository

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Song
import org.springframework.data.mongodb.repository.MongoRepository

interface SongRepository : MongoRepository<Song, String> {

    fun findAllByNameContainingIgnoreCaseOrderByName(name: String): List<Song>

    fun findAllByNameStartsWithIgnoreCaseOrderByName(startingCharacter: String): List<Song>
}