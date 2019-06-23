package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.App;
import com.example.ian.keepaccount.contract.HomePageContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.data.model.Account_;
import com.example.ian.keepaccount.data.source.local.ObjectBox;
import com.example.ian.keepaccount.utils.DateUtil;

import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class HomePagePresenter extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter{

    private Box<Account> box;
    public int index;

    public HomePagePresenter() {
        box = ObjectBox.get().boxFor(Account.class);
    }

    @Override
    public void getOutInCome(Date time) {

        Date startTime = DateUtil.getStartTime(time);
        Date endTime = DateUtil.getEndDate(time);

        double inMoney = box.query().between(Account_.recordTime, startTime, endTime)
                .equal(Account_.accountBook, App.getCurBook())
                .equal(Account_.costFirstType, "收入")
                .build()
                .property(Account_.money)
                .sumDouble();

        double outMoney = box.query().between(Account_.recordTime,startTime,endTime)
                .equal(Account_.accountBook, App.getCurBook())
                .and()
                .equal(Account_.costFirstType, "支出")
                .build()
                .property(Account_.money)
                .sumDouble();

        String month = String.valueOf(startTime.getMonth() + 1);
        getView().showOutInCome(String.valueOf(inMoney) + "元",
                String.valueOf(outMoney) + "元", month +"月");


    }

    @Override
    public void loadMoreRecentList(Date time, int count) {

        Date startTime = DateUtil.getStartTime(time);
        Date endTime = DateUtil.dateAddMonth(time);
        List<Account> list = box.query().between(Account_.recordTime, startTime, endTime)
                .equal(Account_.accountBook, App.getCurBook())
                .order(Account_.recordTime, QueryBuilder.DESCENDING)
                .build()
                .find(index,index + count);

        index = index + count;

        getView().showRecentList(list,true);

    }

    @Override
    public void initRecentList(Date time){
        Date startTime = DateUtil.getStartTime(time);
        Date endTime = DateUtil.getEndDate(time);
        List<Account> list = box.query().between(Account_.recordTime, startTime, endTime)
                .equal(Account_.accountBook, App.getCurBook())
                .order(Account_.recordTime, QueryBuilder.DESCENDING)
                .build()
                .find(0,10);
        index = 11;
        getView().showRecentList(list,false);
    }

}
