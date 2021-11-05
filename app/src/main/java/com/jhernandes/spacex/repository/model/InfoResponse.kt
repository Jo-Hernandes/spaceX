package com.jhernandes.spacex.repository.model
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("ceo")
    val ceo: String,
    @SerializedName("coo")
    val coo: String,
    @SerializedName("cto")
    val cto: String,
    @SerializedName("cto_propulsion")
    val ctoPropulsion: String,
    @SerializedName("employees")
    val employees: Int,
    @SerializedName("founded")
    val founded: Int,
    @SerializedName("founder")
    val founder: String,
    @SerializedName("headquarters")
    val headquarters: Headquarters,
    @SerializedName("launch_sites")
    val launchSites: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("test_sites")
    val testSites: Int,
    @SerializedName("valuation")
    val valuation: Long,
    @SerializedName("vehicles")
    val vehicles: Int
)

data class Headquarters(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String
)
