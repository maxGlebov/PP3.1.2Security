package com.maglebov.MvcBoot.service;

import com.maglebov.MvcBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.maglebov.MvcBoot.dao.UserDao;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDao UserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== ОТЛАДКА: Попытка входа с email: '" + username + "' ===");
        User user = UserService.findByEmail(username);
        System.out.println("=== ОТЛАДКА: Найден пользователь: " + (user != null ? user.toString() : "NULL") + " ===");
        System.out.println("Загружен пароль из БД: " + (user != null ? user.getPassword() : "НЕТ ПАРОЛЯ") );
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }
}