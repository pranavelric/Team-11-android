package com.hacka.team11.data.local.model

import java.io.Serializable

data class MatchModel(
    val info: Info,
    val innings: List<Inning>,
    val meta: Meta
):Serializable