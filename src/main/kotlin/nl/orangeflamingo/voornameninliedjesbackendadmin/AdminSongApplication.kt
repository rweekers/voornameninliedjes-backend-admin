package nl.orangeflamingo.voornameninliedjesbackendadmin

import nl.orangeflamingo.voornameninliedjesbackendadmin.config.SecurityConfig
import nl.orangeflamingo.voornameninliedjesbackendadmin.controller.SongController
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.*
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.SongRepository
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.UserRepository
import nl.orangeflamingo.voornameninliedjesbackendadmin.service.MyUserDetailsService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.Instant


@SpringBootApplication
@ComponentScan(basePackageClasses = [SongRepository::class, SongController::class, SecurityConfig::class, MyUserDetailsService::class])
@EnableMongoRepositories(value = ["nl.orangeflamingo.voornameninliedjesbackendadmin.repository"])
class AdminSongApplication {
    private val log = LoggerFactory.getLogger(AdminSongApplication::class.java)

    @Bean
    @Profile("!pro")
    fun init(songRepository: SongRepository, userRepository: UserRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val wikimediaPhoto = WikimediaPhoto("https://upload.wikimedia.org/wikipedia/commons/2/2d/Paul_Simon_in_1982.jpg", "https://upload.wikimedia.org/wikipedia/commons/2/2d/Paul_Simon_in_1982.jpg")

        val songList = listOf<Song>(
                Song("1", "Michael Jackson", "Ben", "Ben", null, null, null, SongStatus.SHOW, mutableSetOf(), mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("2", "Neil Diamond", "Sweet Caroline", "Caroline", null, null, null, SongStatus.SHOW, mutableSetOf(), mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("3", "The Police", "Roxanne", "Roxanne", null, null, null, SongStatus.SHOW, mutableSetOf(), mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("4", "Dolly Parton", "Jolene", "Jolene", null, null, null, SongStatus.IN_PROGRESS, mutableSetOf(), mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("5", "The Kinks", "Lola", "Lola", null, null, null, SongStatus.IN_PROGRESS, mutableSetOf(), mutableSetOf(), mutableListOf<LogEntry>(LogEntry(Instant.now(), "Remco"))),
                Song("6", "Paul Simon", "You Can Call Me Al", "Al", null, null, null, SongStatus.SHOW, mutableSetOf(wikimediaPhoto), mutableSetOf(), mutableListOf(LogEntry(Instant.now(), "Remco")))
        )
        songRepository.saveAll(songList)
        log.info("Saving ${songList.size} songs")

        val userRemco = User(username = "remco", password = passwordEncoder.encode("secret"), roles = mutableSetOf("ADMIN", "OWNER"))
        val userNadja = User(username = "nadja", password = passwordEncoder.encode("secret"), roles = mutableSetOf("ADMIN"))
        userRepository.saveAll(listOf(userRemco, userNadja))
        log.info("Saving users $userRemco and $userNadja")
    }
}

fun main(args: Array<String>) {
    runApplication<AdminSongApplication>(*args)
}

