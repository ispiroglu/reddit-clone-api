package com.ispiroglu.redditclone.config.security

import io.netty.channel.group.DefaultChannelGroup
import io.netty.util.concurrent.DefaultEventExecutor
import org.jboss.resteasy.client.jaxrs.ResteasyClient
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.resteasy.client.jaxrs.engines.ReactorNettyClientHttpEngine
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.admin.client.resource.RealmResource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.HttpResources
import reactor.netty.http.client.HttpClient
import javax.ws.rs.client.ClientBuilder

@Configuration
class KeycloakConfig {
    @Bean
    fun reactorNettyClientHttpEngine() = ReactorNettyClientHttpEngine(
        HttpClient.create(),
        DefaultChannelGroup(DefaultEventExecutor()),
        HttpResources.get()
    )

    @Bean
    fun resteasyClient(reactorNettyClientHttpEngine: ReactorNettyClientHttpEngine): ResteasyClient = (ClientBuilder.newBuilder() as ResteasyClientBuilder)
        .httpEngine(reactorNettyClientHttpEngine)
        .build() as ResteasyClient

//    @Bean
//    fun keycloak(resteasyClient: ResteasyClient) =
//        KeycloakBuilder.builder()
//            .serverUrl("localhost:8000/auth")
//            .realm("master")
//            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//            .clientId("admin-client")
//            .clientSecret("5pYMMb9IXBJgCO9ugef2tXTK6syt3cTS")
//            .resteasyClient(resteasyClient)
//            .build()!!


    @Bean
    fun keycloak() : Keycloak  = Keycloak.getInstance(
        "http://localhost:8000",
        "master", "admin",  "admin", "admin-cli"
    )


    @Bean
    fun customerRealm(keycloak: Keycloak): RealmResource = keycloak.realm("reddit-realm")

    @Bean
    fun getWebClient() : WebClient =  WebClient.create()

}