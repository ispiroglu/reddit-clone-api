package com.ispiroglu.redditclone.domain.model

import com.ispiroglu.redditclone.domain.enums.VoteType
import javax.persistence.*

@Entity
data class Vote(
    @Id @GeneratedValue private val voteId: Long,
    private var voteType: VoteType,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private val post: Post,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "redditorId", referencedColumnName = "redditorId")
    private val redditor: Redditor,
)
