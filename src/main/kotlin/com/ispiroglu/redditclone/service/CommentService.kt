package com.ispiroglu.redditclone.service

import com.ispiroglu.redditclone.domain.dto.request.CreateCommentRequest
import com.ispiroglu.redditclone.domain.model.Comment
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

    fun save(comment: Comment) = commentRepository.save(comment)

    fun createComment(request: CreateCommentRequest) {
        val comment = Comment(
            text = request.text,
            post = postService.getPost(request.subredditName, request.postTitle),
            redditor = redditorService.getRedditorByUsername( AuthService.getRequestOwnerUsername() ),
            createdDate = Instant.now()
        )

        /*
        * Notification System!
        * */

        save(comment)
    }

    @Transactional(readOnly = true)
    fun getAllCommentsOfPost(subredditName: String, postTitle: String): MutableList<Comment> {
        val post = postService.getPost(subredditName, postTitle)
        return commentRepository.findCommentsByPost(post)
    }
}