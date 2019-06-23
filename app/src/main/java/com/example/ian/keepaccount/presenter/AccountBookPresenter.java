package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.contract.AccountBookContract;
import com.example.ian.keepaccount.data.model.AccountBook;
import com.example.ian.keepaccount.data.source.local.ObjectBox;

import java.util.List;

public class AccountBookPresenter extends BasePresenter<AccountBookContract.View> implements AccountBookContract.Presenter {

    private List<AccountBook> list;

    @Override
    public void getAccountBooks() {
        list = ObjectBox.get().boxFor(AccountBook.class)
        .query()
        .build()
        .find();

        getView().showAccountBooks(list);
    }

    @Override
    public void addAccountBook(String bookName) {
        AccountBook addBook = new AccountBook();
        addBook.setBookName(bookName);
        ObjectBox.get().boxFor(AccountBook.class)
                .put(addBook);
    }

    @Override
    public void deleteBook(long id, String bookName) {
        ObjectBox.get().boxFor(AccountBook.class)
                .remove(id);
    }
}
