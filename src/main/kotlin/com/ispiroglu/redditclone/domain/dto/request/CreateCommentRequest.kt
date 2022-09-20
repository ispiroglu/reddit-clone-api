package com.ispiroglu.redditclone.domain.dto.request

data class CreateCommentRequest(
    val text: String,
    val postTitle: String,
    val subredditName: String
)