package nl.orangeflamingo.voornameninliedjesbackendadmin

import nl.orangeflamingo.voornameninliedjesbackend.controller.SongController
import nl.orangeflamingo.voornameninliedjesbackend.domain.Song
import nl.orangeflamingo.voornameninliedjesbackend.repository.SongRepository
import nl.orangeflamingo.voornameninliedjesbackendadmin.config.SecurityConfig
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.time.Instant


@SpringBootApplication
@ComponentScan(basePackageClasses = [SongRepository::class, SongController::class, SecurityConfig::class])
@EnableMongoRepositories(value = ["nl.orangeflamingo.voornameninliedjesbackend.repository"])
class AdminSongApplication {
    private val log = LoggerFactory.getLogger(AdminSongApplication::class.java)

    @Bean
    fun init(songRepository: SongRepository) = CommandLineRunner {
        val songList = listOf<Song>(
                Song("1", "Michael Jackson", "Ben", "Ben", Instant.now(), null, "Remco", "null"),
                Song("2", "Neil Diamond", "Sweet Caroline", "Caroline", Instant.now(), null, "Remco", "null"),
                Song("3", "The Police", "Roxanne", "Roxanne", Instant.now(), null, "Remco", "null"),
                Song("4", "Dolly Parton", "Jolene", "Jolene", Instant.now(), null, "Remco", "null"),
                Song("5", "The Kinks", "Lola", "Lola", Instant.now(), null, "Remco", "null")
        )
        songRepository.saveAll(songList)
        log.info("Saving ${songList.size} songs")
    }
}

fun main(args: Array<String>) {
    runApplication<AdminSongApplication>(*args)
}

