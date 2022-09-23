package com.ispiroglu.redditclone.extensions

import com.ispiroglu.redditclone.domain.dto.response.PostDto
import com.ispiroglu.redditclone.domain.dto.response.SubredditDto
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Subreddit

fun Subreddit.asDto() =
    SubredditDto(subredditName,subredditDesc, postList.size)

fun List<Subreddit>.asDto() =
    map { it.asDto() }