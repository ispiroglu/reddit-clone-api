package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.RegisterRedditorRequest
import com.ispiroglu.redditclone.domain.dto.response.KeycloakTokenResponse
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import javax.transaction.Transactional
import com.ispiroglu.redditclone.extensions.mutableMultiValueMapOf
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.representations.idm.GroupRepresentation
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.util.function.Function
import java.util.function.Predicate

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val redditorService: RedditorService,
    private val realm: RealmResource,
    private val loginClient: WebClient
    ) {

    @Transactional
    fun signup(request: RegisterRedditorRequest) : KeycloakTokenResponse{
        val credentialRepresentation = CredentialRepresentation().apply {
            isTemporary = false
            value = request.password
            type = CredentialRepresentation.PASSWORD
        }

        val userRepresentation = UserRepresentation().apply {
            isEnabled = true
            username = request.username
            credentials = listOf(credentialRepresentation)
        }

        redditorService.createRedditor(request)
        realm.users().create(userRepresentation)
        return login(request.username, request.password)
    }

    fun login(username: String, password: String): KeycloakTokenResponse {
        val token = getToken(username, password)
        return token ?: throw NoSuchElementException("There is no user with this credentials.")
    }

    fun createGroupWithName(groupName: String) {
        val groupRep = GroupRepresentation().apply {
            name = groupName

        }
        val myUser = realm.users().search(getRequestOwnerUsername())[0]
        realm.groups().add(groupRep)
        val id = realm.groups().groups().find { it.name == groupName }?.id ?: throw IllegalArgumentException()
        realm.users().get(myUser.id).joinGroup(id)
    }


    /*
    * TODO: Should handle wrong credentials!
    * */
    private fun getToken(username: String, password: String): KeycloakTokenResponse? {
        return loginClient.post()
            .uri("http://localhost:8000/realms/reddit-realm/protocol/openid-connect/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(bodyOfAccessToken(username, password))
            .retrieve()
            .bodyToMono<KeycloakTokenResponse>()
            .block()
    }


    private fun bodyOfAccessToken(username: String, password: String): MultiValueMap<String, String> = mutableMultiValueMapOf<String, String>(
        OAuth2Constants.GRANT_TYPE to OAuth2Constants.PASSWORD,
        OAuth2Constants.CLIENT_ID to "reddit-client",
        OAuth2Constants.USERNAME to username,
        OAuth2Constants.PASSWORD to password,
//        OAuth2Constants.CLIENT_SECRET to "dRc6llNYybr Z7BpdemCdnjWv2Tx3tuI8"
    )

    private fun wrongCredentials(): Function<ClientResponse, Mono<out Throwable>> {
        throw NoSuchElementException("Wrong Credentials!")
    }

    private fun nice(): Function<ClientResponse, Mono<out Throwable>> {
        throw NoSuchElementException("Nice Credentials!")
    }

    companion object {
        fun getRequestOwnerUsername(): String {
            val authentication = SecurityContextHolder.getContext().authentication as KeycloakAuthenticationToken
            val acc = authentication.details as SimpleKeycloakAccount
            return acc.keycloakSecurityContext.token.preferredUsername
        }
    }
}