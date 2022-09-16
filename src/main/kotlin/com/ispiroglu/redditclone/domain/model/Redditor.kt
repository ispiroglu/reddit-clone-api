package com.ispiroglu.redditclone.domain.model

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class Redditor(
    @Id @GeneratedValue private val redditorId: Long? = null,
    @NotBlank private val username: String,
    @NotBlank private val password: String,
    @Email private val email: String,
    @CreatedDate private val createdDate: Instant? = null,
    private var enabled: Boolean = true, // need to add verification system

//    @OneToMany(fetch = FetchType.LAZY)
//    private var userComments: MutableCollection<Comment>
    )
