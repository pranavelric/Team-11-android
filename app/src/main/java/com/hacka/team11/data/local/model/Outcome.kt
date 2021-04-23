package com.hacka.team11.data.local.model

import com.google.gson.annotations.SerializedName

data class Outcome(
    @SerializedName("by")
    val by_team: By,
    val winner: String
)