package com.jhernandes.spacex.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.jhernandes.spacex.commons.ConnectionException
import com.jhernandes.spacex.main.usecase.GetInfoUseCase
import com.jhernandes.spacex.main.usecase.GetLaunchesUseCase
import com.jhernandes.spacex.model.MissionLaunchModel
import com.jhernandes.spacex.model.ServiceInformationModel
import java.io.IOException

class MainViewModel(
    val getInfo: GetInfoUseCase,
    val getLaunches: GetLaunchesUseCase
) : ViewModel() {


    private val _informationData: MutableLiveData<ServiceInformationModel> = MutableLiveData()
    val informationData: LiveData<ServiceInformationModel> get() = _informationData

    private val _launchesData: MutableLiveData<List<MissionLaunchModel>> = MutableLiveData()
    val launchesData: LiveData<List<MissionLaunchModel>> get() = _launchesData

    private val _displayException: LiveEvent<ConnectionException> = LiveEvent()
    val displayException: LiveData<ConnectionException>
        get() = _displayException

    private val _action: LiveEvent<MainFragmentAction> = LiveEvent()
    val action: LiveData<MainFragmentAction> get() = _action

    private val _showLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val showLoading: LiveData<Boolean> get() = _showLoading

    var filterSuccesses: Boolean = false
    var yearsRange = listOf(2000f, 2030f)

    fun getSpaceXInfo() {
        _showLoading.postValue(true)
        getInfo(_informationData::postValue) {
            handleError(it) { getSpaceXInfo() }
        }
    }

    fun getSpaceXLaunches() {
        _showLoading.postValue(true)
        getLaunches(::postFilteredLaunches) { throwable ->
            handleError(throwable) { getSpaceXLaunches() }
        }
    }

    private fun postFilteredLaunches(launches: List<MissionLaunchModel>) =
        _launchesData.postValue(launches
            .filter { it.launchSuccess || !filterSuccesses }
            .filter {
                yearsRange.minOrNull()
                    ?.rangeTo(yearsRange.maxOrNull() ?: 0f)
                    ?.contains(it.year.toFloat()) ?: true
            }
        ).also {
            _showLoading.postValue(false)
        }

    fun handleYearRange(yearValues: List<Float>) {
        yearsRange = yearValues
        postFilteredLaunches(getLaunches.cachedResponse ?: listOf())
    }

    fun updateSortOrder(orderType: GetLaunchesUseCase.SortOrder) {
        getLaunches.currentOrder = orderType
        getSpaceXLaunches()
    }

    fun handleLaunchPressed(launchModel: MissionLaunchModel) =
        _action.postValue(MainFragmentAction.OpenLaunchDialog(launchModel))

    fun handleLinkPressed(url: String?) =
        url?.let { _action.postValue(MainFragmentAction.OpenUrl(it)) }
            ?: _displayException.postValue(ConnectionException.NoLinkFound)

    fun handleSuccessFilterChanged(checked: Boolean) {
        filterSuccesses = checked
        postFilteredLaunches(getLaunches.cachedResponse ?: listOf())
    }

    private fun handleError(error: Throwable, retryAction: () -> Unit) =
        _displayException.postValue(
            when (error) {
                is IOException -> ConnectionException.OfflineException(retryAction)
                is java.net.UnknownHostException -> ConnectionException.NoHostException(retryAction)
                else -> ConnectionException.FetchDataException(retryAction)
            }
        ).also {
            _showLoading.postValue(false)
        }


    override fun onCleared() {
        super.onCleared()
        getInfo.onCleared()
        getLaunches.onCleared()
    }

    fun handleMenuItemPressed(): Boolean {
        _action.postValue(MainFragmentAction.OpenOptionsDialog)
        return true
    }

}
