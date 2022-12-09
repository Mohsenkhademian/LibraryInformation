package mft.model.entity;

import com.google.gson.Gson;

public class Book {
    private int id;
    private String name;
    private String writer;

    private int count;

    public Book() {
    }

    public Book(String name, String writer, int count) {
        this.name = name;
        this.writer = writer;
        this.count = count;
    }

    public Book(int id, String name, String writer, int count) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getWriter() {
        return writer;
    }

    public Book setWriter(String writer) {
        this.writer = writer;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Book setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
