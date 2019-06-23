package com.example.ian.keepaccount.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.ian.keepaccount.contract.BaseContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseContract.View {


    protected Context context;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onFragmentViewCreated();
        return view;
    }

    public abstract int getLayout();

    public void onFragmentViewCreated(){
    }

    @Override
    public void showError(String message) {
        Log.e(getClass().getName(), message);
        Toast.makeText(getActivity(), "出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
