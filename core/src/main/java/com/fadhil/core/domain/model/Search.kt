package com.fadhil.core.domain.model

import android.os.Parcelable
import com.fadhil.core.data.remote.response.ItemsItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
data class ItemsSearch(


    val gistsUrl: String? = null,

    val reposUrl: String? = null,

    val followingUrl: String? = null,

    val starredUrl: String? = null,

    val login: String? = null,

    val followersUrl: String? = null,

    val type: String? = null,

    val url: String? = null,

    val subscriptionsUrl: String? = null,

    val score: Int? = null,

    val receivedEventsUrl: String? = null,

    val avatarUrl: String? = null,

    val eventsUrl: String? = null,

    val htmlUrl: String? = null,

    val siteAdmin: Boolean? = null,

    val id: Int? = null,

    val gravatarId: String? = null,

    val nodeId: String? = null,

    val organizationsUrl: String? = null

) : Parcelable