package com.jhernandes.spacex.main.usecase

import com.jhernandes.spacex.commons.observeOnMainThread
import com.jhernandes.spacex.model.MissionLaunchModel
import com.jhernandes.spacex.model.adapters.MissionLaunchAdapter
import com.jhernandes.spacex.repository.WebService
import io.reactivex.disposables.Disposable

class GetLaunchesUseCase(
    private val service: WebService,
    private val adapter: MissionLaunchAdapter
) {

    enum class SortOrder(val orderValue: String) {
        ASC("asc"), DESC("desc")
    }

    var currentOrder = SortOrder.DESC

    private var disposable: Disposable? = null
        set(value) {
            field?.dispose()
            field = value
        }

    var cachedResponse: List<MissionLaunchModel>? = null

    operator fun invoke(
        onSuccess: (List<MissionLaunchModel>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        disposable = service.getSpaceXLaunches(currentOrder.orderValue)
            .observeOnMainThread()
            .map { it.map(adapter::mapFromService) }
            .subscribe({
                cachedResponse = it
                onSuccess(it)
            }, onFailure)
    }

    fun onCleared() {
        disposable?.dispose()
        cachedResponse = null
    }
}
