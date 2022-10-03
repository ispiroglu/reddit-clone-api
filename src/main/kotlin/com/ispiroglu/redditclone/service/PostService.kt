package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreatePostRequest
import com.ispiroglu.redditclone.domain.dto.response.PostDto
import com.ispiroglu.redditclone.domain.model.Comment
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.extensions.asDto
import com.ispiroglu.redditclone.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class PostService(
    private val postRepository: PostRepository,
    private val redditorService: RedditorService,
    private val subredditService: SubredditService
) {

    @Transactional
    fun save(post: Post) = postRepository.save(post)

    @Transactional
    fun createPost(request: CreatePostRequest): PostDto {
        val subreddit = subredditService.getSubredditByName(request.subredditName)
        val post = Post(
            postTitle = request.title,
            postDesc = request.desc,
            owner = redditorService.getRedditorByUsername(AuthService.getRequestOwnerUsername()),
            subreddit = subreddit,
            creationDate = Instant.now()
        )
        val savedPost = save(post)
        subredditService.addPostToSubreddit(subreddit, post)
        return savedPost.asDto()
    }

    @Transactional(readOnly = true)
    fun getAllPosts(): List<PostDto> = postRepository.findAll().asDto()

    @Transactional(readOnly = true)
    fun getPost(subredditName: String, postTitle: String): Post =
        postRepository.findPostBySubredditAndPostTitle(subredditService.getSubredditByName(subredditName), postTitle) ?:
        throw NoSuchElementException("Could not find the post with given subreddit name. $subredditName, postTitle: $postTitle")

    @Transactional(readOnly = true)
    fun getPostAsDto(subredditName: String, postTitle: String) = getPost(subredditName, postTitle).asDto()

    @Transactional(readOnly = true)
    fun getPostsByUsername(username: String) = postRepository.findPostByOwner(redditorService.getRedditorByUsername(username)) ?:
    throw NoSuchElementException("Could not find the posts with given username: $username")

    @Transactional(readOnly = true)
    fun getPostsByUsernameAsDto(username: String) = getPostsByUsername(username).asDto()

    @Transactional
    fun addCommentToPost(post: Post, comment: Comment) {
        post.comments.add(comment)
        postRepository.save(post)
    }
}