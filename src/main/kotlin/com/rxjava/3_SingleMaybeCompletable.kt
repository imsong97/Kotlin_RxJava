package com.rxjava

import com.rxjava.utils.DateUtil
import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import com.rxjava.utils.TimeUtil
import io.reactivex.*
import io.reactivex.schedulers.Schedulers

fun main(){
    single()
    println("\n=========================================================\n")
    maybe()
    println("\n=========================================================\n")
    completable()
}

fun single(){
    // create 사용
    Single.create { emitter: SingleEmitter<String> ->
        emitter.onSuccess(DateUtil.getNowDate())
    }.subscribe(
        { Logger.log(LogType.ON_SUCCESS, "# 날짜시각: $it") },
        { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
    ) // nothing to do

    println("======")

    // just 사용
    Single.just(DateUtil.getNowDate())
        .subscribe(
            { Logger.log(LogType.ON_SUCCESS, "# 날짜시각: $it") },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
        ) // nothing to do
}

fun maybe(){
    // create 사용
    Maybe.create { emitter: MaybeEmitter<String> ->
//        emitter.onSuccess(DateUtil.getNowDate()) // 데이터 통지 O
        emitter.onComplete() // 데이터 통지 X -> 완료
    }.subscribe(
        { Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: $it") },
        { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
    ) { Logger.log(LogType.ON_COMPLETE) }

    println("======")

    // just 사용
    Maybe.just(DateUtil.getNowDate()) // 데이터 통지 O
        .subscribe(
            { Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: $it") },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
        ) { Logger.log(LogType.ON_COMPLETE) }
    Maybe.empty<Any>() // 데이터 통지 X -> 완료
        .subscribe(
            { Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: $it") },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
        ) { Logger.log(LogType.ON_COMPLETE) }

    println("======")

    // fromSingle 사용
    val single = Single.just(DateUtil.getNowDate())
    Maybe.fromSingle(single)
        .subscribe(
            { Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: $it") },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
        ) { Logger.log(LogType.ON_COMPLETE) }
}

fun completable(){
    Completable
        .create { emitter: CompletableEmitter ->
            // 데이터를 발행하는것이 아니라 특정 작업을 수행한 후, 완료를 통지한다.
            var sum = 0
            for (i in 0..99) {
                sum += i
            }
            Logger.log(LogType.PRINT, "# 합계: $sum")
            emitter.onComplete()
        }
        .subscribeOn(Schedulers.computation())
        .subscribe(
            { Logger.log(LogType.ON_COMPLETE) },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) }
        ) // nothing to do

    TimeUtil.sleep(100L)
}