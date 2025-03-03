package spring_review.spring.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring_review.spring.domain.Refresh;

@Repository
@RequiredArgsConstructor
@Transactional
public class RefreshRepository {

    private final EntityManager em;

    public void save(Refresh refresh) {
        em.persist(refresh);
    }
    public Boolean existsByRefresh(String refresh) {
        Refresh result = em.createQuery("select r from Refresh r where r.refresh = :refresh", Refresh.class)
                .setParameter("refresh", refresh)
                .getSingleResult();
        return (result != null);
    }

    public void deleteByRefresh(String refresh) {
        em.createQuery("delete Refresh r where r.refresh = :refresh")
                .setParameter("refresh", refresh)
                .executeUpdate();
    }
}
