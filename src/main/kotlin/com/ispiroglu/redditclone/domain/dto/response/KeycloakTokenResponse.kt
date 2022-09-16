package com.ispiroglu.redditclone.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class KeycloakTokenResponse(
    @JsonProperty("access_token")
    var accessToken: String,

    @JsonProperty("expires_in")
    var expiresIn: Long,

    @JsonProperty("refresh_expires_in")
    var refreshExpiresIn: Long,

    @JsonProperty("refresh_token")
    var refreshToken: String,

    @JsonProperty("token_type")
    var tokenType: String,

    @JsonProperty("not-before-policy")
    var notBeforePolicy: Long,

    @JsonProperty("session_state")
    var sessionState: String,

    @JsonProperty("scope")
    var scope: String
)
