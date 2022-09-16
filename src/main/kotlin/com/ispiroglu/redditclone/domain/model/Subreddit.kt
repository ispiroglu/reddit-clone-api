package com.ispiroglu.redditclone.domain.model

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank

@Entity
data class Subreddit(

    @Id @GeneratedValue private val subredditId: Long,
    @NotBlank private val subredditName: String,
    @NotBlank private val subredditDesc: String,

    @OneToMany(fetch = FetchType.LAZY)
    private val postList: MutableList<Post> = mutableListOf(),
    @CreatedDate private val createdDate: Instant,
    @ManyToOne(fetch = FetchType.LAZY)
    private val redditor: Redditor
)
