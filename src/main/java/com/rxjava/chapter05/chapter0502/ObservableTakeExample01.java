package com.rxjava.chapter05.chapter0502;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

/**
 * 지정한 갯수만큼 데이터를 발행
 */
public class ObservableTakeExample01 {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d")
                .take(2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
