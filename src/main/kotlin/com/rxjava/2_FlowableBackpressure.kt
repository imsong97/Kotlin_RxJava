package com.rxjava

import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import com.rxjava.utils.TimeUtil
import io.reactivex.BackpressureOverflowStrategy
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

fun main(){
//    missing()
//
//    dropLatest()
//    dropOldest()
//
//    drop()

    flowable()
}

// 배압(Back Pressure) 전략 -> Missing
fun missing() {
    Flowable.interval(1L, TimeUnit.MILLISECONDS) // interval() -> 일정주기마다 0부터 1씩 증가하는 숫자 생성(통지)
        .doOnNext { Logger.log(LogType.DO_ON_NEXT, it) }
        .observeOn(Schedulers.computation())
        .subscribe(
            {
                Logger.log(LogType.PRINT, "# 소비자 처리 대기 중..")
                TimeUtil.sleep(1000L) // 지연시간 설정
                Logger.log(LogType.ON_NEXT, it)
            },
            { error: Throwable? ->
                Logger.log(LogType.ON_ERROR, error)
            }
        ) { Logger.log(LogType.ON_COMPLETE) }

    Thread.sleep(2000L)
}

// 배압 전략 -> Buffer - Drop_Latest 전략
fun dropLatest(){
    println("\n\nDrop_Latest Start : ${TimeUtil.getCurrentTimeFormatted()}")

    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .doOnNext { Logger.log("#inverval doOnNext()", it) }
        .onBackpressureBuffer(
            2, { Logger.log("overflow!") },
            BackpressureOverflowStrategy.DROP_OLDEST
        )
        .doOnNext { Logger.log("#BackpressureBuffer doOnNext() : ", it) }
        .observeOn(Schedulers.computation(), false, 1)
        .subscribe(
            {
                TimeUtil.sleep(1000L)
                Logger.log(LogType.ON_NEXT, it)
            },
            { error: Throwable? ->
                Logger.log(LogType.ON_ERROR, error)
            }
        )

    TimeUtil.sleep(2800L)
}

// 배압 전략 -> Buffer - Drop_Oldest 전략
fun dropOldest(){
    println("\n\nDrop_Oldest Start : ${TimeUtil.getCurrentTimeFormatted()}")

    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .doOnNext { Logger.log("#inverval doOnNext()", it) }
        .onBackpressureBuffer(
            2, { Logger.log("overflow!") },
            BackpressureOverflowStrategy.DROP_LATEST
        )
        .doOnNext { Logger.log("#BackpressureBuffer doOnNext() : ", it) }
        .observeOn(Schedulers.computation(), false, 1)
        .subscribe(
            {
                TimeUtil.sleep(1000L)
                Logger.log(LogType.ON_NEXT, it)
            },
            { error: Throwable? ->
                Logger.log(LogType.ON_ERROR, error)
            }
        )

    TimeUtil.sleep(2800L)
}

// 배압 전략 -> Drop
fun drop(){
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .doOnNext { Logger.log("#inverval doOnNext()", it) }
        .onBackpressureDrop { Logger.log(LogType.PRINT, "$it Drop!") }
        .observeOn(Schedulers.computation(), false, 1)
        .subscribe(
            {
                TimeUtil.sleep(1000L)
                Logger.log(LogType.ON_NEXT, it)
            },
            { error: Throwable? ->
                Logger.log(LogType.ON_ERROR, error)
            }
        )

    TimeUtil.sleep(5500L)
}

fun flowable(){
    val flowable = Flowable.create(
        { emitter: FlowableEmitter<String?> ->
            val datas = arrayOf("Hello", "RxJava!")
            for (data in datas) {
                // 구독이 해지되면 처리 중단
                if (emitter.isCancelled) return@create
                // 데이터 발행
                emitter.onNext(data!!)
            }
            // 데이터 발행 완료를 알림
            emitter.onComplete()
        },
        BackpressureStrategy.BUFFER // 구독자의 처리가 늦을 경우 데이터를 버퍼에 담아둠
    )

    flowable.observeOn(Schedulers.computation())
        .subscribe(
            { Logger.log( LogType.ON_NEXT, it) },
            { error: Throwable? -> Logger.log(LogType.ON_ERROR, error) },
            { Logger.log(LogType.ON_COMPLETE) }
        ) { subscription: Subscription -> // 데이터 개수 요청 및 구독을 취소하기 위한 Subscription 객체
            subscription.request(Long.MAX_VALUE)
        }

    Thread.sleep(500L)
}