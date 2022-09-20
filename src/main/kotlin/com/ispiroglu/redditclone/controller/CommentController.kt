package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreateCommentRequest
import com.ispiroglu.redditclone.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/comment")
class CommentController(private val commentService: CommentService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    fun createComment(@RequestBody request: CreateCommentRequest) = commentService.createComment(request)

    @GetMapping("/{subredditName}/{postTitle}")
    fun getAllCommentsOfPost(@PathVariable subredditName: String, @PathVariable postTitle: String) = commentService.getAllCommentsOfPost(subredditName, postTitle)

}