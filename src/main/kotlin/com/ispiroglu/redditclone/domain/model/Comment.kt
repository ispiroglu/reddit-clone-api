package com.ispiroglu.redditclone.domain.model

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotEmpty

/*
* May need to make fields nullable
* */

@Entity
data class Comment(
    @Id @GeneratedValue val commendId: Long? = null ,

    @NotEmpty val text: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    val post: Post,

    @CreatedDate val createdDate: Instant,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    val redditor: Redditor
)
