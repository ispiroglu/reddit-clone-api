package com.ispiroglu.redditclone.domain.dto.response

import java.time.Instant
import java.util.Date

data class PostDto(
    val title: String,
    val desc: String?,
    val subredditName: String,
    val ownerUsername: String,
    val commentCount: Int,
    val voteAverage: Int,
    val creationDate: Date
)
