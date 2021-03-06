package com.starter.services;

import com.starter.entities.User;

import java.util.List;

/**
 * Created by Yuksi on 24.04.2018.
 */
public interface IUserService {
    List<User> getAll();

    User getById(long id);

    void save(User user);

    void update(User user);

    User findByUsername(String username);


}