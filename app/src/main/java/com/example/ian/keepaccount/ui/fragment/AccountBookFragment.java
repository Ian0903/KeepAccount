package com.example.ian.keepaccount.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.AccountBookContract;
import com.example.ian.keepaccount.data.model.AccountBook;
import com.example.ian.keepaccount.presenter.AccountBookPresenter;
import com.example.ian.keepaccount.ui.adapter.AccountBooksAdapter;
import com.example.ian.keepaccount.ui.listener.CallBackListener;
import com.example.ian.keepaccount.ui.listener.DeleteBookCallback;
import com.example.ian.keepaccount.utils.ACache;
import com.example.ian.keepaccount.widget.AddBookPopup;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountBookFragment extends BaseFragment implements AccountBookContract.View,CallBackListener,DeleteBookCallback {


    @BindView(R.id.account_book_list_rv)
    RecyclerView accountBookListRv;

    private AccountBookPresenter presenter;
    private AccountBooksAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_account_book;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getAccountBooks();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_account_book,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            AddBookPopup addBookPopup = AddBookPopup.create(getContext()).apply();
            addBookPopup.setListener(this);
            addBookPopup.showAtLocation(getView(),Gravity.BOTTOM,0,0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        setHasOptionsMenu(true);
        presenter = new AccountBookPresenter();
        presenter.attachView(this);
        adapter = new AccountBooksAdapter(ACache.get().getAsString("account_book"));
        adapter.setCallback(this);
        accountBookListRv.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        accountBookListRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        accountBookListRv.setAdapter(adapter);
    }

    @Override
    public void showAccountBooks(List<AccountBook> list) {
        adapter.setData(list);
    }

    @Override
    public void onResult(Object result) {
        String res = (String) result;
        if (res.equals("success")){
            presenter.getAccountBooks();
        }
    }

    @Override
    public void delete(long id, String book) {
        presenter.deleteBook(id,book);
    }
}
