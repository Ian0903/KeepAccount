package com.example.ian.keepaccount.contract;

public interface MainContract {

    interface View extends BaseContract.View{
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View>{
        void initAccountBook();
    }
}
