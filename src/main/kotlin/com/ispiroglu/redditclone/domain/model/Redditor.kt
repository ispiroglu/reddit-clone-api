package com.ispiroglu.redditclone.domain.model

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class Redditor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val redditorId: Long? = null,
    @NotBlank val username: String,
    @NotBlank val password: String,
    @Email val email: String,
    @CreatedDate val createdDate: Instant? = null,
    var enabled: Boolean = true, // need to add verification system

//    @OneToMany(fetch = FetchType.LAZY)
//    private var userComments: MutableCollection<Comment>
    )
