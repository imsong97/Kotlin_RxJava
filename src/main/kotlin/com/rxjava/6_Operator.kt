package com.rxjava

import com.rxjava.chapter05.chapter0501.ObservableFromFutureExample
import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import com.rxjava.utils.TimeUtil
import io.reactivex.Observable
import java.time.LocalTime
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

fun main(){
//    interval()
//    range()
//    timer()
//    defer()
//    fromIterable()
    fromFuture()
}

fun interval(){
    println("Interval")

    Observable.interval(1000L, TimeUnit.MILLISECONDS)
        .map { "$it count" }
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    TimeUtil.sleep(3000) // 별도의 스레드에서 동작하기 때문에 메인스레드에 딜레이 시간 설정
}

fun range(){
    println("Range")

    Observable.range(0, 5)
        .subscribe { Logger.log(LogType.DO_ON_NEXT, it) }
}

fun timer(){
    println("Timer")

    Observable.timer(2000, TimeUnit.MILLISECONDS)
        .map { "Do work" }
        .subscribe { Logger.log(LogType.DO_ON_NEXT, it) }

    TimeUtil.sleep(3000)
}

fun defer(){
    println("Defer")

    val o1 = Observable.defer { Observable.just(LocalTime.now()) }
    val o2 = Observable.just(LocalTime.now())

    o1.subscribe { Logger.log(LogType.PRINT, " # defer() 구독1의 구독 시간: $it") }
    o2.subscribe { Logger.log(LogType.PRINT, " # just() 구독1의 구독 시간: $it") }

    Thread.sleep(3000)

    o1.subscribe { Logger.log(LogType.PRINT, " # defer() 구독2의 구독 시간: $it") }
    o2.subscribe { Logger.log(LogType.PRINT, " # just() 구독자2의 구독 시간: $it") }
}

fun fromIterable(){
    println("FromIterable")

    Observable.fromIterable(listOf("KOR", "USA", "CAN", "ITA", "GER"))
        .subscribe { Logger.log(LogType.ON_NEXT, it) }
}

fun fromFuture(){
    println("FromFuture")

    Logger.log(LogType.PRINT, "# start time")

    // 긴 처리 시간이 걸리는 작업
    val future: Future<Double> = ObservableFromFutureExample.longTimeWork()

    // 짧은 처리 시간이 걸리는 작업
    ObservableFromFutureExample.shortTimeWork()

    Observable.fromFuture(future)
        .subscribe {
            Logger.log(LogType.PRINT, "# 긴 처리 시간 작업 결과 : $it")
        }

    Logger.log(LogType.PRINT, "# end time")
}