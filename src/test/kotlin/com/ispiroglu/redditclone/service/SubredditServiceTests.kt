package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateSubredditRequest
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Redditor
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.extensions.asDto
import com.ispiroglu.redditclone.repository.SubredditRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant

internal class SubredditServiceTests {
    private val authenticationService: AuthService = mockk(relaxed = true)
    private val subredditRepository: SubredditRepository = mockk(relaxed = true)
    private val redditorService: RedditorService = mockk(relaxed = true)
    private val subredditService = SubredditService(subredditRepository, authenticationService, redditorService)


    @Test
    fun `when trying to create subreddit with already existing name, it should throw IllegalArgumentException`() {
        // given
        val sampleSubredditName = "something"
        val sampleRequest = CreateSubredditRequest(
            name = sampleSubredditName,
            desc = "something",
        )
        every { subredditRepository.existsSubredditBySubredditName(sampleSubredditName) } returns true
        //when - then
        Assertions.assertThrows(IllegalArgumentException::class.java) {   subredditService.createSubreddit(sampleRequest) }
    }

    @Test
    fun `when trying to find a subreddit with non existing subreddit name, it should throw NoSuchElementException`() {
        // given
        val sampleSubredditName = "something"
        every { subredditRepository.findBySubredditName(sampleSubredditName) } returns null
        //when - then
        Assertions.assertThrows(NoSuchElementException::class.java) {   subredditService.getSubredditByName(sampleSubredditName) }
    }

    @Test
    fun `when trying to get subreddit with name, it should get the correct one`() {
        // given
        val sampleSubredditName = "something"
        val sampleSubreddit = Subreddit(
            subredditName = sampleSubredditName,
            subredditDesc = "sometin",
            createdDate = Instant.now()
        )
        every { subredditRepository.findBySubredditName(sampleSubredditName) } returns sampleSubreddit
        //when - then
        Assertions.assertEquals(subredditService.getSubredditByName(sampleSubredditName),
            subredditRepository.findBySubredditName(sampleSubredditName))

    }

    @Test
    fun `when trying to get all subreddits, it should return the list of all subreddits as Dto`() {
        // given
        val sampleSubredditName = "something"
        val sampleSubreddit = Subreddit(
            subredditName = sampleSubredditName,
            subredditDesc = "sometin",
            createdDate = Instant.now()
        )
        val sampleSubreddit2 = Subreddit(
            subredditName = sampleSubredditName,
            subredditDesc = "sometin2",
            createdDate = Instant.now()
        )
        val sampleSubreddit3 = Subreddit(
            subredditName = sampleSubredditName,
            subredditDesc = "sometin3",
            createdDate = Instant.now()
        )
        every { subredditRepository.findAll() } returns mutableListOf(sampleSubreddit, sampleSubreddit2, sampleSubreddit3)
        //when - then
        Assertions.assertEquals(subredditService.getAllSubreddits(),
            subredditRepository.findAll().asDto())
    }

    @Test
    fun `when trying to add post on subreddit, it should add it correctly`() {
        // given
        val sampleRedditor = Redditor(username = "username", password = "pass", email = "email")
        val sampleSubreddit = Subreddit(
            subredditName = "sampleSubredditName",
            subredditDesc = "sometin",
            createdDate = Instant.now()
        )
        val samplePost = Post(
            postTitle = "someTitle",
            subreddit = sampleSubreddit,
            owner = sampleRedditor
        )
        every { subredditRepository.save(sampleSubreddit) } returns sampleSubreddit
        //when
        subredditService.addPostToSubreddit(sampleSubreddit, samplePost)

        //then
        Assertions.assertEquals(1, sampleSubreddit.postList.size)
    }
}