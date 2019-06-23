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
public class OutcomeFragment extends BaseFragment {

    @BindView(R.id.outcome_type_list_rv)
    RecyclerView outcomeTypeListRv;

    private PageRecyclerAdapter adapter;

    @Override
    public void onFragmentViewCreated() {
        outcomeTypeListRv.setLayoutManager(new GridLayoutManager(getActivity(),4));
        outcomeTypeListRv.addItemDecoration(new GridItemDecoration(4));
        adapter = new PageRecyclerAdapter(Constant.OUTCOME_STRINGS,Constant.OUTCOME_IMAGES,"支出");
        outcomeTypeListRv.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_outcome;
    }

}
