package nl.orangeflamingo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByArtistLikeIgnoreCase(String artist);

    List<Song> findByTitleLikeIgnoreCase(String title);

    List<Song> findAll(Specification<Song> spec);

    Page<Song> findAll(Specification<Song> spec, Pageable page);

    Page<Song> findAll(Pageable page);

}
