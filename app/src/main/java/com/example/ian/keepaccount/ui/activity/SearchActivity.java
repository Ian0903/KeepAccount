package com.example.ian.keepaccount.ui.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.SearchContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.presenter.SearchPresenter;
import com.example.ian.keepaccount.ui.adapter.MainRecentAdapter;
import com.example.ian.keepaccount.utils.ToastUtils;
import com.example.ian.keepaccount.widget.NoLastDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter>
        implements SearchContract.View {

    @BindView(R.id.search_list_rv)
    RecyclerView searchListRv;
    private SearchView searchView;
    private MainRecentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        adapter = new MainRecentAdapter();
        searchListRv.addItemDecoration(new NoLastDividerItemDecoration(this,NoLastDividerItemDecoration.VERTICAL));
        searchListRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        searchListRv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //通过MenuItem得到SearchView
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("请输入关键字");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getSearchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void initPresenter() {
        presenter = new SearchPresenter();
        presenter.attachView(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void showSearchList(List<Account> list) {
        adapter.clear();
        if (list.size() == 0)
            ToastUtils.show("无相关记录");
        else{
            adapter.setData(list);
        }
    }
}
