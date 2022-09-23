package com.ispiroglu.redditclone.domain.mapper

import com.ispiroglu.redditclone.domain.dto.response.SubredditDto
import com.ispiroglu.redditclone.domain.model.Post
import com.ispiroglu.redditclone.domain.model.Subreddit
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel


@Mapper(componentModel = ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    fun mapSubredditToDto(subreddit: Subreddit): SubredditDto
    fun mapPosts(numberOfPosts: MutableList<Post>) = numberOfPosts.size
}