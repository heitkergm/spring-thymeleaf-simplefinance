
package com.dappermoose.stsimplefinance.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dappermoose.stsimplefinance.security.AuthenticationListener;


/**
 *Spring Security config.
 *
 * @author matthewheitker
 */
@Configuration
public class SpringSecurityConfig
{
    @Bean
    SecurityFilterChain securityFilterChain (final HttpSecurity http) throws Exception
    {
        http
            .formLogin (formLogin ->
                formLogin.loginPage ("/login").permitAll ()
            )
            .authorizeHttpRequests (authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers ("/images/**", "/css/**", "**/favicon.ico",
                                      "/webjars/**", "/register", "/actuator/**")
                    .permitAll ().anyRequest ().authenticated ()
            )
            .requiresChannel (requiresChannel ->
                requiresChannel.anyRequest ().requiresSecure ()
            )
            .logout (logout ->
                logout.invalidateHttpSession (true).logoutSuccessUrl ("/main")
                      .clearAuthentication (true)
            )
            .sessionManagement (sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy (SessionCreationPolicy.ALWAYS)
                    .sessionFixation ()
            )
        ;
        return http.build ();
    }

    @Bean
    PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder ();
    }

    /**
     * Authentication Listener.
     *
     * @return the authentication listener
     */
    @Bean
    AuthenticationListener authenticationListener ()
    {
        return new AuthenticationListener ();
    }
}
