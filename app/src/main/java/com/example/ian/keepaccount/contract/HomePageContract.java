package com.example.ian.keepaccount.contract;

import com.example.ian.keepaccount.data.model.Account;

import java.util.Date;
import java.util.List;

public interface HomePageContract {

    interface View extends BaseContract.View{
        void showOutInCome(String inMoney,String outMoney,String month);
        void showRecentList(List<Account> list, boolean isLoadMore);
    }

    interface Presenter extends BaseContract.Presenter<HomePageContract.View>{
        void getOutInCome(Date date);
        void loadMoreRecentList(Date date, int count);

        void initRecentList(Date date);
    }
}
