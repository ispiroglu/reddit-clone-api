package com.ispiroglu.redditclone.config.security

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebMvc
class WebSecurityConfig : WebMvcConfigurer {
//    @Bean
//    fun filterChain(httpSecurity: HttpSecurity) : SecurityFilterChain {
//        httpSecurity.csrf().disable().authorizeRequests().anyRequest().permitAll()
//        return httpSecurity.build()
//    }
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
    .maxAge(3600L)
    .allowedHeaders("*")
//    .exposedHeaders("Authorization")
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
    fun keycloakConfigResolver(): KeycloakConfigResolver? {
        return KeycloakSpringBootConfigResolver()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}