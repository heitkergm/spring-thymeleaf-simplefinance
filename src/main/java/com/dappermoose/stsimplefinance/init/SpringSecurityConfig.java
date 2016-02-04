
package com.dappermoose.stsimplefinance.init;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import com.dappermoose.stsimplefinance.security.AuthenticationListener;

/**
 *Spring Security config.
 *
 * @author matthewheitker
 */
@Configuration
@Order (SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Resource (name = "authService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure (final HttpSecurity http) throws Exception
    {
        http
           .formLogin ().loginPage ("/login").permitAll ()
        .and ()
           .authorizeRequests ()
               .antMatchers ("/images/**", "/css/**", "**/favicon.ico",
                             "/webjarslocator/**", "/register").permitAll ()
               .anyRequest ().authenticated ()
        .and ()
            .csrf ()
        .and ()
            .requiresChannel ()
                .anyRequest ().requiresSecure ()
        .and ()
            .logout ().invalidateHttpSession (true).logoutSuccessUrl ("/main")
                .clearAuthentication (true)
                .deleteCookies (CookieThemeResolver.DEFAULT_COOKIE_NAME,
                                CookieLocaleResolver.DEFAULT_COOKIE_NAME)
        .and ()
            .sessionManagement ().sessionCreationPolicy (SessionCreationPolicy.ALWAYS)
                .sessionFixation ()
     ;
    }

    @Override
    protected void configure (final AuthenticationManagerBuilder auth) throws Exception
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
        auth.userDetailsService (userDetailsService).passwordEncoder (encoder);
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
