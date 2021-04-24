package com.hacka.team11.data.local.model

import java.io.Serializable

data class Innings(
    val deliveries: List<Deliveries>,
    val team: String
): Serializable