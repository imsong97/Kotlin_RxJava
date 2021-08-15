package com.hello

import io.reactivex.Observable

fun main(){
    val observable = Observable.just("Hello", "RxJava")

    observable.subscribe{
        println(it)
    }
}