package com.rxjava.chapter05.chapter0504;

import com.rxjava.common.Car;
import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

/**
 * Car 제조사 별로 그룹으로 묶어서 데이터를 통지하는 예제
 */
public class ObservableGroupByExample01 {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                Observable.fromIterable(SampleData.carList).groupBy(car -> car.getCarMaker());

        observable.subscribe(
                groupedObservable -> groupedObservable.subscribe(
                        car -> Logger.log(
                                LogType.ON_NEXT, "Group: " +
                                        groupedObservable.getKey() +
                                        "\t Car name: " + car.getCarName())
                )
        );

    }
}
