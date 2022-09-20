package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreatePostRequest
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val redditorService: RedditorService,
    private val subredditService: SubredditService
) {

    fun save(post: Post) = postRepository.save(post)

    fun createPost(request: CreatePostRequest) {
        val post = Post(
            postTitle = request.title,
            postDesc = request.desc,
            owner = redditorService.getRedditorByUsername(AuthService.getRequestOwnerUsername()),
            subreddit = subredditService.getSubredditByName(request.subredditName)
        )

        save(post)

        /*
        * Should return some DTO or URL LOL
        * */
    }

    @Transactional(readOnly = true)
    fun getAllPosts(): MutableList<Post> = postRepository.findAll()

    @Transactional(readOnly = true)
    fun getPost(subredditName: String, postTitle: String): Post =
        postRepository.findPostBySubredditAndPostTitle(subredditService.getSubredditByName(subredditName), postTitle) ?:
        throw NoSuchElementException("Could not find the post with given subreddit name. $subredditName, postTitle: $postTitle")

    @Transactional(readOnly = true)
    fun getPostsByUsername(username: String) = postRepository.findPostByOwner(redditorService.getRedditorByUsername(username)) ?:
    throw NoSuchElementException("Could not find the posts with given username: $username")
}