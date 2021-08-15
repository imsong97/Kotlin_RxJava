package com.rxjava.chapter05.chapter0504;

import com.rxjava.common.Car;
import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

/**
 * Group으로 묶은 데이터들 중에서 filter를 이용해 필터링한 Group의 데이터만 출력하는 예제
 */
public class ObservableGroupByExample02 {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                Observable.fromIterable(SampleData.carList).groupBy(Car::getCarMaker);

        observable.subscribe(
                groupedObservable ->
                        groupedObservable
                                .filter(car -> groupedObservable.getKey().equals(CarMaker.CHEVROLET))
                                .subscribe(
                                        car -> Logger.log(
                                                LogType.PRINT, "Group: "
                                                        + groupedObservable.getKey()
                                                        + "\t Car name: " + car.getCarName())
                                )
        );

    }
}
