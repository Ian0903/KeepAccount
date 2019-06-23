package com.example.ian.keepaccount.ui.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.HomePageContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.presenter.HomePagePresenter;
import com.example.ian.keepaccount.ui.activity.AddBillActivity;
import com.example.ian.keepaccount.ui.activity.CalendarSearchActivity;
import com.example.ian.keepaccount.ui.activity.SearchActivity;
import com.example.ian.keepaccount.ui.adapter.MainRecentAdapter;
import com.example.ian.keepaccount.ui.listener.EndlessRecyclerOnScrollListener;
import com.example.ian.keepaccount.utils.ActivityUtil;
import com.example.ian.keepaccount.utils.DateUtil;
import com.example.ian.keepaccount.widget.NoLastDividerItemDecoration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    @BindView(R.id.main_date_select_tv)
    TextView mainDateSelectTv;
    @BindView(R.id.main_income_title_tv)
    TextView mainIncomeTitleTv;
    @BindView(R.id.main_income_tv)
    TextView mainIncomeTv;
    @BindView(R.id.main_outcome_title_tv)
    TextView mainOutcomeTitleTv;
    @BindView(R.id.main_outcome_tv)
    TextView mainOutcomeTv;
    @BindView(R.id.main_record_list_rv)
    RecyclerView mainRecordListRv;
    @BindView(R.id.main_empty_list_tv)
    TextView mainEmptyListTv;

    private MainRecentAdapter adapter;
    private Date currentDate;
    private HomePagePresenter presenter;

    public HomePageFragment() {
        // Required empty public constructor
    }



    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        setHasOptionsMenu(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mainRecordListRv.setLayoutManager(layoutManager);
        NoLastDividerItemDecoration itemDecoration = new NoLastDividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.recycler_divider));
        mainRecordListRv.addItemDecoration(itemDecoration);
        adapter = new MainRecentAdapter();
        mainRecordListRv.setAdapter(adapter);

        mainRecordListRv.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                adapter.setLoading();
                presenter.loadMoreRecentList(currentDate, 10);
            }
        });
        currentDate = new Date();
        mainDateSelectTv.setText(DateUtil.getYMTime(currentDate) + " ∨");
        presenter = new HomePagePresenter();
        presenter.attachView(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.initRecentList(currentDate);
        presenter.getOutInCome(currentDate);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_calendar) {
            ActivityUtil.startActivity(context, CalendarSearchActivity.class);
        } else if (id == R.id.action_search) {
            ActivityUtil.startActivity(context, SearchActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void showOutInCome(String inMoney, String outMoney, String month) {
        mainIncomeTv.setText(inMoney);
        mainOutcomeTv.setText(outMoney);
        mainIncomeTitleTv.setText(month + getString(R.string.income));
        mainOutcomeTitleTv.setText(month + getString(R.string.outcome));
    }

    @Override
    public void showRecentList(List<Account> list, boolean isLoadMore) {

        if (!isLoadMore && list.size() == 0) {
            mainRecordListRv.setVisibility(View.GONE);
            mainEmptyListTv.setVisibility(View.VISIBLE);
        } else if (!isLoadMore && list.size() != 0) {
            mainRecordListRv.setVisibility(View.VISIBLE);
            mainEmptyListTv.setVisibility(View.GONE);
            adapter.clear();
            adapter.setData(list);
        } else if (list.size() == 0) {
            adapter.setNoMoreHint();
        } else {
            adapter.appendItems(list);
        }
    }

    @OnClick({R.id.main_date_select_tv, R.id.add_account_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_date_select_tv:
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(context, new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int y, int m, int d, String dateDesc) {
                        calendar.set(Calendar.YEAR, y);
                        calendar.set(Calendar.MONTH, m - 1);
                        calendar.set(Calendar.DAY_OF_MONTH, d);
                        currentDate = calendar.getTime();
                        mainDateSelectTv.setText(DateUtil.getYMTime(currentDate) + " ∨");
                        presenter.getOutInCome(currentDate);
                        presenter.initRecentList(currentDate);
                        //adapter.resetFooter();
                    }
                }).textConfirm("确定") //text of confirm button
                        .textCancel("取消") //text of cancel button
                        .btnTextSize(16) // button text size
                        .viewTextSize(25) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                        .minYear(1990) //min year in loop
                        .maxYear(DateUtil.getCurYear() + 1) // max year in loop
                        .showDayMonthYear(false) // shows like dd mm yyyy (default is false)
                        .dateChose(year + "-" + month + "-" + day) // date chose when init popwindow
                        .build();

                pickerPopWin.dayLoopView.setVisibility(View.GONE);
                pickerPopWin.showPopWin((Activity) context);
                break;
            case R.id.add_account_fab:
                ActivityUtil.startActivity(context, AddBillActivity.class);
                break;
        }
    }
}
