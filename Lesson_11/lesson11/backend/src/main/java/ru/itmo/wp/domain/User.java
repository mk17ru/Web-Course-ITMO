package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-zA-Z]{2,24}")
    private String login;

    private boolean admin;

    @CreationTimestamp
    private Date creationTime;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }
}
