package com.ispiroglu.redditclone.domain.dto.request

import javax.validation.constraints.Email

data class RegisterRedditorRequest(
    val username: String,
    @Email val email: String,
    val password: String
)
