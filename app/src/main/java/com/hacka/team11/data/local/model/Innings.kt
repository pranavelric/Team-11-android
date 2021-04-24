package com.hacka.team11.data.local.model

import java.io.Serializable

data class Innings(
    val deliveries: List<Any>,
    val team: String
): Serializable