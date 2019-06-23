package com.example.ian.keepaccount.ui.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.ChartPageContract;
import com.example.ian.keepaccount.presenter.ChartPagePresenter;
import com.example.ian.keepaccount.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartPageFragment extends BaseFragment implements ChartPageContract.View {


    @BindView(R.id.main_date_select_tv)
    TextView mainDateSelectTv;
    @BindView(R.id.pie_chart_view)
    PieChartView pieChartView;
    @BindView(R.id.column_chart_view)
    ColumnChartView columnChartView;

    private Date currentDate;
    private ChartPagePresenter presenter;
    private PieChartData chartData;
    private ColumnChartData columnChartData;
    private int CHART_TYPE = 1;
    public ChartPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        currentDate = new Date();
        mainDateSelectTv.setText(DateUtil.getYMTime(currentDate) + " ∨");
        presenter = new ChartPagePresenter();
        presenter.attachView(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_chart_page;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPieChartData(currentDate);
    }


    @Override
    public void showPieChart(List<SliceValue> list) {
        columnChartView.setVisibility(View.GONE);
        pieChartView.setVisibility(View.VISIBLE);
        chartData = new PieChartData(list);
        chartData.setHasLabels(true);
        pieChartView.setPieChartData(chartData);
    }

    @Override
    public void showColumnChart(List<Column> list) {
        pieChartView.setVisibility(View.GONE);
        columnChartView.setVisibility(View.VISIBLE);
        columnChartData = new ColumnChartData(list);
        Axis axisY = new Axis().setHasLines(true);
        axisY.setName("金额");
        columnChartData.setAxisYLeft(axisY);
        columnChartView.setColumnChartData(columnChartData);
    }

    @OnClick({R.id.main_date_select_tv, R.id.select_pie_btn, R.id.select_column_btn})
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
                        if (CHART_TYPE == 1)
                            presenter.getPieChartData(currentDate);
                        else
                            presenter.getColumnChartData(currentDate);
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
            case R.id.select_pie_btn:
                CHART_TYPE = 1;
                presenter.getPieChartData(currentDate);
                break;
            case R.id.select_column_btn:
                CHART_TYPE = 2;
                presenter.getColumnChartData(currentDate);
                break;
        }
    }

}
