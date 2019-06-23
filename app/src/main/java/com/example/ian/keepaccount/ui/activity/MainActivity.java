package com.example.ian.keepaccount.ui.activity;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.MainContract;
import com.example.ian.keepaccount.presenter.MainPresenter;
import com.example.ian.keepaccount.ui.fragment.AccountBookFragment;
import com.example.ian.keepaccount.ui.fragment.BaseFragment;
import com.example.ian.keepaccount.ui.fragment.ChartPageFragment;
import com.example.ian.keepaccount.ui.fragment.HomePageFragment;
import com.example.ian.keepaccount.ui.fragment.MinePageFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void initPresenter() {
        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.initAccountBook();
    }


    private void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        initFragment("home");
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initFragment(String type){
        BaseFragment fragment;
        switch (type){
            case "home":
                fragment = new HomePageFragment();
                setTitle("极简记账");
                break;
            case "account_book":
                fragment = new AccountBookFragment();
                setTitle("账本页");
                break;
            case  "chart":
                fragment = new ChartPageFragment();
                setTitle("图表统计页");
                break;
            case "mine":
                fragment = new MinePageFragment();
                setTitle("我的页");
                break;
                default:
                    fragment = new HomePageFragment();
        }
        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment, fragment).commit();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_home:
                initFragment("home");
                break;
            case R.id.nav_book:
                initFragment("account_book");
                break;
            case R.id.nav_chart:
                initFragment("chart");
                break;
            case R.id.nav_mine:
                initFragment("mine");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
