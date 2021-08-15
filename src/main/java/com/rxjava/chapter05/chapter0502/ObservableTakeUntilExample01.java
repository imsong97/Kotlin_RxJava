package com.rxjava.chapter05.chapter0502;

import com.rxjava.common.Car;
import com.rxjava.common.SampleData;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;

/**
 * 파리미터로 지정한 조건이 될 때까지 데이터를 계속 발행
 */
public class ObservableTakeUntilExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .takeUntil((Car car) -> car.getCarName().equals("트랙스"))
                .subscribe(car -> System.out.println(car.getCarName()));

        TimeUtil.sleep(300L);
    }
}
