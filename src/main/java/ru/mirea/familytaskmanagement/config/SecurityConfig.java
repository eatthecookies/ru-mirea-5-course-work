package ru.mirea.familytaskmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.mirea.familytaskmanagement.models.UserRole;
import ru.mirea.familytaskmanagement.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/register", "/auth/register", "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority(UserRole.Взрослый.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(UserRole.Взрослый.name())
                        .anyRequest().authenticated() // Все остальные страницы требуют авторизации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Указание кастомной страницы входа
                        .defaultSuccessUrl("/tasks", true) // Перенаправление на главную страницу после успешного входа
                        .permitAll() // Разрешаем доступ ко всем на страницу входа
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода
                        .logoutSuccessUrl("/login?logout=true") // Перенаправление после выхода
                        .permitAll()
                );

        return http.build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
