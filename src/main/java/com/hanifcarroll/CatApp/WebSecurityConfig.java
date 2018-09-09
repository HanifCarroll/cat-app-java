package com.hanifcarroll.CatApp;

import com.hanifcarroll.CatApp.user.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();

        httpSecurity.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/cats/**").permitAll()
                .antMatchers("/api/cats/*").authenticated()
                .antMatchers(HttpMethod.POST, "/api/register/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                //.antMatchers(HttpMethod.DELETE, "/api/users/**").authenticated()
                .antMatchers(HttpMethod.PATCH, "/api/users/*").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        String password = passwordEncoder().encode("password");
//        auth.inMemoryAuthentication()
//                .withUser("user").password(password).roles("USER");
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("password").roles("ADMIN", "USER");
//    }
}
