package com.townhubzuul.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townhubzuul.model.LoginModel;
import com.townhubzuul.model.ProfileForLoginState;
import com.townhubzuul.model.ProfileModel;
import com.townhubzuul.service.ProfileService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authManager;
    private final JwtConfig jwtConfig;
    private ProfileService profileService;
    private ObjectMapper mapper = new ObjectMapper();

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig,
                                                      ProfileService profileService) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;
        this.profileService = profileService;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(),
                    creds.getPassword(), Collections.emptyList());
            return authManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Long now = System.currentTimeMillis();
        String token = Jwts.builder().setSubject(auth.getName())
                .claim("authorities",
                        auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now)).setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()).compact();
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + " " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        ProfileModel profileModel = profileService.getProfileByUsername(auth.getName());
        response.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new ProfileForLoginState(
                profileModel.getId(), profileModel.getUsername(), profileModel.getRole(), profileModel.isStatus())));
    }
}

