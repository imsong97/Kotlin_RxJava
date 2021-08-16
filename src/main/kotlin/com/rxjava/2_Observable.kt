package com.rxjava

import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun main(){
    val observable = Observable.create { emitter: ObservableEmitter<String?> ->
        val datas = arrayOf("Hello", "RxJava!")
        for (data in datas) {
            if (emitter.isDisposed) return@create
            emitter.onNext(data)
        }
        emitter.onComplete()
    }

    observable.observeOn(Schedulers.computation())
        .subscribe(
            { Logger.log(LogType.ON_NEXT, it) },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) },
            { Logger.log(LogType.ON_COMPLETE) }
        ) { disposable: Disposable? ->
            // 처리 동작 없음 -> 배압전략이 없기 때문에
        }

    Thread.sleep(500L)
}