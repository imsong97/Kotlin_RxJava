package com.rxjava.chapter05.chapter0502;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

public class ObservableSkipWhileExample {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .skipWhile(car -> !car.getCarName().equals("티볼리"))
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarName()));
    }
}
