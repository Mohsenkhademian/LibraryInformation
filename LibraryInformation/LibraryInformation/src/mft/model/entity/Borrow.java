package mft.model.entity;

import com.google.gson.Gson;

import java.time.LocalDateTime;

public class Borrow {

    private int id;
    private Member member;
    private Book book;
    private LocalDateTime borromTime;
    private boolean returned;

    public Borrow() {
    }

    public Borrow(int id, Member member, Book book, LocalDateTime borromTime, boolean returned) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.borromTime = borromTime;
        this.returned = returned;
    }

    public Borrow(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public Borrow setId(int id) {
        this.id = id;
        return this;
    }

    public Member getMember() {
        return member;
    }

    public Borrow setMember(Member member) {
        this.member = member;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public Borrow setBook(Book book) {
        this.book = book;
        return this;
    }

    public LocalDateTime getBorromTime() {
        return borromTime;
    }

    public Borrow setBorromTime(LocalDateTime borromTime) {
        this.borromTime = borromTime;
        return this;
    }

    public boolean isReturned() {
        return returned;
    }

    public Borrow setReturned(boolean returned) {
        this.returned = returned;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
