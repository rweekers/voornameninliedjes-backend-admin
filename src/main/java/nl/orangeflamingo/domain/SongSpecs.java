package nl.orangeflamingo.domain;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SongSpecs {

    public static Specification<Song> isLongTermCustomer() {
        return new Specification<Song>() {
            public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return null;
            }
        };
    }
}
