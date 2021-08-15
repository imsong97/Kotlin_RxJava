package com.rxjava.chapter05.chapter0506;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class CanNotUseTryCatchExample {
    public static void main(String[] args) {
        try{
            Observable.just(2)
                    .map(num -> num / 0)
                    .subscribe(System.out::println);
        }catch (Exception e){
            Logger.log(LogType.PRINT, "# 에러 처리가 필요: " + e.getCause());
        }
    }
}
