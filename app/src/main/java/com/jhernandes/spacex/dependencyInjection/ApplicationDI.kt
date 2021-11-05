package com.jhernandes.spacex.dependencyInjection

import com.jhernandes.spacex.main.ui.MainViewModel
import com.jhernandes.spacex.main.usecase.GetInfoUseCase
import com.jhernandes.spacex.main.usecase.GetLaunchesUseCase
import com.jhernandes.spacex.model.adapters.MissionLaunchAdapter
import com.jhernandes.spacex.model.adapters.ServiceInformationAdapter
import org.koin.dsl.module


val application = module {

    factory { MainViewModel(get(), get()) }

    single { ServiceInformationAdapter() }

    single { GetInfoUseCase(get(), get()) }

    single { MissionLaunchAdapter() }

    single { GetLaunchesUseCase(get(), get()) }

}
