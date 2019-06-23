package com.example.ian.keepaccount.contract;

import com.example.ian.keepaccount.data.model.AccountBook;

import java.util.List;

public interface AccountBookContract extends BaseContract {

    interface View extends BaseContract.View{
        void showAccountBooks(List<AccountBook> list);
    }

    interface Presenter extends BaseContract.Presenter<AccountBookContract.View>{
        void getAccountBooks();
        void addAccountBook(String bookName);
        void deleteBook(long id,String bookName);
    }
}
