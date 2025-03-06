package spring_review.spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Refresh {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String refresh;
    private String expiration;


    static public Refresh create(String email, String refresh, String expiration) {
        Refresh refreshEntity = new Refresh();
        refreshEntity.email = email;
        refreshEntity.refresh = refresh;
        refreshEntity.expiration = expiration;
        return refreshEntity;
    }
}
