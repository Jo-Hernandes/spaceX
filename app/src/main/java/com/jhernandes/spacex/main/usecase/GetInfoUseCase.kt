package com.jhernandes.spacex.main.usecase

import com.jhernandes.spacex.commons.observeOnMainThread
import com.jhernandes.spacex.model.ServiceInformationModel
import com.jhernandes.spacex.model.adapters.ServiceInformationAdapter
import com.jhernandes.spacex.repository.WebService
import io.reactivex.disposables.Disposable

class GetInfoUseCase(
    private val service: WebService,
    private val infoAdapter : ServiceInformationAdapter
) {

    private var disposable: Disposable? = null
        set(value) {
            field?.dispose()
            field = value
        }

    operator fun invoke(
        onSuccess : (ServiceInformationModel) -> Unit,
        onFailure : (Throwable) -> Unit) {
        disposable = service.getSpaceXInfo()
            .observeOnMainThread()
            .map(infoAdapter::mapFromService)
            .subscribe(onSuccess, onFailure)
    }

    fun onCleared() {
        disposable?.dispose()
    }
}
