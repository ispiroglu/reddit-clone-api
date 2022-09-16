package com.ispiroglu.redditclone.domain.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Post(

    @Id @GeneratedValue private val postId: Long,

    @NotBlank private val postName: String,

    private val postUrl: String?,
    @Lob private val postDesc: String?,

    private var voteCount: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    private val owner: Redditor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subredditId", referencedColumnName = "subredditId")
    private val subreddit: Subreddit
)

