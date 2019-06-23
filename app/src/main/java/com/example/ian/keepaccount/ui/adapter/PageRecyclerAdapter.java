package com.example.ian.keepaccount.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.ui.ViewHolder.BaseViewHolder;
import com.example.ian.keepaccount.widget.NumberPopup;

import butterknife.BindView;

public class PageRecyclerAdapter extends RecyclerView.Adapter<PageRecyclerAdapter.ItemViewHolder> {


    private String[] data;
    private int[] imageData;
    private String type;

    public PageRecyclerAdapter(String[] data,int[] imageData,String type){
        this.data = data;
        this.imageData = imageData;
        this.type = type;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_page_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(data, position);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class ItemViewHolder extends BaseViewHolder<String[]> {

        @BindView(R.id.item_page_recycler_iv)
        ImageView itemPageRecyclerIv;
        @BindView(R.id.item_page_recycler_tv)
        TextView itemPageRecyclerTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final String[] data, final int position) {
            super.bind(data, position);
            Glide.with(context).load(imageData[position]).into(itemPageRecyclerIv);
            itemPageRecyclerTv.setText(data[position]);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NumberPopup popup = NumberPopup.create(itemView.getContext()).apply();
                    popup.setRecordFirstType(type);
                    popup.setRecordSecondType(data[position]);
                    popup.showAtLocation(itemView.getRootView(),Gravity.BOTTOM,0,0);
                }
            });
        }
    }
}
