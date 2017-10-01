package nl.orangeflamingo.domain;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SongSpecs {

    public static Specification<Song> songWithArtistOrTitleLike(String query) {
        return new Specification<Song>() {
            public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.like(root.get("artist"), "*" + query + "*");
            }
        };
    }
}
