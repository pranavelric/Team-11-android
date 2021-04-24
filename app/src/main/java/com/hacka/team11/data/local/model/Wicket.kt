package com.hacka.team11.data.local.model

import java.io.Serializable

data class Wicket(
    val player_out: String?,
    val fielders: List<String>?,
    val kind: String?
):Serializable
