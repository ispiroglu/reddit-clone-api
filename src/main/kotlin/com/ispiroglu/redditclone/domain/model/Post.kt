package com.ispiroglu.redditclone.domain.model

import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Post(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val postId: Long? = null,

    @NotBlank val postTitle: String,

    val postUrl: String ? = null,
    @Lob val postDesc: String? = null,

    var voteCount: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    val owner: Redditor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subredditId", referencedColumnName = "subredditId")
    val subreddit: Subreddit,

    val creationDate: Instant,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//    @JoinColumn(name = "commentId", referencedColumnName = "commentId")
    val comments: MutableList<Comment> = mutableListOf()
)

