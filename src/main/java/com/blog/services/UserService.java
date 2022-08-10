package com.blog.services;

import com.blog.entities.User;
import com.blog.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer userId);
    UserDTO getUserByID(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
