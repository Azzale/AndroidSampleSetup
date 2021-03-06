package no.txcb.sample.tools;

import android.support.v4.util.LruCache;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class ObservableCache {


    private LruCache<String, Observable<?>> apiObservables;


    @Inject

    public ObservableCache() {
        super();
        apiObservables = new LruCache<>(10);
    }

    public Observable<?> getCachedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache) {
        return getCachedObservable(unPreparedObservable, clazz, "", cacheObservable, useCache);
    }

    public Observable<?> getCachedObservable(Observable<?> unPreparedObservable, Class<?> clazz, String key, boolean cacheObservable, boolean useCache) {
        Observable<?> preparedObservable = null;

        if (useCache) {
            preparedObservable = apiObservables.get(clazz + key);
        }

        if (preparedObservable != null) {
            return preparedObservable;
        }


        preparedObservable = unPreparedObservable;

        if (cacheObservable) {
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz + key, preparedObservable);
        }

        return preparedObservable;
    }

    public void clearCache() {
        apiObservables.evictAll();
    }

}
