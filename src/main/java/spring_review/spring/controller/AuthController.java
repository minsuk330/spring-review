package spring_review.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_review.spring.dto.MemberRegistrationDto;
import spring_review.spring.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/register")
    public String registration(@ModelAttribute MemberRegistrationDto memberRegistrationDto) {
        authService.register(memberRegistrationDto);
        return "success";
    }



}
