package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.CreateCommentRequest
import com.ispiroglu.redditclone.domain.dto.response.CommentDto
import com.ispiroglu.redditclone.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/comment")
class CommentController(private val commentService: CommentService) {


    @PostMapping()
    fun createComment(@RequestBody request: CreateCommentRequest): ResponseEntity<CommentDto> {
        val comment  = commentService.createComment(request)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{subredditName}/{postTitle}/{commentId}")
            .buildAndExpand(comment.subreddit, comment.post, comment.id).toUri()
        return ResponseEntity.created(uri).body(comment)

    }

    @GetMapping("/{subredditName}/{postTitle}")
    fun getAllCommentsOfPost(@PathVariable subredditName: String, @PathVariable postTitle: String) =
        commentService.getAllCommentsOfPostAsDto(subredditName, postTitle)

}

