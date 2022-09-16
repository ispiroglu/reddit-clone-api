package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.RegisterRedditorRequest
import com.ispiroglu.redditclone.domain.dto.response.KeycloakTokenResponse
import com.ispiroglu.redditclone.domain.model.Redditor
import com.ispiroglu.redditclone.repository.RedditorRepository
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import java.time.Instant
import javax.transaction.Transactional
import com.ispiroglu.redditclone.extensions.mutableMultiValueMapOf
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val redditorRepository: RedditorRepository,
    private val realm: RealmResource,
    private val loginClient: WebClient
    ) {

    @Transactional
    fun signup(request: RegisterRedditorRequest) : KeycloakTokenResponse{
        /*
        * TODO: UserVerification?
        * */
        val redditor = Redditor(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            createdDate = Instant.now())

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

        val createdUser = redditorRepository.save(redditor)
        realm.users().create(userRepresentation)

        val token = getToken(userRepresentation.username, userRepresentation.credentials[0].value)

        return token ?: throw IllegalArgumentException()
    }

    fun getToken(username: String, password: String): KeycloakTokenResponse? {
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
        OAuth2Constants.CLIENT_SECRET to "dRc6llNYybrZ7BpdemCdnjWv2Tx3tuI8"
    )
}