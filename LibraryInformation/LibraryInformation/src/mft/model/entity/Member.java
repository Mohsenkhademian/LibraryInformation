package mft.model.entity;

import com.google.gson.Gson;

public class Member {
    private int id;
    private String name;
    private String family;

    public Member(int id, String name, String family) {
        this.id = id;
        this.name = name;
        this.family = family;
    }

    public Member(String name, String family) {
        this.name = name;
        this.family = family;
    }

    public Member() {
    }

    public int getId() {
        return id;
    }

    public Member setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Member setFamily(String family) {
        this.family = family;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
