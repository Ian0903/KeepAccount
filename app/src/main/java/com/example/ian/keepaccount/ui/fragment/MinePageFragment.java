package com.example.ian.keepaccount.ui.fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.MinePageContract;
import com.example.ian.keepaccount.presenter.MinePagePresenter;
import com.example.ian.keepaccount.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinePageFragment extends BaseFragment implements MinePageContract.View {

    @BindView(R.id.mine_remind_sw)
    Switch mineRemindSw;
    @BindView(R.id.mine_remind_time_set_tv)
    TextView mineRemindTimeSetTv;
    @BindView(R.id.mine_version_tv)
    TextView mineAboutTv;
    @BindView(R.id.mine_remind_time_tv)
    TextView mineRemindTimeTv;

    private MinePagePresenter presenter;
    private boolean isSetRemind;

    @Override
    public int getLayout() {
        return R.layout.fragment_mine_page;
    }

    @Override
    public void showSwitch(boolean isOpenRemind) {
        isSetRemind = isOpenRemind;
        mineRemindSw.setChecked(isOpenRemind);
        if (isSetRemind){
            mineRemindTimeTv.setVisibility(View.VISIBLE);
            mineRemindTimeSetTv.setVisibility(View.VISIBLE);
            presenter.getRemindTime();
        }
    }

    @Override
    public void showTime(String remindTime) {
        mineRemindTimeSetTv.setText(remindTime);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        presenter = new MinePagePresenter(getContext());
        presenter.attachView(this);
        presenter.getSwitchState();
    }

    @OnClick({R.id.mine_remind_time_set_tv, R.id.mine_version_tv, R.id.mine_remind_sw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_remind_time_set_tv:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String remindTime;
                        if (i1 < 10)
                            remindTime = i + ":0" + i1;
                        else
                            remindTime = i + ":" + i1;
                        mineRemindTimeSetTv.setText(remindTime);
                        presenter.setRemindTime(remindTime);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
                break;
            case R.id.mine_version_tv:
                ToastUtils.show("该版本已经是最新版本");
                break;
            case R.id.mine_remind_sw:
                isSetRemind = !isSetRemind;
                presenter.setSwitch(isSetRemind);
                if (isSetRemind) {
                    mineRemindTimeTv.setVisibility(View.VISIBLE);
                    mineRemindTimeSetTv.setVisibility(View.VISIBLE);
                }else{
                    mineRemindTimeTv.setVisibility(View.GONE);
                    mineRemindTimeSetTv.setVisibility(View.GONE);
                    presenter.closeRemind();
                }
                break;
        }
    }

}
