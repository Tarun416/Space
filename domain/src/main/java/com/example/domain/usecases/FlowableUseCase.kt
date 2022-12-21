package com.example.domain.usecases

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import java.util.concurrent.Flow

abstract class FlowableUseCase<T, Params> {

    private var disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params?): Flowable<T>

    fun execute(observer: DisposableSubscriber<T>, params: Params?) {
        val flowable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Flowable<T>

        addDisposable(flowable.subscribeWith(observer))

    }


    fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}