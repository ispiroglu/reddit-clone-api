package com.ispiroglu.redditclone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class RedditCloneApplication

fun main(args: Array<String>) {
    runApplication<RedditCloneApplication>(*args)


    @Bean
    fun Faker() {

    }
}
