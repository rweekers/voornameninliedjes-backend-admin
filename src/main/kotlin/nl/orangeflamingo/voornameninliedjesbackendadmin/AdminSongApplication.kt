package nl.orangeflamingo.voornameninliedjesbackendadmin

import nl.orangeflamingo.voornameninliedjesbackendadmin.controller.SongController
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Song
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.SongRepository
import nl.orangeflamingo.voornameninliedjesbackendadmin.config.SecurityConfig
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Audit
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.SongStatus
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
@EnableMongoRepositories(value = ["nl.orangeflamingo.voornameninliedjesbackendadmin.repository"])
class AdminSongApplication {
    private val log = LoggerFactory.getLogger(AdminSongApplication::class.java)

    @Bean
    fun init(songRepository: SongRepository) = CommandLineRunner {
        val songList = listOf<Song>(
                Song("1", "Michael Jackson", "Ben", "Ben", null, null, SongStatus.SHOW, Audit(dateInserted = Instant.now(), userInserted =  "Remco")),
                Song("2", "Neil Diamond", "Sweet Caroline", "Caroline", null, null, SongStatus.SHOW, Audit(dateInserted = Instant.now(), userInserted =  "Remco")),
                Song("3", "The Police", "Roxanne", "Roxanne", null, null, SongStatus.SHOW, Audit(dateInserted = Instant.now(), userInserted =  "Remco")),
                Song("4", "Dolly Parton", "Jolene", "Jolene", null, null, SongStatus.IN_PROGRESS, Audit(dateInserted = Instant.now(), userInserted =  "Remco")),
                Song("5", "The Kinks", "Lola", "Lola", null, null, SongStatus.IN_PROGRESS, Audit(dateInserted = Instant.now(), userInserted =  "Remco"))
        )
        songRepository.saveAll(songList)
        log.info("Saving ${songList.size} songs")
    }
}

fun main(args: Array<String>) {
    runApplication<AdminSongApplication>(*args)
}

