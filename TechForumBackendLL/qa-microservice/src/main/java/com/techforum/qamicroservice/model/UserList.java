package com.techforum.qamicroservice.model;

import java.util.ArrayList;
import java.util.List;

import com.techforum.qamicroservice.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserList {
    private List<User> users;

    public UserList() {
        users = new ArrayList<>();
    }
}