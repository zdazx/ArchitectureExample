package com.thoughtworks.architectureexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArchViewModel extends ViewModel {
    private MutableLiveData<Integer> number;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;

    public LiveData<Integer> getNumber() {
        if (Objects.isNull(number)) {
            number = new MutableLiveData<>();
        }
        return number;
    }

    public void increase() {
        if (Objects.nonNull(disposable) && !disposable.isDisposed()) {
            return;
        }
        Observable.interval(1, TimeUnit.SECONDS)
                .map(Long::intValue)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        number.postValue(integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() { }
                });
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
