package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.contract.BaseContract;

public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V>  {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public V getView(){
        return view;
    }
}
