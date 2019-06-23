package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.contract.SearchContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.data.model.Account_;
import com.example.ian.keepaccount.data.source.local.ObjectBox;

import java.util.List;

public class SearchPresenter extends BasePresenter<SearchContract.View>
        implements SearchContract.Presenter {

    @Override
    public void getSearchList(String keyword) {
        List<Account> list = ObjectBox.get().boxFor(Account.class)
                .query()
                .contains(Account_.remark,keyword)
                .build()
                .find();
        getView().showSearchList(list);
    }
}
