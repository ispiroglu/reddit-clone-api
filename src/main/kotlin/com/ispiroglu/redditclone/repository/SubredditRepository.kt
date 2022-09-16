package com.ispiroglu.redditclone.repository

import com.ispiroglu.redditclone.domain.model.Subreddit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubredditRepository : JpaRepository<Subreddit, Long> {

    fun findBySubredditName(subredditName: String) : Subreddit?
}