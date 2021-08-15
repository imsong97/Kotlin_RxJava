package com.rxjava.chapter05.chapter0502.quiz;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

public class QuizAnswerForChapter050204 {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .skipLast(3)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
