package com.example.ian.keepaccount.ui.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected Context context;

    public BaseViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this,itemView);
    }

    public void bind(T data,int position){}
}
