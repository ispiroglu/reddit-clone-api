package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateVoteRequest
import com.ispiroglu.redditclone.domain.model.Vote
import com.ispiroglu.redditclone.repository.VoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VoteService(
    private val voteRepository: VoteRepository,
    private val redditorService: RedditorService,
    private val postService: PostService
) {

    @Transactional
    fun save(vote: Vote) = voteRepository.save(vote)

    @Transactional
    fun creatVote(request: CreateVoteRequest) {
        val vote = Vote(
            voteType = request.type,
            redditor = redditorService.getRedditorByUsername(AuthService.getRequestOwnerUsername()),
            post = postService.getPost(request.subredditName, request.postTitle),
        )

        save(vote)
    }
}