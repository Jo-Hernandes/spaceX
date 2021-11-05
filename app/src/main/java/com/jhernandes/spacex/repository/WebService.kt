package com.jhernandes.spacex.repository

import com.jhernandes.spacex.repository.model.InfoResponse
import com.jhernandes.spacex.repository.model.LaunchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("info")
    fun getSpaceXInfo(): Single<InfoResponse>

    @GET("launches")
    fun getSpaceXLaunches(@Query("order") order: String = "asc"): Single<LaunchResponse>

}
