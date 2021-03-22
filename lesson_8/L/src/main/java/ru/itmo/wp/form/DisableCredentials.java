package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DisableCredentials {

    @NotNull
    @NotEmpty
    private String curId;

    private boolean disabled;

    public String getUserD() {
        return userD;
    }

    public void setUserD(String userD) {
        this.userD = userD;
    }

    @NotNull
    @NotEmpty
    private String userD;

    public String getCurId() {
        return curId;
    }

    public void setCurId(String curId) {
        this.curId = curId;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }



}
