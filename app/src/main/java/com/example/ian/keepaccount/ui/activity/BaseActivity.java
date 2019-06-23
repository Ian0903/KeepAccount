package com.example.ian.keepaccount.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.contract.BaseContract;
import com.example.ian.keepaccount.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseContract.View {

    protected String TAG = this.getClass().getSimpleName();
    protected T presenter;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(R.id.appbar_layout)
    protected AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        initPresenter();
    }

    public abstract void initPresenter();

    public abstract int getLayout();

    @Override
    public void showError(String message) {
        Log.e(getClass().getName(), message);
        Toast.makeText(this, "出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        ButterKnife.bind(this).unbind();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();

    }


}
