package com.hacka.team11.data.local.model

data class Info(
    val city: String,
    val competition: String,
    val dates: List<String>,
    val gender: String,
    val match_type: String,
    val outcome: Outcome,
    val overs: Int,
    val player_of_match: List<String>,
    val teams: List<String>,
    val toss: Toss,
    val umpires: List<String>,
    val venue: String
)