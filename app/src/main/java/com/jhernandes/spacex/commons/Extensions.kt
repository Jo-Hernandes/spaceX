package com.jhernandes.spacex.commons

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.jhernandes.spacex.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.observeOnMainThread(): Single<T> = subscribeOn(Schedulers.io()).observeOn(
    AndroidSchedulers.mainThread()
)

fun <T> Fragment.observe(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, { action(it) })
}

@BindingAdapter("goneUnless")
fun View.goneUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter(
    value = ["imageUrl", "placeholder", "error"],
    requireAll = false
)
fun ImageView.loadImage(
    url: String?,
    placeholder: Drawable?,
    error: Drawable?,
) = Glide
    .with(context)
    .load(url)
    .apply(
        RequestOptions()
            .fitCenter()
            .placeholder(placeholder)
            .error(error)
    )
    .into(this)

fun Fragment.showSnackbar(
    @StringRes text: Int,
    duration: Int = BaseTransientBottomBar.LENGTH_LONG,
    onRetry: (() -> Unit)? = null
) = view?.let { Snackbar.make(it, text, duration) }?.also { snackbar ->
    onRetry?.let {
        snackbar.setAction(R.string.retry) { onRetry() }
    }
    snackbar.show()
}
