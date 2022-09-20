package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreateVoteRequest
import com.ispiroglu.redditclone.service.VoteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/vote")
class VoteController(private val voteService: VoteService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    fun createVote(@RequestBody request: CreateVoteRequest) = voteService.creatVote(request)
}