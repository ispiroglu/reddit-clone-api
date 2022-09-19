package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.RegisterRedditorRequest
import com.ispiroglu.redditclone.domain.model.Redditor
import com.ispiroglu.redditclone.repository.RedditorRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class RedditorService(
    private val redditorRepository: RedditorRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun save(redditor: Redditor) = redditorRepository.save(redditor)

    fun createRedditor(registerRedditorDto: RegisterRedditorRequest) {
        val redditor = Redditor(
            username = registerRedditorDto.username,
            email = registerRedditorDto.email,
            password = passwordEncoder.encode(registerRedditorDto.password),
            createdDate = Instant.now())
        save(redditor)
    }

    fun getRedditorByUsername(username: String) = redditorRepository.findByUsername(username) ?: throw NoSuchElementException("There is no user with that username !!")
}