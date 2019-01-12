package nl.orangeflamingo.voornameninliedjesbackendadmin

import nl.orangeflamingo.voornameninliedjesbackend.domain.Song
import nl.orangeflamingo.voornameninliedjesbackend.repository.SongRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AdminSongApplication {
	private val log = LoggerFactory.getLogger(AdminSongApplication::class.java)

	@Bean
	fun init(songRepository: SongRepository) = CommandLineRunner {
		val songList = listOf<Song>(
				Song("1", "Michael Jackson", "Ben", "Ben"),
				Song("2", "Neil Diamond", "Sweet Caroline", "Caroline"),
				Song("3", "The Police", "Roxanne", "Roxanne"),
				Song("4", "Dolly Parton", "Jolene", "Jolene"),
				Song("5", "The Kinks", "Lola", "Lola")
		)
		songRepository.saveAll(songList)
		log.info("Saving ${songList.size} songs")
	}
}

fun main(args: Array<String>) {
	runApplication<AdminSongApplication>(*args)
}

