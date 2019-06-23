package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.contract.BillDetailContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.data.source.local.ObjectBox;

public class BillDetailPresenter extends BasePresenter<BillDetailContract.View>
        implements BillDetailContract.Presenter {

    @Override
    public void delete(long id) {
        ObjectBox.get().boxFor(Account.class).remove(id);
        getView().showDeleteSuccess();
    }
}
