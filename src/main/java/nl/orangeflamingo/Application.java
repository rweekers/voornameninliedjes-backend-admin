package nl.orangeflamingo;

import nl.orangeflamingo.domain.Song;
import nl.orangeflamingo.domain.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("development")
    public CommandLineRunner demo(SongRepository repository) {
        return args -> {
            // fetch all songs
            log.info("Songs found with findAll():");
            for (Song song : repository.findAll()) {
                log.info(song.getArtist() + " - " + song.getTitle());
            }
        };
    }
}
