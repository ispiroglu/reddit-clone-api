package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.repository.SubredditRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import kotlin.IllegalArgumentException

/**
 *
 * Should learn to use mappers LOL
 */



@Service
class SubredditService(
    private val subredditRepository: SubredditRepository,
    private val authService: AuthService,
    private val redditorService: RedditorService
) {

    @Transactional
    fun save(subreddit: Subreddit) = subredditRepository.save(subreddit)

    @Transactional
    fun createSubreddit(request: CreateSubredditRequest) {
            if (subredditRepository.existsSubredditBySubredditName(request.name))
                throw IllegalArgumentException("Cannot create subreddit. There is an already existing subreddit with given name: ${request.name}")

            val currentUsername = AuthService.getRequestOwnerUsername()

            println(request.name)
            val subreddit = Subreddit(
                subredditName = request.name,
                subredditDesc = request.desc,
                createdDate = Instant.now(),
                redditor = redditorService.getRedditorByUsername(currentUsername)
            )
            authService.createGroupWithName(request.name)
            save(subreddit)
    }


    fun getAllSubreddits(): MutableList<Subreddit> = subredditRepository.findAll()

    @Transactional(readOnly = true)
    fun getSubredditByName(subredditName: String) = subredditRepository.findBySubredditName(subredditName)
        ?: throw IllegalArgumentException("Could not find subreddit with given name: $subredditName")

}