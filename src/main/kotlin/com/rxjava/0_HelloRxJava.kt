package com.rxjava

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

fun main(){
    Observable.just(100, 200, 300, 400, 500) // 데이터 발행
        .subscribeOn(Schedulers.io()) // 스레드 지정: 데이터의 발행, 흐름
//        .observeOn(Schedulers.io()) // 스레드 지정: 데이터의 가공, 처리
        .filter { // 데이터 가공
            it > 300
        }
        .subscribe{ // 발행 된 데이터를 전달받아서(구독) 처리
            println("${getThreadName()} : $it")
        }
    Thread.sleep(500)
}

fun getThreadName(): String{
    return Thread.currentThread().name
    // commit - 1
    // commit - 2
}