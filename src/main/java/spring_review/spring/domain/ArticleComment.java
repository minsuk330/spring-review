package spring_review.spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter @Getter
public class ArticleComment {
    @Id @GeneratedValue
    private Long id;
    private String content;
    private LocalDateTime createdAt;

}
