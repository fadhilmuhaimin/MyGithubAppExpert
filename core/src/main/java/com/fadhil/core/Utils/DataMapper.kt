package com.fadhil.core.Utils

import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.domain.model.Detail
import com.fadhil.core.domain.model.ItemsSearch

object DataMapper {

//    fun mapResponsesToDomain(tourismResponses: List<SearchResponse>): List<SearchResponse> {
//        return tourismResponses.map { mapResponseToDomain(it) }
//    }

    fun mapResponseDetailToDOmain(response: DetailResponse) = Detail(
        gistsUrl = response.gistsUrl,
        reposUrl = response.reposUrl,
        followingUrl = response.followingUrl,
        starredUrl = response.starredUrl,
        login = response.login,
        followersUrl = response.followersUrl,
        type = response.type,
        url = response.url,
        subscriptionsUrl = response.subscriptionsUrl,
        receivedEventsUrl = response.receivedEventsUrl,
        avatarUrl = response.avatarUrl,
        eventsUrl = response.eventsUrl,
        htmlUrl = response.htmlUrl,
        siteAdmin = response.siteAdmin,
        id = response.id,
        nodeId = response.nodeId,
        organizationsUrl = response.organizationsUrl,
        twitterUsername = response.twitterUsername,
        createdAt = response.createdAt,
        bio = response.bio,
        blog = response.blog,
        updatedAt = response.updatedAt,
        company = response.company,
        gravatarId = response.gravatarId,
        email = response.email,
        name = response.name,
        followers = response.followers,
        following = response.following


    )

    fun mapDomainToEntity(input: ItemsSearch) = input.login?.let {
        FavoriteUser(
            username = it,
            avatarUrl = input.avatarUrl

        )
    }

    fun mapEntityToDomain(input: List<FavoriteUser>): List<ItemsSearch> =
        input.map {
            ItemsSearch(
                login = it.username,
                avatarUrl = it.avatarUrl
            )
        }

    fun mapEntityToDomainFavorite(input: FavoriteUser) = ItemsSearch(
        login = input.username,
        avatarUrl = input.avatarUrl
    )

    fun mapResponsesToDomain(userResponses: List<ItemsItem>): List<ItemsSearch> {
        return userResponses.map { mapResponseToDomain(it) }
    }

    private fun mapResponseToDomain(response: ItemsItem) = ItemsSearch(
        gistsUrl = response.gistsUrl,
        reposUrl = response.reposUrl,
        followingUrl = response.followingUrl,
        starredUrl = response.starredUrl,
        login = response.login,
        followersUrl = response.followersUrl,
        type = response.type,
        url = response.url,
        subscriptionsUrl = response.subscriptionsUrl,
        score = response.score,
        receivedEventsUrl = response.receivedEventsUrl,
        avatarUrl = response.avatarUrl,
        eventsUrl = response.eventsUrl,
        htmlUrl = response.htmlUrl,
        siteAdmin = response.siteAdmin,
        id = response.id,
        nodeId = response.nodeId,
        organizationsUrl = response.organizationsUrl,

        )
}