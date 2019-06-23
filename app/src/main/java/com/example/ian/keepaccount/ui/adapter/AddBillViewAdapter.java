package com.example.ian.keepaccount.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.utils.DisplayUtil;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.List;

public class AddBillViewAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] names = {"支出","收入"};

    public AddBillViewAdapter(FragmentManager fragmentManager,List<Fragment> list) {
        super(fragmentManager);
        fragments = list;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.tab_top,container,false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(names[position]);
        int padding = DisplayUtil.dipToPix(container.getContext(),10);
        textView.setPadding(padding, 0, padding, 0);
        return convertView;

    }

    @Override
    public Fragment getFragmentForPage(int position) {
        return fragments.get(position);
    }
}
