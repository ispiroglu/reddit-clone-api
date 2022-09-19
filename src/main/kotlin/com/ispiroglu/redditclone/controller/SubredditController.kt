package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.service.SubredditService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/subreddit")
class SubredditController(
    private val subredditService: SubredditService
) {

    @PostMapping
    fun createSubreddit(@RequestBody request: CreateSubredditRequest) = subredditService.createSubreddit(request)
}