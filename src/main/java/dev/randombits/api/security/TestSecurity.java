package dev.randombits.api.security;

import dev.randombits.api.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TestSecurity {

//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;

//    @Autowired
//    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        return auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder()).and().build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/welcome", "/authenticate", "/graphiql", "/graphql").permitAll()
                        .anyRequest().authenticated()
                )
//                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
