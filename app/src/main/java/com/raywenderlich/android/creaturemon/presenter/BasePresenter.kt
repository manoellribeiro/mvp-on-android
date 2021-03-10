package com.raywenderlich.android.creaturemon.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V> {

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    protected val presenterScope = CoroutineScope(coroutineContext)

    // We use a WeakReference because typically the view is an Activity or Fragment class
    // and we need those view objects to be correctly garbage collected when they are destroyed
    private var view: WeakReference<V>? = null

    fun setView(view: V) {
        this.view = WeakReference(view)
    }

    protected fun getView(): V? = view?.get()

    fun onDestroy() {
        this.view = null
        presenterScope.cancel()
    }
}