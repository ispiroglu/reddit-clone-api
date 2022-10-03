package com.ispiroglu.redditclone.domain.model

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Subreddit(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val subredditId: Long? = null,
    @NotBlank val subredditName: String,
    @NotBlank val subredditDesc: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subreddit")
//    @JoinColumn(name = "subredditId",referencedColumnName = "subredditId")

    val postList: MutableList<Post> = mutableListOf(),



    @CreatedDate  val createdDate: Instant,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    val redditor: Redditor? = null
)
