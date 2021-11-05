package com.jhernandes.spacex.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

data class MissionLaunchModel(
    val missionName: String?,
    val rocketName: String?,
    val rocketType: String?,
    val missionLaunchDate: Date,
    val launchSuccess: Boolean,
    val missionPatchUrl: String?,
    val missionLinks: Links
) {

    val year : Int
    get() {
        val cal = Calendar.getInstance()
        cal.time = missionLaunchDate
        return cal.get(Calendar.YEAR)
    }

    val isPastLaunch: Boolean
        get() = missionLaunchDate.before(Date())

    val daysDifference: Int
        get() {
            val difference: Long = (Date().time - missionLaunchDate.time).absoluteValue
            val dayInMillis = 86400000
            return (difference / dayInMillis).toInt()
        }

    val launchDateFormatted: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            return dateFormat.format(missionLaunchDate)
        }

    val launchTimeFormatted: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.US)
            return dateFormat.format(missionLaunchDate)
        }
}

data class Links(
    val wikipediaUrl: String?,
    val youtubeUrl: String?,
    val articleUrl: String?,
)


