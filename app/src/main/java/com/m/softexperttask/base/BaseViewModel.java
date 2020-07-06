package com.m.softexperttask.base;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;



public class BaseViewModel extends BaseObservable {
    private MutableLiveData<Integer> clicksMutableLiveData;

    public BaseViewModel() {

    }

    public void accessLoadingBar(int visiablity) {
        getClicksMutableLiveData().setValue(visiablity);
    }

    public MutableLiveData<Integer> getClicksMutableLiveData() {
        if (clicksMutableLiveData == null) clicksMutableLiveData = new MutableLiveData<>();
        return clicksMutableLiveData;
    }

}
