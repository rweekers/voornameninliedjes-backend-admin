package nl.orangeflamingo.voornameninliedjesbackend.repository

import nl.orangeflamingo.voornameninliedjesbackend.domain.Song
import org.springframework.data.mongodb.repository.MongoRepository

interface SongRepository : MongoRepository<Song, String>