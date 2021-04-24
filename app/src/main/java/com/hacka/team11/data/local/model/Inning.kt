package com.hacka.team11.data.local.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Inning(
    @SerializedName("1st innings")
    val firstInnings: Innings,
    @SerializedName("2nd innings")
    val secondInnings: Innings
): Serializable