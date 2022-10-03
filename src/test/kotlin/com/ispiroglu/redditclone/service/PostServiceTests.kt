package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreatePostRequest
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Redditor
import com.ispiroglu.redditclone.domain.model.Subreddit
import com.ispiroglu.redditclone.repository.PostRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.time.Instant

internal class PostServiceTests {
    private val postRepository: PostRepository = mockk(relaxed = true)
    private val redditorService: RedditorService = mockk(relaxed = true)
    private val subredditService: SubredditService = mockk(relaxed = true)
    private val postService: PostService = PostService(postRepository, redditorService, subredditService)

    @Test
    fun `when creating a new post, it should add it to the correct subreddit`() {
        // given
        val sampleRedditor = Redditor(username = "username", password = "pass", email = "email")
        val sampleSubredditName = "sampelSubreddit"
        val sampleRequest = CreatePostRequest(
            title = "sampleTitle",
            desc = "sampleDesc",
            subredditName = sampleSubredditName
        )

        val sampleSubreddit = Subreddit(
            subredditName = sampleSubredditName,
            subredditDesc = "sampelDesc",
            createdDate = Instant.now()
        )
        every { subredditService.getSubredditByName(sampleSubredditName) } returns sampleSubreddit
        every { AuthService.getRequestOwnerUsername() } returns "sampleRedditorUsername"
        every { redditorService.getRedditorByUsername(AuthService.getRequestOwnerUsername())} returns sampleRedditor
        every { postService.save(any()) } returns Post(postTitle = "sampleTitle", postDesc = "sampleDesc", subreddit = sampleSubreddit, owner = sampleRedditor)
        //when
        postService.createPost(sampleRequest)
        assertThat(subredditService.getSubredditByName(sampleSubredditName).postList.find { it.postTitle == "sampleTitle" })
        //then

    }
}