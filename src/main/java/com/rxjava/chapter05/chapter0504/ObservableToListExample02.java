package com.rxjava.chapter05.chapter0504;

import com.rxjava.common.Car;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

/**
 * 각각의 통지될 Car 객체를 List로 변환해서 Single로 한번만 통지하는 예제
 */
public class ObservableToListExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toList()
                .subscribe(carList -> Logger.log(LogType.ON_NEXT, carList));
    }
}
