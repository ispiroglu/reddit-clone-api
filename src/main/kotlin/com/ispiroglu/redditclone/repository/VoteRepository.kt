package com.ispiroglu.redditclone.repository

import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Redditor
import com.ispiroglu.redditclone.domain.model.Vote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VoteRepository: JpaRepository<Vote, Long> {
    fun findTopByPostAndRedditorOrderByVoteIdDesc(post: Post, redditor: Redditor) : Vote?
}