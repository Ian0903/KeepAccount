package com.example.ian.keepaccount.contract;

import com.example.ian.keepaccount.data.model.Account;

import java.util.List;

public interface CalendarSearchContract {

    interface View extends BaseContract.View{
        void showSearchList(List<Account> list);
    }

    interface Presenter extends BaseContract.Presenter<CalendarSearchContract.View>{
        void getSearchList(String date);
    }
}
