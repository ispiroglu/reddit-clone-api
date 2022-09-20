package com.ispiroglu.redditclone.domain.dto.request

import com.ispiroglu.redditclone.domain.enums.VoteType

data class CreateVoteRequest(
    val type: VoteType,
    val postTitle: String,
    val subredditName: String
)