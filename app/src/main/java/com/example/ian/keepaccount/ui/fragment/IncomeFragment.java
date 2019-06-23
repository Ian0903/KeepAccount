package com.example.ian.keepaccount.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.data.Constant;
import com.example.ian.keepaccount.ui.adapter.PageRecyclerAdapter;
import com.example.ian.keepaccount.widget.GridItemDecoration;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends BaseFragment {

    @BindView(R.id.income_type_list_rv)
    RecyclerView incomeTypeListRv;
    private PageRecyclerAdapter adapter;

    @Override
    public void onFragmentViewCreated() {
        incomeTypeListRv.setLayoutManager(new GridLayoutManager(getActivity(),4));
        incomeTypeListRv.addItemDecoration(new GridItemDecoration(4));
        adapter = new PageRecyclerAdapter(Constant.INCOME_STRINGS,Constant.INCOME_IMAGES,"收入");
        incomeTypeListRv.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_income;
    }

}
