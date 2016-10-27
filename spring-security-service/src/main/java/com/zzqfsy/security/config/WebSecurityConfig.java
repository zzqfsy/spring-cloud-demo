package com.zzqfsy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by john on 16-10-24.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
//                    .accessDeniedHandler(accessDeniedHandler()) // handle access denied in general (for example comming from @PreAuthorization
//                    .authenticationEntryPoint(entryPointBean()) // handle authentication exceptions for unauthorized calls.
            .and()
            .authorizeRequests()
//                    .antMatchers("/hystrix.stream/**", "/info", "/error").permitAll()
            .anyRequest().authenticated().and().csrf().disable();
    }

    //        @Bean
//        @Autowired
//        AccessDeniedHandler accessDeniedHandler() {
//            return new AccessDeniedExceptionHandler();
//        }
//
//        @Bean
//        @Autowired
//        AuthenticationEntryPoint entryPointBean() {
//            return new UnauthorizedEntryPoint();
//        }
    // 不需要权限控制的路径
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hystrix.stream/**", "/info", "/error");
    }
}