package com.example.ian.keepaccount.data.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class AccountBook {

    @Id
    public long id;

    String bookName;        //帐本名称

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
