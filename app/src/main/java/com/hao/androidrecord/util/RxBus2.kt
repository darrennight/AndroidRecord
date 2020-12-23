package com.hao.androidrecord.util

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.concurrent.ConcurrentHashMap

class RxBus2 {
    private var mBus: Subject<Any>? = null
    private var mStickyEventMap: ConcurrentHashMap<Class<*>, Any>? = null

    companion object {
        @Volatile
        private var mDefaultInstance: RxBus2? = null

        fun getInstance(): RxBus2 {
            if (mDefaultInstance == null) {
                mDefaultInstance = RxBus2()
            }
            return mDefaultInstance as RxBus2
        }

    }

    constructor(){
        mBus = PublishSubject.create<Any>().toSerialized()
        mStickyEventMap = ConcurrentHashMap()
    }

    /**
     * Broadcast event
     */
    fun post(event: Any?) {
        mBus!!.onNext(event!!)
    }

    fun <T> toObservable(
        owner: LifecycleOwner?,
        eventType: Class<T>?
    ): Observable<T>? {
        return toObservable(owner, eventType, Lifecycle.Event.ON_DESTROY)
    }

    /**
     * Make use of Rx-lifecycle to solve memory leaking issue
     */
    fun <T> toObservable(
        owner: LifecycleOwner?,
        eventType: Class<T>?,
        lifecycleEvent: Lifecycle.Event
    ): Observable<T>? {
        val provider =
            AndroidLifecycle.createLifecycleProvider(owner)
        return mBus!!.ofType(eventType)
            .doOnDispose { Log.i("RxBus", "Unsubscribe from RxBus") }
            .compose(provider.bindUntilEvent(lifecycleEvent))
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * To determine whether there's a observer subscribed to current Bus
     */
    fun hasObservers(): Boolean {
        return mBus!!.hasObservers()
    }

    fun reset() {
        mDefaultInstance = null
    }

    /**
     * Sticky event related
     * Broadcast a sticky type event
     */
    fun postSticky(event: Any) {
        synchronized(mStickyEventMap!!) {
            mStickyEventMap!!.put(event.javaClass, event)
        }
        post(event)
    }


    fun <T> toObservableSticky(owner: LifecycleOwner?, eventType: Class<T>?): Observable<T>? {
        return toObservableSticky(owner, eventType!!, Lifecycle.Event.ON_DESTROY)
    }

    /**
     * Make use of Rx-lifecycle to solve memory leaking issue
     */
    fun <T> toObservableSticky(owner: LifecycleOwner?, eventType: Class<T>, lifecycleEvent: Lifecycle.Event): Observable<T>? {
        synchronized(mStickyEventMap!!) {
            val provider =
                AndroidLifecycle.createLifecycleProvider(owner)
            val observable = mBus!!.ofType(eventType)
                .doOnDispose { Log.i("RxBus", "Unsubscribe from RxBus") }
                .compose(provider.bindUntilEvent(lifecycleEvent))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            val event = mStickyEventMap!![eventType]
            return if (event != null) {
                observable.mergeWith(Observable.create { subscriber ->
                    subscriber.onNext(eventType.cast(event)!!)
                })
            } else {
                observable
            }
        }
    }

    /**
     * Get sticky event based on eventType
     */
    fun <T> getStickyEvent(eventType: Class<T>): T? {
        synchronized(mStickyEventMap!!) { return eventType.cast(mStickyEventMap!![eventType]) }
    }

    /**
     * Remove sticky event based on eventType
     */
    fun <T> removeStickyEvent(eventType: Class<T>): T? {
        synchronized(mStickyEventMap!!) { return eventType.cast(mStickyEventMap!!.remove(eventType)) }
    }

    /**
     * Removed all sticky events
     */
    fun removeAllStickyEvents() {
        synchronized(mStickyEventMap!!) { mStickyEventMap!!.clear() }
    }


}