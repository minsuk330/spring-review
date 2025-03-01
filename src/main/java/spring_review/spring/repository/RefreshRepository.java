package spring_review.spring.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring_review.spring.domain.Refresh;

@Repository
@RequiredArgsConstructor
public class RefreshRepository {

    private final EntityManager em;

    @Transactional
    public void save(Refresh refresh) {
        em.persist(refresh);
    }
}
