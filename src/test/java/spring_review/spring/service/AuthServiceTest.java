package spring_review.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import spring_review.spring.domain.Member;
import spring_review.spring.dto.MemberRegistrationDto;
import spring_review.spring.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class AuthServiceTest {
    @Autowired
    private AuthService authService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원_등록() {
        MemberRegistrationDto memberRegistrationDto = new MemberRegistrationDto();
        memberRegistrationDto.setEmail("test@email");
        memberRegistrationDto.setPassword("123456");
        authService.register(memberRegistrationDto);
        Member byEmail = memberRepository.findByEmail("test@email");
        assertNotNull(byEmail);
    }


}