package com.example.myapplication.viewm_livedt.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActivityViewModel extends ViewModel {
    private int counter;

    private MutableLiveData<Integer> mLiveData = new MutableLiveData<>(counter);

    public MutableLiveData<Integer> getmLiveData() {
        return mLiveData;
    }

    public int getCounter() {
        return counter;
    }

    public void getIncreasedCounter() {
        mLiveData.postValue(++counter);
    }
}
