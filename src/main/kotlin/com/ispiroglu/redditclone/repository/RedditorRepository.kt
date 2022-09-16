package com.ispiroglu.redditclone.repository

import com.ispiroglu.redditclone.domain.model.Redditor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RedditorRepository : JpaRepository<Redditor, Long> {
    fun findByUsername(username: String) : Redditor?
}