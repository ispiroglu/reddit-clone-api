package com.ispiroglu.redditclone.extensions

import com.ispiroglu.redditclone.domain.dto.response.PostDto
import com.ispiroglu.redditclone.domain.model.Post
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Date

fun Post.asDto() =
    PostDto(postTitle, postDesc, subreddit.subredditName,
            owner.username, comments.size, 4, Date.from(creationDate))

fun List<Post>.asDto() =
    map { it.asDto() }

fun Post.url() = ServletUriComponentsBuilder.fromCurrentRequest().path("/{subredditName}").buildAndExpand(subreddit.subredditName).toUri()