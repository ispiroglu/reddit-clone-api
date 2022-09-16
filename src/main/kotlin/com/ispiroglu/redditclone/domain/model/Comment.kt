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
    @Id @GeneratedValue private val commendId: Long,

    @NotEmpty private val text: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private val post: Post,

    @CreatedDate private val createdDate: Instant,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    private val redditor: Redditor
)
