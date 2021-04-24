package com.hacka.team11.data.local.model

import java.io.Serializable

data class Meta(
    val created: String,
    val data_version: Double,
    val revision: Int
):Serializable