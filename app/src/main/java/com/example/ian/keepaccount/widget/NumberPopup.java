package com.example.ian.keepaccount.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.utils.DateUtil;
import com.example.ian.keepaccount.utils.RecordUtils;
import com.example.ian.keepaccount.utils.ToastUtils;
import com.zyyoona7.popup.BasePopup;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberPopup extends BasePopup<NumberPopup> {

    EditText numberPopupMoneyEt;
    EditText numberRemarkEt;
    TextView numberPopupTimeTv;
    Button numberPopupFinishBtn;

    private Context context;
    private String recordFirstType;
    private String recordSecondType;
    private boolean isCurrent = true;

    public static NumberPopup create(Context context) {
        return new NumberPopup(context);
    }

    protected NumberPopup(Context context) {
        this.context = context;
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.number_popup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void initViews(View view, NumberPopup numberPopup) {
        numberPopupMoneyEt = findViewById(R.id.number_popup_money_et);
        numberPopupTimeTv = findViewById(R.id.number_popup_time_tv);
        numberRemarkEt = findViewById(R.id.number_remark_et);
        numberPopupFinishBtn = findViewById(R.id.number_popup_finish_btn);

        numberPopupTimeTv.setText("今天");
        numberPopupTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatePickerDialog();
            }
        });

        numberPopupFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecord();
            }
        });
    }

    private void addRecord() {
        if (checkMoneyIsLegal()) {
            Date time = isCurrent ? new Date() : DateUtil.strToDate(numberPopupTimeTv.getText().toString());
            RecordUtils.add(Double.valueOf(numberPopupMoneyEt.getText().toString()),
                    time,
                    recordFirstType,
                    recordSecondType,
                    numberRemarkEt.getText().toString());
            ToastUtils.show("记录成功");
            dismiss();
        } else {
            ToastUtils.show("请正确完整的金额");
        }
    }

    private boolean checkMoneyIsLegal(){
        String money = numberPopupMoneyEt.getText().toString();
        if (money.length() == 0)
            return false;

        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(money);
        if(!match.matches()){
            return false;
        }
        else{
            return true;
        }
    }

    private void createDatePickerDialog() {
        Calendar ca = Calendar.getInstance();
        int mYear = ca.get(Calendar.YEAR);
        int mMonth = ca.get(Calendar.MONTH);
        int mDay = ca.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        numberPopupTimeTv.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                        isCurrent = false;
                    }
                },
                mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void setRecordFirstType(String type) {
        this.recordFirstType = type;
    }

    public void setRecordSecondType(String type) {
        this.recordSecondType = type;
    }


}
