package com.rxjava.chapter05.chapter0502;

import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import io.reactivex.Observable;

public class ObservableFilterExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.CHEVROLET)
                .filter(car -> car.getCarPrice() > 30000000)
                .subscribe(car -> System.out.println(car.getCarName()));
    }
}
