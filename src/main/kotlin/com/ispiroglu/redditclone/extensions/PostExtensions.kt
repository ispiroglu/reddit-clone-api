package com.ispiroglu.redditclone.extensions

import com.ispiroglu.redditclone.domain.dto.response.PostDto
import com.ispiroglu.redditclone.domain.model.Post

fun Post.asDto() =
    PostDto(postTitle,postDesc, subreddit.subredditName)

fun List<Post>.asDto() =
    map { it.asDto() }