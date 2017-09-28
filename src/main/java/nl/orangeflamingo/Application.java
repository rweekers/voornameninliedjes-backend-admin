package nl.orangeflamingo;

import nl.orangeflamingo.domain.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(SongRepository repository) {
        return (args) -> {
            // fetch all customers
            log.info("Songs found with findAll():");
            log.info("-------------------------------");
            for (Song song : repository.findAll()) {
                log.info(song.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Song song = repository.findById(1L).orElseThrow(() -> new RuntimeException("Song not found"));
            log.info("Song found with findOne(1L):");
            log.info("--------------------------------");
            log.info(song.toString());
            log.info("");

            // fetch customers by artist
            log.info("Song found with findByArtist('Nirvana'):");
            log.info("--------------------------------------------");
            for (Song nirvana : repository.findByArtistIgnoreCase("Nirvana")) {
                log.info(nirvana.toString());
            }
            log.info("");
        };
    }
}