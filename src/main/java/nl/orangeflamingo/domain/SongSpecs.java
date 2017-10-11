package nl.orangeflamingo.domain;

import org.springframework.data.jpa.domain.Specification;

public class SongSpecs {

    private SongSpecs() {
        // prevent from instantiating
    }
    
    public static Specification<Song> songWithArtistOrTitleLike(String queryString) {
        return (Specification<Song>) (root, query, builder) ->
                builder.or(builder.like(builder.lower(root.get("artist")), getLowercaseQueryWithWildcards(queryString)),
                builder.like(builder.lower(root.get("title")), getLowercaseQueryWithWildcards(queryString)));
    }

    private static String getLowercaseQueryWithWildcards(String query) {
        return "%" + query.toLowerCase() + "%";
    }
}
