package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreatePostRequest
import com.ispiroglu.redditclone.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/post")
class PostController(private val postService: PostService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createPost(@RequestBody request: CreatePostRequest) = postService.createPost(request)


    @GetMapping
    fun getAllPosts() = postService.getAllPosts()

    @GetMapping("/{subreddit}/{title}")
    fun getPost(@PathVariable subreddit: String, @PathVariable title: String) = postService.getPost(subreddit, title)

    @GetMapping("/{username}")
    fun getPostsByUsername(@PathVariable username: String) = postService.getPostsByUsername(username)
}