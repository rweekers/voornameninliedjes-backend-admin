package nl.orangeflamingo.domain;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SongSpecs {

    public static Specification<Song> songWithArtistOrTitleLike(String queryString) {
        return new Specification<Song>() {
            public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.or(builder.like(builder.lower(root.get("artist")), getLowercaseQueryWithWildcards(queryString)),
                        builder.like(builder.lower(root.get("title")), getLowercaseQueryWithWildcards(queryString)));
            }
        };
    }

    public static String getLowercaseQueryWithWildcards(String query) {
        return "%" + query.toLowerCase() + "%";
    }
}
