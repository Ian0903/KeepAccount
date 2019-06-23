package com.example.ian.keepaccount.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.CalendarSearchContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.presenter.CalendarSearchPresenter;
import com.example.ian.keepaccount.ui.adapter.MainRecentAdapter;
import com.example.ian.keepaccount.utils.DateUtil;
import com.example.ian.keepaccount.utils.ToastUtils;
import com.example.ian.keepaccount.widget.NoLastDividerItemDecoration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

public class CalendarSearchActivity extends BaseActivity<CalendarSearchPresenter>
        implements CalendarSearchContract.View {

    @BindView(R.id.calender_search_rv)
    RecyclerView calenderSearchRv;
    @BindView(R.id.calender_search_dp)
    DatePicker calenderSearchDp;

    private MainRecentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setTitle("日历搜索页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Calendar calendar = Calendar.getInstance();
        calenderSearchDp.setDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) +1 );
        calenderSearchDp.setMode(DPMode.SINGLE);
        calenderSearchDp.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                presenter.getSearchList(date);
            }
        });

        adapter = new MainRecentAdapter();
        calenderSearchRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        calenderSearchRv.addItemDecoration(new NoLastDividerItemDecoration(this,NoLastDividerItemDecoration.VERTICAL));
        calenderSearchRv.setAdapter(adapter);

        presenter.getSearchList(DateUtil.getStringDate2(new Date()));

    }

    @Override
    public void initPresenter() {
        presenter = new CalendarSearchPresenter();
        presenter.attachView(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_calendar_search;
    }

    @Override
    public void showSearchList(List<Account> list) {
        adapter.clear();
        if (list.size() == 0){
            ToastUtils.show("该日期无相关账单记录");
        }else{
            adapter.setData(list);
        }
    }
}
