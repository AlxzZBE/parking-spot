package com.api.parkingcontrol.requests;

import com.api.parkingcontrol.domain.User;

public class UserGet {

    private String username;
    private boolean isEnabled;

    public UserGet(User user) {
        this.username = user.getUsername();
        this.isEnabled = user.isEnabled();
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}