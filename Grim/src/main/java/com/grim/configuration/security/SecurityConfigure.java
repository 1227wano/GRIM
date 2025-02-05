package com.grim.configuration.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.grim.auth.util.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfigure {
	
	private final JwtFilter filter;
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true); // 옵션
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
	
	
	@Bean // Bean 애노테이션을 이용해서 빈으로 등록하는 경우 동일한 이름의 메소드가 존재해서는 안됨
	public SecurityFilterChain securityFiltarChain(HttpSecurity httpSecurity) throws Exception {
		/*
		 * return httpSecurity .formLogin().disable() .build(); // HttpSecurity 메서드안에
		 * formlogin.disable() <로그인기능안해!의미> 7.0 버전에서 없어질 문법
		 */
		/*
		 * return httpSecurity .formLogin(new
		 * Customizer<FormLoginConfigurer<HttpSecurity>>() {
		 * 
		 * @Override public void customize(FormLoginConfigurer<HttpSecurity> formLogin)
		 * { formLogin.disable(); } }).httpBasic(null).csrf(null).cors(null).build(); 근본
		 * 작성방법
		 */
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable) // form 로그인 방식은 사용하지 않겠다
				.httpBasic(AbstractHttpConfigurer::disable) // httpBasic 사용하지 않겠다
				.csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
				.cors(Customizer.withDefaults()) // 얘는 일단 꺼놓고 나중에 nginx붙이기
				.authorizeHttpRequests(requests -> {
					requests.requestMatchers("/members", "/members/login", "/uploads/**").permitAll(); // 인증없이 이용할 수 있음
					requests.requestMatchers(HttpMethod.PUT,"/members").authenticated(); // 인증해야 이용할 수 있음
					requests.requestMatchers(HttpMethod.PUT,"/admin/**").hasRole("ADMIN");  //ADMIN 권한만 이용할 수 있음
				})
				/*
				 * sessionManagement : 세션 관리에 대한 설정을 지정할 수 있음
				 * sessionCreationPolicy : 정책을 설정
				 * 
				 */
				.sessionManagement(sessionManagement -> 
								   sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}
	
	

}
