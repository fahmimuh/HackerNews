package com.fahmimuh.hackernews.data.network.models


import com.google.gson.annotations.SerializedName

data class CommentResponse(
        @SerializedName("by")
        val `by`: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("parent")
        val parent: Int,

        @SerializedName("text")
        val text: String,

        @SerializedName("time")
        val time: Int,

        @SerializedName("type")
        val type: String
)