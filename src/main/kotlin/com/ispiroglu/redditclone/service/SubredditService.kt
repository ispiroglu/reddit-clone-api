package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.domain.dto.response.SubredditDto
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.extensions.asDto
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
    private val redditorService: RedditorService,
//    private val subredditMapper: SubredditMapper
) {

    @Transactional
    fun save(subreddit: Subreddit) = subredditRepository.save(subreddit)

    @Transactional
    fun createSubreddit(request: CreateSubredditRequest): Subreddit {

        if (subredditRepository.existsSubredditBySubredditName(request.name))
                throw IllegalArgumentException("Cannot create subreddit. There is an already existing subreddit with given name: ${request.name}")

        val currentUsername = AuthService.getRequestOwnerUsername()

        val subreddit = Subreddit(
            subredditName = request.name,
            subredditDesc = request.desc,
            createdDate = Instant.now(),
            redditor = redditorService.getRedditorByUsername(currentUsername))
        authService.createGroupWithName(request.name)
        return save(subreddit)
    }

    @Transactional(readOnly = true)
    fun getAllSubreddits(): List<SubredditDto> = subredditRepository.findAll().asDto()

    @Transactional(readOnly = true)
    fun getSubredditByName(subredditName: String): Subreddit
        = subredditRepository.findBySubredditName(subredditName)
            ?: throw NoSuchElementException("Could not find subreddit with given name: $subredditName")

    @Transactional(readOnly = true)
    fun getSubredditDtoByName(subredditName: String): SubredditDto  =
        getSubredditByName(subredditName).asDto()

    @Transactional
    fun addPostToSubreddit(subreddit: Subreddit, post: Post) {
        subreddit.postList.add(post)
        save(subreddit)
    }
}