package com.example.ian.keepaccount.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.data.model.AccountBook;
import com.example.ian.keepaccount.data.source.local.ObjectBox;
import com.example.ian.keepaccount.ui.listener.CallBackListener;
import com.example.ian.keepaccount.utils.ToastUtils;
import com.zyyoona7.popup.BasePopup;

public class AddBookPopup extends BasePopup<AddBookPopup> {

    private EditText nameEt;
    private Button confirmBtn;
    private Button cancelBtn;

    private String bookName;
    private CallBackListener listener;

    public static AddBookPopup create(Context context) {
        return new AddBookPopup(context);
    }

    protected AddBookPopup(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.add_book_popup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void initViews(View view, AddBookPopup addBookPopup) {
        nameEt = findViewById(R.id.add_book_name_tv);
        cancelBtn = findViewById(R.id.cancel_btn);
        confirmBtn = findViewById(R.id.confirm_btn);

        confirmBtn.setOnClickListener(v -> {
            bookName = nameEt.getText().toString();
            if (!bookName.equals("")){
                AccountBook book = new AccountBook();
                book.setBookName(bookName);
                ObjectBox.get().boxFor(AccountBook.class).put(book);
                listener.onResult("success");
                dismiss();
            }else{
                ToastUtils.show("请填写账本名称");
            }
        });

        cancelBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    public void setListener(CallBackListener listener){
        this.listener = listener;
    }
}
