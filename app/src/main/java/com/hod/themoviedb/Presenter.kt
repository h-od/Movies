package com.hod.themoviedb

import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Presenter<in V : Presenter.View> {

    private lateinit var viewSubscriptions: CompositeDisposable

    @CallSuper
    protected open fun onViewAttached(view: V) {
        this.viewSubscriptions = CompositeDisposable()
    }

    @CallSuper
    open fun onViewDetached(view: V) {
        this.viewSubscriptions.clear()
    }

    protected fun clearOnDetached(disposable: Disposable) {
        this.viewSubscriptions.add(disposable)
    }

    protected fun <T> Observable<T>.subscribeUntilDetached() =
            subscribe().apply { clearOnDetached(this) }

    protected fun <T> Observable<T>.subscribeUntilDetached(onNext: (T) -> Unit) =
            subscribe(onNext).apply { clearOnDetached(this) }

    protected fun <T> Observable<T>.subscribeUntilDetached(onNext: (T) -> Unit, onError: (Throwable) -> Unit) =
            subscribe(onNext, onError).apply { clearOnDetached(this) }

    protected fun <T> Observable<T>.subscribeUntilDetached(onNext: (T) -> Unit, onError: (Throwable) -> Unit, onComplete: () -> Unit) =
            subscribe(onNext, onError, onComplete).apply { clearOnDetached(this) }


    interface View
}