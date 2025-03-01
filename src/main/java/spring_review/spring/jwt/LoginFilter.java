package spring_review.spring.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring_review.spring.domain.Refresh;
import spring_review.spring.repository.RefreshRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> authoritiesIterator = authorities.iterator();
        GrantedAuthority authority = authoritiesIterator.next();
        String role = authority.getAuthority();

        String access = jwtUtil.createJwt("access", email, role, 600000L);
        String refresh = jwtUtil.createJwt("refresh", email, role, 86400000L);

        //refresh 엔티티 추가 함수
        addRefreshEntity(refresh, email);

        //헤더에 정보 추가
        response.setHeader("access", access);
        response.addCookie(createCookie("refresh",refresh));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void addRefreshEntity(String refresh, String email) {

        Date date = new Date(System.currentTimeMillis() + 8640000L);

        Refresh refreshEntity = Refresh.create(email, refresh, date.toString());
        refreshRepository.save(refreshEntity);
    }


    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
