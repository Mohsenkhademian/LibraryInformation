package mft.model.entity;

import com.google.gson.Gson;

public class BorrowView {
    private int id;
    private String member;
    private String book;
    private String timeStamp;
    private String returned;

    public BorrowView() {
    }

    public BorrowView(int id, String member, String book, String timeStamp, String returned) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.timeStamp = timeStamp;
        this.returned = returned;
    }

    public BorrowView(String member, String book, String timeStamp, String returned) {
        this.member = member;
        this.book = book;
        this.timeStamp = timeStamp;
        this.returned = returned;
    }

    public int getId() {
        return id;
    }

    public BorrowView setId(int id) {
        this.id = id;
        return this;
    }

    public String getMember() {
        return member;
    }

    public BorrowView setMember(String member) {
        this.member = member;
        return this;
    }

    public String getBook() {
        return book;
    }

    public BorrowView setBook(String book) {
        this.book = book;
        return this;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public BorrowView setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getReturned() {
        return returned;
    }

    public BorrowView setReturned(String returned) {
        this.returned = returned;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
