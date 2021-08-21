package com.rxjava

import com.rxjava.common.Car
import com.rxjava.common.CarMaker
import com.rxjava.common.SampleData
import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import com.rxjava.utils.TimeUtil
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(){
//    map
//    flatMap()
//    concatMap()
//    switchMap()
//    groupBy()
//    toList()
    toMap()
}

fun map(){
    Observable.just(1, 3, 5, 7)
        .map { it + 1 }
        .subscribe{ Logger.log(LogType.ON_NEXT, it) }
}

fun flatMap(){
    // 첫번째 유형
    Observable.just("Hello")
        .flatMap { hello: String ->
            Observable.just("Java", "Android", "Kotlin").map { "$hello $it" }
        }
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    // 두번째 유형
    Observable.range(2, 1)
        .flatMap(
            { Observable.range(1, 9) }
        ) { sourceData: Int, transformedData: Int ->
            "$sourceData * $transformedData = ${sourceData * transformedData}"
        }
        .subscribe { Logger.log(LogType.ON_NEXT, it) }
}

fun concatMap(){
    TimeUtil.start()
    Observable.interval(100L, TimeUnit.MILLISECONDS)
        .take(4)
        .skip(2)
        .concatMap { num: Long ->
            Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(10)
                .skip(1)
                .map { "$num * $it = ${num * it}" }
        }
        .subscribe(
            { Logger.log(LogType.ON_NEXT, it) },
            { error: Throwable? -> }
        ) {
            TimeUtil.end()
            TimeUtil.takeTime()
        }

    TimeUtil.sleep(5000L)
    // flatMap을 사용 할 경우 더 빠른 실행시간
}

fun switchMap(){
    TimeUtil.start()
    Observable.interval(100L, TimeUnit.MILLISECONDS)
        .take(4)
        .skip(2)
        .doOnNext { Logger.log(LogType.ON_NEXT, it) }
        .switchMap { num: Long ->
            Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(10)
                .skip(1)
                .map { "$num * $it = ${num * it}" }
        }
        .subscribe(
            { Logger.log(LogType.ON_NEXT, it) },
            { error: Throwable? -> }
        ) {
            TimeUtil.end()
            TimeUtil.takeTime()
        }

    TimeUtil.sleep(5000L)
    // 처리 속도가 빠르기 때문에 3단만 출력
}

fun groupBy(){
    Observable.fromIterable(SampleData.carList).groupBy { it.carMaker }
        .subscribe {
            it.filter { car: Car -> it.key == CarMaker.CHEVROLET }
                .subscribe { car: Car ->
                    Logger.log(LogType.ON_NEXT, "Group: ${it.key} \t Car name: ${car.carName}")
                }
        }
}

fun toList(){
    Observable.just(1, 3, 5, 7, 9)
        .toList()
        .subscribe { data: List<Int>? ->
            Logger.log(LogType.ON_NEXT, data)
        }
}

fun toMap(){
    Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
        .toMap {
            it.split("-").toTypedArray()[0] // 반환값은 Map의 key
        }
        .subscribe { data: Map<String, String>? ->
            Logger.log(LogType.ON_NEXT, data)
        }

    Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
        .toMap(
            { it.split("-").toTypedArray()[0] } // key
        ) { it.split("-").toTypedArray()[1] } // value
        .subscribe { data: Map<String, String>? ->
            Logger.log(LogType.ON_NEXT, data)
        }
}