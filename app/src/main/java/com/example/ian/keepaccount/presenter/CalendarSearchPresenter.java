package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.contract.CalendarSearchContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.data.model.Account_;
import com.example.ian.keepaccount.data.source.local.ObjectBox;
import com.example.ian.keepaccount.utils.DateUtil;

import java.util.Date;
import java.util.List;

public class CalendarSearchPresenter extends BasePresenter<CalendarSearchContract.View>
        implements CalendarSearchContract.Presenter {

    @Override
    public void getSearchList(String date) {
        Date start = DateUtil.getStartDay(date);
        Date end = DateUtil.dateAddDay(start);
        List<Account> list = ObjectBox.get().boxFor(Account.class)
                .query()
                .between(Account_.recordTime,start,end)
                .build()
                .find();
        getView().showSearchList(list);
    }
}
