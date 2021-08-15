package com.rxjava

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

fun main(){
    coldPublisher()
    println("=============================================================")
    println("=============================================================")
    hotPublisher()
}

// ColdPublisher: Flowable, Observable 등
fun coldPublisher(){
    val cold = Flowable.just(1,3,4,5)

    cold.subscribe{
        println("구독자 1: $it")
    }

    cold.subscribe{
        println("구독자 2: $it")
    }
}

// HotPublisher: Subject, Processor 등
fun hotPublisher(){
    val hot: PublishProcessor<Int> = PublishProcessor.create()

    hot.subscribe{
        println("구독자 1: $it")
    }
    hot.onNext(1)
    hot.onNext(3)

    hot.subscribe{
        println("구독자 2: $it")
    }
    hot.onNext(4)
    hot.onNext(5)

    hot.onComplete()
}