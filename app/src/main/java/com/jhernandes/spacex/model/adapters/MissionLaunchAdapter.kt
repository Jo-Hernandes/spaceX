package com.jhernandes.spacex.model.adapters

import com.google.gson.internal.bind.util.ISO8601Utils
import com.jhernandes.spacex.model.Links
import com.jhernandes.spacex.model.MissionLaunchModel
import com.jhernandes.spacex.repository.model.LaunchResponseItem
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MissionLaunchAdapter {

    fun mapFromService(launch: LaunchResponseItem): MissionLaunchModel =
        MissionLaunchModel(
            missionName = launch.missionName,
            missionLaunchDate = ISO8601Utils.parse(launch.launchDateUtc, ParsePosition(0)),
            rocketName = launch.rocket.rocketName,
            rocketType = launch.rocket.rocketType,
            launchSuccess = launch.launchSuccess,
            missionPatchUrl = launch.links.missionPatchSmall,
            missionLinks = Links(
                wikipediaUrl = launch.links.wikipedia,
                youtubeUrl = launch.links.videoLink,
                articleUrl = launch.links.articleLink
            )
        )
}
