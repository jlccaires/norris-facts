package com.jlccaires.norrisfacts.data

import com.google.gson.annotations.SerializedName

data class JokeDto(
    var category: List<String> = emptyList(),
    @SerializedName("icon_url") var iconUrl: String?,
    var id: String?,
    var url: String?,
    var value: String?
)