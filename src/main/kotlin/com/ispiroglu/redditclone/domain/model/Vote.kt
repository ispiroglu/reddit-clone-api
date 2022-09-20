package com.ispiroglu.redditclone.domain.model

import com.ispiroglu.redditclone.domain.enums.VoteType
import javax.persistence.*

@Entity
data class Vote(
    @Id @GeneratedValue val voteId: Long? = null,
    var voteType: VoteType,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    val post: Post,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    val redditor: Redditor,
)
