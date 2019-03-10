package nl.orangeflamingo.voornameninliedjesbackendadmin

import nl.orangeflamingo.voornameninliedjesbackendadmin.config.SecurityConfig
import nl.orangeflamingo.voornameninliedjesbackendadmin.controller.SongController
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.LogEntry
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Song
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.SongStatus
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.SongRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.time.Instant


@SpringBootApplication
@ComponentScan(basePackageClasses = [SongRepository::class, SongController::class, SecurityConfig::class])
@EnableMongoRepositories(value = ["nl.orangeflamingo.voornameninliedjesbackendadmin.repository"])
class AdminSongApplication {
    private val log = LoggerFactory.getLogger(AdminSongApplication::class.java)

    @Bean
    @Profile("!pro")
    fun init(songRepository: SongRepository) = CommandLineRunner {
        val songList = listOf<Song>(
                Song("1", "Michael Jackson", "Ben", "Ben", null, null, null, SongStatus.SHOW, mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("2", "Neil Diamond", "Sweet Caroline", "Caroline", null, null, null, SongStatus.SHOW, mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("3", "The Police", "Roxanne", "Roxanne", null, null, null, SongStatus.SHOW, mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("4", "Dolly Parton", "Jolene", "Jolene", null, null, null, SongStatus.IN_PROGRESS, mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("5", "The Kinks", "Lola", "Lola", null, null, null, SongStatus.IN_PROGRESS, mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco")))
        )
        songRepository.saveAll(songList)
        log.info("Saving ${songList.size} songs")
    }
}

fun main(args: Array<String>) {
    runApplication<AdminSongApplication>(*args)
}

