package com.example.ian.keepaccount.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.ui.activity.BillDetailActivity;
import com.example.ian.keepaccount.utils.ActivityUtil;
import com.example.ian.keepaccount.utils.DateUtil;
import com.example.ian.keepaccount.data.model.Account;
import com.example.ian.keepaccount.ui.ViewHolder.BaseViewHolder;
import com.example.ian.keepaccount.utils.DisplayUtil;

import butterknife.BindView;

public class MainRecentAdapter extends BaseRecyclerViewAdapter<Account, RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    private FooterViewHolder footerViewHolder;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                viewHolder = new ItemViewHolder(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.main_recent_list_item, parent, false));
                break;
            case VIEW_TYPE_LOADING:
                viewHolder = new FooterViewHolder(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.recycler_footer, parent, false));
                footerViewHolder = (FooterViewHolder) viewHolder;
                break;
            default:
                viewHolder = new ItemViewHolder(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.main_recent_list_item, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(data.get(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data != null && position == data.size()) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public void resetFooter() {
        footerViewHolder.loadMoreProgressBar.setVisibility(View.GONE);
        footerViewHolder.noMoreHint.setVisibility(View.GONE);
    }

    public void setLoading() {
        footerViewHolder.loadMoreProgressBar.setVisibility(View.VISIBLE);
        footerViewHolder.noMoreHint.setVisibility(View.GONE);
    }

    public void setNoMoreHint() {
        footerViewHolder.loadMoreProgressBar.setVisibility(View.GONE);
        footerViewHolder.noMoreHint.setVisibility(View.VISIBLE);
    }


    class ItemViewHolder extends BaseViewHolder<Account> {
        @BindView(R.id.list_icon_iv)
        ImageView listIconIv;
        @BindView(R.id.list_tag_tv)
        TextView listTagTv;
        @BindView(R.id.list_time_tv)
        TextView listTimeTv;
        @BindView(R.id.list_money_tv)
        TextView listMoneyTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final Account data, int position) {
            listTagTv.setText(data.getCostSecondType());
            listTimeTv.setText(DateUtil.getStringDate(data.getRecordTime()));
            if (data.getCostFirstType().equals("支出"))
                listMoneyTv.setText(String.valueOf("-" + data.getMoney()));
            else
                listMoneyTv.setText(String.valueOf("+" + data.getMoney()) );
            //set listIcon
            Glide.with(context).load(DisplayUtil.getSecondTypeIcon(data.getCostSecondType())).into(listIconIv);
            //set listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityUtil.startActivityWithAccount(context,BillDetailActivity.class,data);
                }
            });
        }
    }

    class FooterViewHolder extends BaseViewHolder {
        @BindView(R.id.load_more_progress_bar)
        ProgressBar loadMoreProgressBar;
        @BindView(R.id.no_more_hint)
        TextView noMoreHint;

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
