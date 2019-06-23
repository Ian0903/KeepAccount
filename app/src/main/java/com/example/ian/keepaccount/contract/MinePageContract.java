package com.example.ian.keepaccount.contract;

public interface MinePageContract {

    interface View extends BaseContract.View{
        void showSwitch(boolean isOpenRemind);
        void showTime(String remindTime);
    }

    interface Presenter extends BaseContract.Presenter<MinePageContract.View>{
        void getRemindTime();
        void getSwitchState();
        void setRemindTime(String remindTime);
        void setSwitch(boolean switchRemind);
        void closeRemind();
    }
}
