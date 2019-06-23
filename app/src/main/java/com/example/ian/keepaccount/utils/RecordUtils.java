package com.example.ian.keepaccount.utils;

import com.example.ian.keepaccount.App;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.data.source.local.ObjectBox;

import java.util.Date;

public class RecordUtils {

    public static void add(double money, Date time,String fType,String sType,String remark){
        Account account = new Account();
        account.setMoney(money);
        account.setAccountBook(App.getCurBook());
        account.setCostFirstType(fType);
        account.setCostSecondType(sType);
        account.setRecordTime(time);
        account.setRemark(remark);
        ObjectBox.get().boxFor(Account.class).put(account);
    }
}
