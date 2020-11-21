package ru.itmo.web.lesson4.model;

import java.util.Objects;

public class Post implements Comparable<Post> {
    private final long id;
    private final String title;
    private final String text;
    private final long user_id;

    public Post(long id, String title, String text, long user_id) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public long getUser_id() {
        return user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                user_id == post.user_id &&
                Objects.equals(title, post.title) &&
                Objects.equals(text, post.text);
    }

    @Override
    public int compareTo(Post o) {
        return Long.compare(o.getId(), this.id);
    }
}
