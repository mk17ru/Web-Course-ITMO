package ru.itmo.wp.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Tag implements Comparable<Tag>{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    /** @noinspection unused*/
    public Tag() {
    }

    public Tag(@NotNull String name) {
        this.name = name;
    }

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

    @Override
    public int compareTo(Tag tag) {
        return tag.name.compareTo(getName());
    }
}
