package com.rxjava.chapter03.chapter0303;

import com.rxjava.utils.DateUtil;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ObservableToSingleExample {
    public static void main(String[] args){
        Observable<String> observable = Observable.just(DateUtil.getNowDate());
        Single.fromObservable(observable)
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, " # 날짜시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
    }
}
