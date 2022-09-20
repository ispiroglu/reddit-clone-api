package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.service.SubredditService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/subreddit")
class SubredditController(
    private val subredditService: SubredditService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createSubreddit(@RequestBody request: CreateSubredditRequest) = subredditService.createSubreddit(request)

    @GetMapping
    fun getAllSubreddits(): MutableList<Subreddit> = subredditService.getAllSubreddits()

    @GetMapping("/{subredditName}")
    fun getSubredditByName(@PathVariable subredditName: String) = subredditService.getSubredditByName(subredditName)

}