package com.example.ian.keepaccount.contract;

public interface BillDetailContract {

    interface View extends BaseContract.View{
        void showDeleteSuccess();
    }

    interface Presenter extends BaseContract.Presenter<BillDetailContract.View>{
        void delete(long id);
    }
}
