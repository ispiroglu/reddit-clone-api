package com.ispiroglu.redditclone.repository

import com.ispiroglu.redditclone.domain.model.Comment
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Redditor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {
    fun findCommentsByPost(post: Post) : List<Comment>
    fun findAllByRedditor(redditor: Redditor) : MutableList<Comment>
}