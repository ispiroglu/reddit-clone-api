package com.ispiroglu.redditclone.extensions

import com.ispiroglu.redditclone.domain.dto.response.CommentDto
import com.ispiroglu.redditclone.domain.model.Comment

fun Comment.asDto() = CommentDto(commentId!!, redditor.username, post.subreddit.subredditName, post.postTitle, text, createdDate)

fun List<Comment>.asDto() = map { it.asDto() }