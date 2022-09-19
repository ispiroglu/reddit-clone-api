package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.repository.SubredditRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional
class SubredditService(
    private val subredditRepository: SubredditRepository,
    private val authService: AuthService,
    private val redditorService: RedditorService
) {

    fun save(subreddit: Subreddit) = subredditRepository.save(subreddit)

    fun createSubreddit(request: CreateSubredditRequest) {
        val currentUsername = AuthService.getRequestOwnerUsername()
        println("CURRENT USERNAE -> $currentUsername")
        val subreddit = Subreddit(
            subredditName = request.name,
            subredditDesc = request.desc,
            createdDate = Instant.now(),
            redditor = redditorService.getRedditorByUsername(currentUsername)
        )
        authService.createGroupWithName(request.name)
        save(subreddit)
    }


}