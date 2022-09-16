package com.ispiroglu.redditclone.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/comment")
class CommentController {

    @GetMapping
    fun getComments() = "Hello from comments"
}