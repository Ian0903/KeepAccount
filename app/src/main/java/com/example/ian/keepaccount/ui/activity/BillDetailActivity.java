package com.example.ian.keepaccount.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.BillDetailContract;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.presenter.BillDetailPresenter;
import com.example.ian.keepaccount.utils.DateUtil;
import com.example.ian.keepaccount.utils.DisplayUtil;
import com.example.ian.keepaccount.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class BillDetailActivity extends BaseActivity<BillDetailPresenter>
        implements BillDetailContract.View {

    @BindView(R.id.bill_detail_iv)
    ImageView billDetailIv;
    @BindView(R.id.bill_detail_stype_tv)
    TextView billDetailStypeTv;
    @BindView(R.id.bill_detail_money_tv)
    TextView billDetailMoneyTv;
    @BindView(R.id.bill_detail_ftype_tv)
    TextView billDetailFtypeTv;
    @BindView(R.id.bill_detail_time_tv)
    TextView billDetailTimeTv;
    @BindView(R.id.bill_detail_book_tv)
    TextView billDetailBookTv;
    @BindView(R.id.bill_detail_remark_tv)
    TextView billDetailRemarkTv;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = (Account) getIntent().getSerializableExtra("key_account");
        initView();

    }

    private void initView() {
        setTitle("详情页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Glide.with(this).load(DisplayUtil.getSecondTypeIcon(account.getCostSecondType())).into(billDetailIv);
        billDetailStypeTv.setText(account.getCostSecondType());
        billDetailMoneyTv.setText(String.valueOf(account.getMoney()));
        billDetailFtypeTv.setText(account.getCostFirstType());
        billDetailTimeTv.setText(DateUtil.getStringDate(account.getRecordTime()));
        billDetailBookTv.setText(account.getAccountBook());
        if (account.getRemark() != null){
            billDetailRemarkTv.setText(account.getRemark());
        }
    }

    @Override
    public void initPresenter() {
        presenter = new BillDetailPresenter();
        presenter.attachView(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bill_detail;
    }

    @Override
    public void showDeleteSuccess() {
        ToastUtils.show("删除账单成功!");
        finish();
    }

    @OnClick(R.id.bill_detail_delete_btn)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确定删除？")
                .setMessage("是否确认删除该笔账单？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.delete(account.getId());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}
