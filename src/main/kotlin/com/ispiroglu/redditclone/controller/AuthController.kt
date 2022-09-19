package com.ispiroglu.redditclone.controller

import com.ispiroglu.redditclone.domain.dto.request.LoginRedditorRequest
import com.ispiroglu.redditclone.domain.dto.request.RegisterRedditorRequest
import com.ispiroglu.redditclone.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    fun signup(@RequestBody request: RegisterRedditorRequest) = ResponseEntity.ok(authService.signup(request))

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRedditorRequest) = authService.login(request.username, request.password)

    @GetMapping("/cu")
    fun currentUser() = AuthService.getRequestOwnerUsername()

}