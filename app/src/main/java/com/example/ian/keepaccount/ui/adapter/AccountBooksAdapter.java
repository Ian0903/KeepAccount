package com.example.ian.keepaccount.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ian.keepaccount.App;
import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.data.model.AccountBook;
import com.example.ian.keepaccount.ui.ViewHolder.BaseViewHolder;
import com.example.ian.keepaccount.ui.listener.DeleteBookCallback;
import com.example.ian.keepaccount.utils.ACache;
import com.example.ian.keepaccount.utils.ToastUtils;

import butterknife.BindView;

public class AccountBooksAdapter extends BaseRecyclerViewAdapter<AccountBook, RecyclerView.ViewHolder> {
    private String curBook;
    private DeleteBookCallback callback;
    private Context context;

    public AccountBooksAdapter(String curBook) {
        this.curBook = curBook;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_account_book, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bind(data.get(position),position);
    }

    class ItemViewHolder extends BaseViewHolder<AccountBook> {
        @BindView(R.id.item_account_book_tv)
        TextView itemAccountBookTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void bind(AccountBook data, int position) {
            super.bind(data, position);
            if (curBook.equals(data.getBookName()))
                itemView.setBackground(context.getDrawable(R.drawable.shape_selected));
            else
                itemView.setBackground(context.getDrawable(R.drawable.bg_white));
            itemAccountBookTv.setText(data.getBookName());
            itemAccountBookTv.setOnClickListener(v -> {
                curBook = data.getBookName();
                App.setCurBook(curBook);
                ACache.get().put("account_book",curBook);
                ToastUtils.show("账本已切换为《" + curBook + "》");
                notifyDataSetChanged();
            });
            itemView.setOnLongClickListener(view -> {
                showAlertDialog(data,position);
                return false;
            });
        }
    }

    private void showAlertDialog(AccountBook data,int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("确认删除" + data.getBookName() + "?");
        builder.setPositiveButton("确认", (dialogInterface, i) -> {
            if (getData().size() == 1){
                ToastUtils.show("至少需要一个帐本");
            }else{
                callback.delete(data.getId(),data.getBookName());
                getData().remove(position);
                curBook = getData().get(0).getBookName();
                App.setCurBook(curBook);
                ACache.get().put("account_book",curBook);
                ToastUtils.show("账本已切换为《" + curBook + "》");
                notifyDataSetChanged();
            }
        })
              .setNegativeButton("取消", (dialogInterface, i) -> {

              })
                .create()
                .show();
    }

    public void setCallback(DeleteBookCallback callback){
        this.callback = callback;
    }
}
