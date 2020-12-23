package com.hao.androidrecord.util

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by zenghao on 2017/6/28.
 */
class RxBus private constructor(){

    private val mBus: PublishRelay<Any> = PublishRelay.create()

    companion object{
        private var instance: RxBus = RxBus()

        @Synchronized
        fun getRxBus(): RxBus {
            if (instance == null) {
                instance = RxBus()
            }
            return instance
        }
    }

    fun post(obj:Any){
        mBus.accept(obj)
    }

    fun toObservable(): Observable<Any> = mBus

    fun <T> toObservable(clzz: Class<T>): Observable<T> = mBus.ofType(clzz)

    fun hasObservers():Boolean{
        return mBus.hasObservers()
    }


    fun unRegist(disposable: Disposable){
        if (!disposable.isDisposed){
            disposable.dispose()
        }
    }
}