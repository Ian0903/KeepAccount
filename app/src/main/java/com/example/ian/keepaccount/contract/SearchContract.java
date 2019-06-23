package com.example.ian.keepaccount.contract;

import com.example.ian.keepaccount.data.model.Account;

import java.util.List;

public interface SearchContract {

    interface View extends BaseContract.View{
        void showSearchList(List<Account> list);
    }

    interface Presenter extends BaseContract.Presenter<SearchContract.View>{
        void getSearchList(String keyword);
    }
}
