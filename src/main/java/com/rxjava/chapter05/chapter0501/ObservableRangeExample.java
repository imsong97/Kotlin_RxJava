package com.rxjava.chapter05.chapter0501;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

/**
 * 반복문으로 사용 가능
 */
public class ObservableRangeExample {
    public static void main(String[] args){
        Observable<Integer> source = Observable.range(0, 5);
        source.subscribe(num -> Logger.log(LogType.ON_NEXT, num));
    }
}
