package com.rxjava

import com.sun.media.jfxmedia.logging.Logger
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun main(){
    backPressure()
}

// 배압(Back Pressure)
fun backPressure() {
    Flowable.interval(1L, TimeUnit.MILLISECONDS)
        .observeOn(Schedulers.computation())
        .subscribe {
            println("처리 대기중")
            println(it)
        }
    Thread.sleep(2000L)
}

fun flowable(){

}