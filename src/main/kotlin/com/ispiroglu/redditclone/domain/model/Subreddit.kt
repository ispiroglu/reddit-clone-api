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

    @Id @GeneratedValue val subredditId: Long? = null,
    @NotBlank val subredditName: String,
    @NotBlank val subredditDesc: String,

    @OneToMany(fetch = FetchType.LAZY)
    val postList: MutableList<Post> = mutableListOf(),
    @CreatedDate  val createdDate: Instant,
    @ManyToOne(fetch = FetchType.LAZY)
    val redditor: Redditor? = null
)
