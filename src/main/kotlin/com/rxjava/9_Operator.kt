package com.rxjava

import com.rxjava.utils.LogType
import com.rxjava.utils.Logger
import com.rxjava.utils.TimeUtil
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(){
//    merge()
//    concat()
    zip()
}

fun merge(){
    val o1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .take(5)

    val o2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                    .take(5)
                    .map { it+100 }

    Observable.merge(o1, o2)
        .subscribe { Logger.log(LogType.ON_NEXT ,it) }

    TimeUtil.sleep(4000)
}

fun concat(){
    val o1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .take(5)

    val o2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                    .take(5)
                    .map { it + 100 }

    Observable.concat(o1, o2) // o1-o2 순서 바꾸면 o2 먼저 출력
        .subscribe { Logger.log(LogType.ON_NEXT ,it) }

    TimeUtil.sleep(4000)
}

fun zip(){
    val o1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .take(4) // o2의 개수보다 적으므로 zip 완료시점은 o1의 개수(4개)

    val o2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                    .take(6)

    Observable.zip(o1, o2) { d1, d2 -> d1 + d2 }
        .subscribe { Logger.log(LogType.ON_NEXT, it) }

    TimeUtil.sleep(4000)
}
