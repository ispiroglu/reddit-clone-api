package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreatePostRequest
import com.ispiroglu.redditclone.domain.dto.response.PostDto
import com.ispiroglu.redditclone.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/post")
class PostController(private val postService: PostService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createPost(@RequestBody request: CreatePostRequest): ResponseEntity<PostDto> {
        val post = postService.createPost(request)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{subredditName}/{postTitle}")
            .buildAndExpand(post.subreddit, post.title).toUri()
        return ResponseEntity.created(uri).body(post)
    }

    @GetMapping
    fun getAllPosts() = ResponseEntity.ok(postService.getAllPosts())

    @GetMapping("/{subreddit}/{title}")
    fun getPost(@PathVariable subreddit: String, @PathVariable title: String) =
        ResponseEntity.ok(postService.getPostAsDto(subreddit, title))

    @GetMapping("/{username}")
    fun getPostsByUsername(@PathVariable username: String) =
        ResponseEntity.ok(postService.getPostsByUsernameAsDto(username))
}