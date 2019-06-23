package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.App;
import com.example.ian.keepaccount.contract.MainContract;
import com.example.ian.keepaccount.data.model.AccountBook;
import com.example.ian.keepaccount.data.source.local.ObjectBox;
import com.example.ian.keepaccount.utils.ACache;

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter{


    @Override
    public void initAccountBook() {
        String book = ACache.get().getAsString("account_book");
        if (book == null){
            App.setCurBook("默认账本");
            AccountBook accountBook = new AccountBook();
            accountBook.setBookName("默认账本");
            ObjectBox.get().boxFor(AccountBook.class).put(accountBook);
            ACache.get().put("account_book","默认账本");
        }else{
            App.setCurBook(book);
        }
    }
}
