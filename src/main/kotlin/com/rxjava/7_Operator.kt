package com.rxjava

import com.rxjava.common.CarMaker
import com.rxjava.common.SampleData
import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import com.rxjava.utils.TimeUtil
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(){
//    filter()
//    distinct()
//    take()
//    takeUntil()
    skip()
}

fun filter(){
    Observable.fromIterable(SampleData.carList)
        .filter { it.carMaker == CarMaker.CHEVROLET }
        .subscribe {
            Logger.log(LogType.ON_NEXT, it.carMaker.toString() + " : " + it.carName)
        }

    Observable.fromIterable(SampleData.carList)
        .filter { it.carMaker == CarMaker.CHEVROLET && it.carPrice > 30000000} // 조건 여러개
        .subscribe { println(it.carName) }
}

fun distinct(){
    Observable.fromArray(*SampleData.carMakersDuplicated)
        .distinct()
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    Observable.fromArray(*SampleData.carMakersDuplicated)
        .distinct()
        .filter { it == CarMaker.SSANGYOUNG }
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    Observable.fromIterable(SampleData.carList)
        .distinct { it.carMaker }
        .subscribe { Logger.log(LogType.ON_NEXT, it.carName) }
}

fun take(){
    Observable.just("a", "b", "c", "d")
        .take(2)
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    // 지정한 시간동안 데이터를 계속 발행
    Observable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(3500L, TimeUnit.MILLISECONDS)
        .subscribe { Logger.log(LogType.ON_NEXT, it) }
    TimeUtil.sleep(3500L)
}

fun takeUntil(){
    // 첫번째 유형 : 파리미터로 지정한 조건이 될 때까지 데이터를 계속 발행
    Observable.fromIterable(SampleData.carList)
        .takeUntil { it.carName == "트랙스" }
        .subscribe { println(it.carName) }
    TimeUtil.sleep(300L)

    // 두번째 유형 : 파라미터로 받은 Flowable/Observable이 최초로 데이터를 발행할 때까지 계속 데이터를 발행
    // timer와 함께 사용하여 특정 시점이 되기전까지 데이터를 발행하는데 활용하기 용이
    Observable.interval(1000L, TimeUnit.MILLISECONDS)
        .takeUntil(Observable.timer(5500L, TimeUnit.MILLISECONDS))
        .subscribe { Logger.log(LogType.ON_NEXT, it)}
    TimeUtil.sleep(5500L)
}

fun skip(){
    // 첫번째 유형
    Observable.range(1, 15)
        .skip(3)
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    // 두번째 유형
    Observable.interval(300L, TimeUnit.MILLISECONDS)
        .skip(1000L, TimeUnit.MILLISECONDS)
        .subscribe { Logger.log(LogType.ON_NEXT, it) }
    TimeUtil.sleep(3000L)
}