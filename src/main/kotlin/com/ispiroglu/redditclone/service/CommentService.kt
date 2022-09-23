package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateCommentRequest
import com.ispiroglu.redditclone.domain.dto.response.CommentDto
import com.ispiroglu.redditclone.domain.model.Comment
import com.ispiroglu.redditclone.extensions.asDto
import com.ispiroglu.redditclone.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val redditorService: RedditorService,
    private val postService: PostService
) {

    @Transactional
    fun save(comment: Comment) = commentRepository.save(comment)

    @Transactional
    fun createComment(request: CreateCommentRequest): CommentDto {
        val post = postService.getPost(request.subredditName, request.postTitle)
        val redditor =  redditorService.getRedditorByUsername( AuthService.getRequestOwnerUsername() )
        val comment = Comment(
            text = request.text,
            post = post,
            redditor = redditor,
            createdDate = Instant.now()
        )
        /*
        * Notification System!
        */
        val dto = save(comment).asDto()
        postService.addCommentToPost(post, comment)
        return dto
    }

    @Transactional(readOnly = true)
    fun getAllCommentsOfPost(subredditName: String, postTitle: String): List<Comment> {
        val post = postService.getPost(subredditName, postTitle)
        return commentRepository.findCommentsByPost(post)
    }

    @Transactional(readOnly = true)
    fun getAllCommentsOfPostAsDto(subredditName: String, postTitle: String): List<CommentDto>
        = getAllCommentsOfPost(subredditName, postTitle).asDto()
}