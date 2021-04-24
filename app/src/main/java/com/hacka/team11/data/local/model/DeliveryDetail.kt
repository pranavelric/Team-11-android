package com.hacka.team11.data.local.model

import java.io.Serializable

data class DeliveryDetail (

    var non_striker:String?,
    val bowler:String?,
    val runs:Runs?,
    val batsman:String?,
    val extras:Extras?,
    val wicket:Wicket?,

        ):Serializable