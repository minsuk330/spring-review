package spring_review.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    static public Member create(String email, String password, RoleType role) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.role = role;
        return member;
    }

}
