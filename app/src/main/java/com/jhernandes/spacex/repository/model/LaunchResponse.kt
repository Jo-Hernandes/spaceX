package com.jhernandes.spacex.repository.model
import com.google.gson.annotations.SerializedName

class LaunchResponse : ArrayList<LaunchResponseItem>()

data class LaunchResponseItem(
    @SerializedName("details")
    val details: String,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("is_tentative")
    val isTentative: Boolean,
    @SerializedName("launch_date_local")
    val launchDateLocal: String,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int,
    @SerializedName("launch_date_utc")
    val launchDateUtc: String,
    @SerializedName("launch_failure_details")
    val launchFailureDetails: LaunchFailureDetails,
    @SerializedName("launch_site")
    val launchSite: LaunchSite,
    @SerializedName("launch_success")
    val launchSuccess: Boolean,
    @SerializedName("launch_window")
    val launchWindow: Int,
    @SerializedName("launch_year")
    val launchYear: String,
    @SerializedName("links")
    val links: Links,
    @SerializedName("mission_id")
    val missionId: List<Any>,
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("rocket")
    val rocket: Rocket,
    @SerializedName("ships")
    val ships: List<Any>,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String,
    @SerializedName("tbd")
    val tbd: Boolean,
    @SerializedName("telemetry")
    val telemetry: Telemetry,
    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String,
    @SerializedName("timeline")
    val timeline: Timeline,
    @SerializedName("upcoming")
    val upcoming: Boolean
)

data class LaunchFailureDetails(
    @SerializedName("altitude")
    val altitude: Int,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("time")
    val time: Int
)

data class LaunchSite(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name")
    val siteName: String,
    @SerializedName("site_name_long")
    val siteNameLong: String
)

data class Links(
    @SerializedName("article_link")
    val articleLink: String,
    @SerializedName("flickr_images")
    val flickrImages: List<Any>,
    @SerializedName("mission_patch")
    val missionPatch: String,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String,
    @SerializedName("presskit")
    val presskit: Any,
    @SerializedName("reddit_campaign")
    val redditCampaign: Any,
    @SerializedName("reddit_launch")
    val redditLaunch: Any,
    @SerializedName("reddit_media")
    val redditMedia: Any,
    @SerializedName("reddit_recovery")
    val redditRecovery: Any,
    @SerializedName("video_link")
    val videoLink: String,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("youtube_id")
    val youtubeId: Any
)

data class Rocket(
    @SerializedName("fairings")
    val fairings: Fairings,
    @SerializedName("first_stage")
    val firstStage: FirstStage,
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String,
    @SerializedName("second_stage")
    val secondStage: SecondStage
)

data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: Any
)

data class Timeline(
    @SerializedName("engine_chill")
    val engineChill: Int,
    @SerializedName("fairing_deploy")
    val fairingDeploy: Int,
    @SerializedName("first_stage_boostback_burn")
    val firstStageBoostbackBurn: Int,
    @SerializedName("first_stage_entry_burn")
    val firstStageEntryBurn: Int,
    @SerializedName("first_stage_landing")
    val firstStageLanding: Int,
    @SerializedName("go_for_launch")
    val goForLaunch: Int,
    @SerializedName("go_for_prop_loading")
    val goForPropLoading: Int,
    @SerializedName("ignition")
    val ignition: Int,
    @SerializedName("liftoff")
    val liftoff: Int,
    @SerializedName("maxq")
    val maxq: Int,
    @SerializedName("meco")
    val meco: Int,
    @SerializedName("payload_deploy")
    val payloadDeploy: Int,
    @SerializedName("prelaunch_checks")
    val prelaunchChecks: Int,
    @SerializedName("propellant_pressurization")
    val propellantPressurization: Int,
    @SerializedName("rp1_loading")
    val rp1Loading: Int,
    @SerializedName("seco-1")
    val seco1: Int,
    @SerializedName("seco-2")
    val seco2: Int,
    @SerializedName("second_stage_ignition")
    val secondStageIgnition: Int,
    @SerializedName("second_stage_restart")
    val secondStageRestart: Int,
    @SerializedName("stage1_lox_loading")
    val stage1LoxLoading: Int,
    @SerializedName("stage2_lox_loading")
    val stage2LoxLoading: Int,
    @SerializedName("stage_sep")
    val stageSep: Int,
    @SerializedName("webcast_liftoff")
    val webcastLiftoff: Int
)

data class Fairings(
    @SerializedName("recovered")
    val recovered: Boolean,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Any,
    @SerializedName("reused")
    val reused: Boolean,
    @SerializedName("ship")
    val ship: Any
)

data class FirstStage(
    @SerializedName("cores")
    val cores: List<Core>
)

data class SecondStage(
    @SerializedName("block")
    val block: Any,
    @SerializedName("payloads")
    val payloads: List<Payload>
)

data class Core(
    @SerializedName("block")
    val block: Any,
    @SerializedName("core_serial")
    val coreSerial: Any,
    @SerializedName("flight")
    val flight: Any,
    @SerializedName("gridfins")
    val gridfins: Any,
    @SerializedName("land_success")
    val landSuccess: Any,
    @SerializedName("landing_intent")
    val landingIntent: Any,
    @SerializedName("landing_type")
    val landingType: Any,
    @SerializedName("landing_vehicle")
    val landingVehicle: Any,
    @SerializedName("legs")
    val legs: Any,
    @SerializedName("reused")
    val reused: Any
)

data class Payload(
    @SerializedName("customers")
    val customers: List<String>,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("norad_id")
    val noradId: List<Any>,
    @SerializedName("orbit")
    val orbit: String,
    @SerializedName("orbit_params")
    val orbitParams: OrbitParams,
    @SerializedName("payload_id")
    val payloadId: String,
    @SerializedName("payload_mass_kg")
    val payloadMassKg: Any,
    @SerializedName("payload_mass_lbs")
    val payloadMassLbs: Any,
    @SerializedName("payload_type")
    val payloadType: String,
    @SerializedName("reused")
    val reused: Boolean
)

data class OrbitParams(
    @SerializedName("apoapsis_km")
    val apoapsisKm: Any,
    @SerializedName("arg_of_pericenter")
    val argOfPericenter: Any,
    @SerializedName("eccentricity")
    val eccentricity: Any,
    @SerializedName("epoch")
    val epoch: Any,
    @SerializedName("inclination_deg")
    val inclinationDeg: Any,
    @SerializedName("lifespan_years")
    val lifespanYears: Any,
    @SerializedName("longitude")
    val longitude: Any,
    @SerializedName("mean_anomaly")
    val meanAnomaly: Any,
    @SerializedName("mean_motion")
    val meanMotion: Any,
    @SerializedName("periapsis_km")
    val periapsisKm: Any,
    @SerializedName("period_min")
    val periodMin: Any,
    @SerializedName("raan")
    val raan: Any,
    @SerializedName("reference_system")
    val referenceSystem: String,
    @SerializedName("regime")
    val regime: String,
    @SerializedName("semi_major_axis_km")
    val semiMajorAxisKm: Any
)
