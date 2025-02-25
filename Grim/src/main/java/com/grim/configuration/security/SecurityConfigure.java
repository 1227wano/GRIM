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

    @Bean 
    public SecurityFilterChain securityFiltarChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.formLogin(AbstractHttpConfigurer::disable) // form 로그인 방식은 사용하지 않겠다
                .httpBasic(AbstractHttpConfigurer::disable) // httpBasic 사용하지 않겠다
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(requests -> {
                	requests.requestMatchers("/upfiles/**").permitAll();
                	requests.requestMatchers("/kakao/callback", "/kakao/callback/**").permitAll();
                    requests.requestMatchers("/members", "/members/login","/members/signup","/museum/**").permitAll();
                    requests.requestMatchers(HttpMethod.GET,"/members/mypage/**", "/members/mypage/update").authenticated(); 
                    requests.requestMatchers(HttpMethod.GET,"/paint/**").permitAll();
                    requests.requestMatchers(HttpMethod.GET, "/upfiles/**").permitAll(); 
                    requests.requestMatchers(HttpMethod.POST,"/paint", "/museum").authenticated();
                    requests.requestMatchers(HttpMethod.PUT,"/admin/**").hasRole("ADMIN");
                    requests.requestMatchers(HttpMethod.PUT,"/members/mypage/password", "/members/mypage/update", "/members/mypage/imgupdate").authenticated(); 
                    requests.requestMatchers(HttpMethod.PUT, "/paint/**", "/museum").authenticated();  
                    requests.requestMatchers(HttpMethod.DELETE,"/members/mypage/leave").authenticated(); 
                    requests.requestMatchers(HttpMethod.DELETE, "/paint/**", "/museum").authenticated();  
                })
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
