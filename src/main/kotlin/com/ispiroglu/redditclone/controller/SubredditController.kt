package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.service.SubredditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/v1/subreddit")
class SubredditController(
    private val subredditService: SubredditService
) {

    @PostMapping
    fun createSubreddit(@RequestBody request: CreateSubredditRequest): ResponseEntity<URI> {
        val subreddit = subredditService.createSubreddit(request)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{subredditName}").buildAndExpand(subreddit.subredditName).toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun getAllSubreddits() = ResponseEntity.ok( subredditService.getAllSubreddits() )

    @GetMapping("/{subredditName}")
    fun getSubredditByName(@PathVariable subredditName: String) = ResponseEntity.ok( subredditService.getSubredditDtoByName(subredditName) )

}