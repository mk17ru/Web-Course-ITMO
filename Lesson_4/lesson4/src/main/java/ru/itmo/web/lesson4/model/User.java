package ru.itmo.web.lesson4.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public enum Color{RED, GREEN, BLUE};
    private final long id;
    private final String handle;
    private final String name;
    private final Color color;
    private final List<Long> posts;

    public User(long id, String handle, String name, Color color) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.posts = new ArrayList<>();
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void addPost(Long p) {
        posts.add(p);
    }

}
