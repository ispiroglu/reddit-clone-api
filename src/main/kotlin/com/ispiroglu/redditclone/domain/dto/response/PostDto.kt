package com.ispiroglu.redditclone.domain.dto.response

data class PostDto(
    val title: String,
    val desc: String?,
    val subreddit: String
)
