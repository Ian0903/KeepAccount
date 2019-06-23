package com.example.ian.keepaccount.presenter;

import com.example.ian.keepaccount.App;
import com.example.ian.keepaccount.contract.ChartPageContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.data.model.Account_;
import com.example.ian.keepaccount.data.source.local.ObjectBox;
import com.example.ian.keepaccount.utils.ChartUtils;
import com.example.ian.keepaccount.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;

public class ChartPagePresenter extends BasePresenter<ChartPageContract.View> implements ChartPageContract.Presenter {

    private Box<Account> box;

    public ChartPagePresenter(){
        box = ObjectBox.get().boxFor(Account.class);
    }

    @Override
    public void getPieChartData(Date date){

        Date startTime = DateUtil.getStartTime(date);
        Date endTime = DateUtil.getEndDate(date);

        String[] pieChartTypeList = box.query().between(Account_.recordTime,startTime,endTime)
                .equal(Account_.accountBook, App.getCurBook())
                .equal(Account_.costFirstType,"支出")
                .build()
                .property(Account_.costSecondType)
                .unique()
                .findStrings();

        List<SliceValue> values = new ArrayList<>();
        for (int i = 0;i<pieChartTypeList.length;i++){
            double money = box.query().between(Account_.recordTime,startTime,endTime)
                    .equal(Account_.accountBook,App.getCurBook())
                    .equal(Account_.costFirstType,"支出")
                    .equal(Account_.costSecondType,pieChartTypeList[i])
                    .build()
                    .property(Account_.money)
                    .sumDouble();
            values.add(new SliceValue((float) money,ChartUtils.pickColor()).setLabel(money + "(" + pieChartTypeList[i] + ")"));
        }

        getView().showPieChart(values);
    }

    @Override
    public void getColumnChartData(Date date) {
        Date startTime = DateUtil.getStartTime(date);
        Date endTime = DateUtil.getEndDate(date);

        String[] columnChartTypeList = box.query().between(Account_.recordTime,startTime,endTime)
                .equal(Account_.accountBook, App.getCurBook())
                .equal(Account_.costFirstType,"支出")
                .build()
                .property(Account_.costSecondType)
                .unique()
                .findStrings();

        List<SubcolumnValue> values = new ArrayList<>();
        for (int i = 0;i<columnChartTypeList.length;i++){
            double money = box.query().between(Account_.recordTime,startTime,endTime)
                    .equal(Account_.accountBook,App.getCurBook())
                    .equal(Account_.costFirstType,"支出")
                    .equal(Account_.costSecondType,columnChartTypeList[i])
                    .build()
                    .property(Account_.money)
                    .sumDouble();
            values.add(new SubcolumnValue((float) money,ChartUtils.pickColor()).setLabel(columnChartTypeList[i]));
        }
        List<Column> columns = new ArrayList<>();
        Column column = new Column(values);
        column.setHasLabels(true);
        columns.add(column);
        getView().showColumnChart(columns);
    }
}
