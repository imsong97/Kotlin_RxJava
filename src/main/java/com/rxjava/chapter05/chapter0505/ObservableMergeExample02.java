package com.rxjava.chapter05.chapter0505;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 각 구간의 차량 속도 데이터를 3개의 Observable에서 통지된 순서대로 merge하여 출력하는 예제
 */
public class ObservableMergeExample02 {
    public static void main(String[] args) {
        Observable<String> observable1 =
                SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);
        Observable<String> observable2 =
                SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB);
        Observable<String> observable3 =
                SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC);

        Observable.merge(observable1, observable2, observable3)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


        TimeUtil.sleep(1000L);
    }
}
