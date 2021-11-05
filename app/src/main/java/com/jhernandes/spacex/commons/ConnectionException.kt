package com.jhernandes.spacex.commons

import androidx.annotation.StringRes
import com.jhernandes.spacex.R

sealed class ConnectionException(@StringRes val message: Int, val retry: (() -> Unit)?) {

    class FetchDataException(retry: () -> Unit) :
        ConnectionException(R.string.fetch_exception, retry)

    class OfflineException(retry: () -> Unit) :
        ConnectionException(R.string.offline_exception, retry)

    class NoHostException(retry: () -> Unit) :
        ConnectionException(R.string.no_host, retry)

    object NoLinkFound :
        ConnectionException(R.string.link_not_found, null)

}


