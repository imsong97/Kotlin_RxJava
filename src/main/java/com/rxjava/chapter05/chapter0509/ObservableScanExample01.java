package com.rxjava.chapter05.chapter0509;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

/**
 * scan을 이용해 1부터 10까지의 sum을 구하는 예제(초기값 없음)
 * - 집계 중간 결과를 계속해서 출력한다.
 */
public class ObservableScanExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .scan((x, y) -> x + y)
                .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + result));
    }
}
