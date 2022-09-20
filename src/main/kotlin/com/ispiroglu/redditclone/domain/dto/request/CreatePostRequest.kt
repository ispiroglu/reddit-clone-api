package com.ispiroglu.redditclone.domain.dto.request

import javax.persistence.Lob

data class CreatePostRequest(
    val title: String,
    val desc: String,
    val subredditName: String
)
