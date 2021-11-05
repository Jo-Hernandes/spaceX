package com.jhernandes.spacex.dependencyInjection

import com.jhernandes.spacex.repository.RestServiceImpl
import com.jhernandes.spacex.repository.WebClient
import com.jhernandes.spacex.repository.WebService
import org.koin.dsl.module


val dataSource = module {

    factory {
        return@factory RestServiceImpl(get()).provideWebService()
    }

    factory {
        return@factory WebClient()
    }

}
