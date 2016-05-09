package com.shashank.chatapp;

import android.databinding.ObservableField;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class RxObservableField<T> extends ObservableField<T> {
    private BehaviorSubject<T> mSubject;
    private Subscription mSubscription;

    public RxObservableField(T defaultVal) {
        this(defaultVal, null);
    }

    public RxObservableField(T defaultVal, final Observable<T> externalSource) {
        mSubject = BehaviorSubject.create ();
        this.set(defaultVal);
        if(externalSource!=null)
        mSubscription = externalSource.subscribe(new Action1<T>() {
            @Override
            public void call(T value) {
                set(value);
            }
        });
    }

    public RxObservableField(Observable<T> source) {
        this(null,source);
    }

    @Override
    public void set(T value) {
        super.set(value);
        mSubject.onNext(value);
    }


    public Observable<T> getObservable() {
        return mSubject.asObservable();
    }

    public void close() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
