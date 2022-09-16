package com.ispiroglu.redditclone.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class WebSecurityConfig {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity) : SecurityFilterChain {
        httpSecurity.csrf().disable().authorizeRequests().anyRequest().permitAll()
        return httpSecurity.build()
    }

//    @Bean
//    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
////        http.csrf().disable()
////            .authorizeRequests()
////            .antMatchers("/api/v1/auth/**")
////            .permitAll()
////            .anyRequest()
////            .authenticated()
//
//        http.csrf().disable()
//            .authorizeRequests().anyRequest().permitAll()
//
//        return http.build()
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}