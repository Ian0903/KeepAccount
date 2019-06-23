package com.example.ian.keepaccount.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.utils.DisplayUtil;
import com.example.ian.keepaccount.contract.AddBillContract;
import com.example.ian.keepaccount.presenter.AddBillPresenter;
import com.example.ian.keepaccount.ui.adapter.AddBillViewAdapter;
import com.example.ian.keepaccount.ui.fragment.IncomeFragment;
import com.example.ian.keepaccount.ui.fragment.OutcomeFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddBillActivity extends BaseActivity<AddBillPresenter> implements AddBillContract.View {

    @BindView(R.id.type_indicator)
    ScrollIndicatorView typeIndicator;
    @BindView(R.id.type_viewPager)
    ViewPager typeViewPager;

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void initPresenter() {
        presenter = new AddBillPresenter();
        presenter.attachView(this);
    }

    private void initView(){
        setTitle("记账页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        typeIndicator.setScrollBar(new DrawableBar(this, R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - DisplayUtil.dipToPix(AddBillActivity.this,12);
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - DisplayUtil.dipToPix(AddBillActivity.this,12);
            }
        });

        typeIndicator.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(Color.BLACK,Color.GRAY));
        typeIndicator.setSplitAuto(true);

        typeViewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(typeIndicator,typeViewPager);
        indicatorViewPager.setAdapter(new AddBillViewAdapter(getSupportFragmentManager(),createFragments()));

    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_bill;
    }

    private List<Fragment> createFragments(){
        List<Fragment> fragments = new ArrayList<>();
        OutcomeFragment outcomeFragment = new OutcomeFragment();
        IncomeFragment incomeFragment = new IncomeFragment();

        fragments.add(outcomeFragment);
        fragments.add(incomeFragment);
        return fragments;
    }
}
