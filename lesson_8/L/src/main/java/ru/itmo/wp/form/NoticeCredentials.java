package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoticeCredentials {
    @NotNull(message = "Content can't be null")
    @NotEmpty(message = "Content can't be empty")
    @Size(min = 1, max = 254, message = "Wrong content length expected 1-254")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}