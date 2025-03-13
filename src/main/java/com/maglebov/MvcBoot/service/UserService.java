package com.maglebov.MvcBoot.service;



import com.maglebov.MvcBoot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void editUser(User user);
    void deleteUser(long id);
    User getUserById(Long id);
    void addUser(User user);
}