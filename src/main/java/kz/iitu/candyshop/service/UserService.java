package kz.iitu.candyshop.service;

import kz.iitu.candyshop.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
