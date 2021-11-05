package com.jhernandes.spacex.main.ui

import com.jhernandes.spacex.model.MissionLaunchModel

sealed interface MainFragmentAction {

    class OpenLaunchDialog(val launchModel: MissionLaunchModel) : MainFragmentAction
    class OpenUrl(val url: String) : MainFragmentAction
    object OpenOptionsDialog : MainFragmentAction
}
