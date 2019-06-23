package com.example.ian.keepaccount.contract;

import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.SliceValue;

public interface ChartPageContract {

    interface View extends BaseContract.View{
        void showPieChart(List<SliceValue> list);
        void showColumnChart(List<Column> list);
    }

    interface Presenter extends BaseContract.Presenter<ChartPageContract.View>{
        void getPieChartData(Date date);
        void getColumnChartData(Date date);
    }
}
