package spring_review.spring.service;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_review.spring.domain.Member;
import spring_review.spring.domain.RoleType;
import spring_review.spring.dto.MemberRegistrationDto;
import spring_review.spring.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void register(MemberRegistrationDto memberRegistrationDto) {
        String email = memberRegistrationDto.getEmail();
        String password = memberRegistrationDto.getPassword();

        if(memberRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        Member member = Member.create(email, bCryptPasswordEncoder.encode(password), RoleType.ROLE_USER);

        try {
            memberRepository.save(member);
        }
        catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

    }
}
