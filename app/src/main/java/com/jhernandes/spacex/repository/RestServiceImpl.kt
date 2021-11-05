package com.jhernandes.spacex.repository

class RestServiceImpl(private val service : WebClient) : RestService {

    override fun provideWebService(): WebService = service.getClient().create(WebService::class.java)
}
