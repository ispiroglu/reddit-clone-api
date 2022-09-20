package com.ispiroglu.redditclone.repository

import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.domain.model.Redditor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {
    fun findAllBySubreddit(subreddit: Subreddit) : MutableList<Post>

    fun findAllByOwner(redditor: Redditor): MutableList<Post>

    fun findPostBySubredditAndPostTitle(subreddit: Subreddit, postTitle: String): Post?

    fun findPostByOwner(owner: Redditor): Post?
}