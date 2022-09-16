package com.ispiroglu.redditclone.domain.enums

import java.util.*

enum class VoteType(direction: Int) {
    UPVOTE(1), DOWNVOTE(-1);

    val direction = 0

    companion object {
        fun lookup(direction: Int): VoteType {
            return Arrays.stream(values())
                .filter { value: VoteType -> value.direction == direction }
                .findAny()
                .orElseThrow { RuntimeException("Vote not found") }
        }
    }
}