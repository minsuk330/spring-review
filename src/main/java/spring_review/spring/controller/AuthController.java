package spring_review.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_review.spring.dto.MemberRegistrationDto;
import spring_review.spring.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * todo : 중복 아이디 처리
     */
    @PostMapping("/api/auth/register")
    public ResponseEntity<String> registration(@ModelAttribute MemberRegistrationDto memberRegistrationDto) {
        try {
            authService.register(memberRegistrationDto);
            return ResponseEntity.ok("회원가입이 완료되었습니다!");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }



}
