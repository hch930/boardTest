package com.hch.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hch.jwt.JwtAccessDeniedHandler;
import com.hch.jwt.JwtAuthenticationEntryPoint;
import com.hch.jwt.JwtSecurityConfig;
import com.hch.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//FilterChainProxy를 생성
	@Override
	public void configure(WebSecurity web) throws Exception {
		//static 디렉터리 하위 파일 목록은 항상 통과(인증x)
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/vendor/**", "/scss/**");
	}

	//HttpSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//페이지 권한 설정
				.antMatchers("/api/hello").permitAll()
				.antMatchers("/api/authenticate").permitAll()
				.antMatchers("/api/signup").permitAll()
				.anyRequest().authenticated()
			.and()
				.csrf().disable()
				//예외처리
				.exceptionHandling()
	            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	            .accessDeniedHandler(jwtAccessDeniedHandler)
	        .and()
	        	.headers()
	        	.frameOptions()
	        	.sameOrigin()
	        .and()
	        	//세션 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .apply(new JwtSecurityConfig(tokenProvider));
	}

	// AuthenticationManagerBuilder를 통해 AuthenticationManager를 생성하여 모든 인증을 처리
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}
}
