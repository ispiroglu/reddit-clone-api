package com.ispiroglu.redditclone.domain.dto.response

import java.time.Instant

data class CommentDto(
    val id: Long,
    val redditor: String,
    val subreddit: String,
    val post: String,
    val desc: String,
    val creationDate: Instant
)
