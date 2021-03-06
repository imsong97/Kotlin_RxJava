package com.rxjava.chapter05.chapter0501;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * polling 용도로 주로 사용.
 */
public class ObservableIntervalExample {
    public static void main(String[] args){
        System.out.println("# start : " +TimeUtil.getCurrentTimeFormatted());

        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .map(num -> num + " count")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000);
    }
}
