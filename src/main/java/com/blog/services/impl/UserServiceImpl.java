package com.blog.services.impl;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);

        return this.userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User","id", userId));

        user.setAbout(userDTO.getAbout());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User updatedUser = userRepo.save(user);

        return userToDTO(updatedUser);
    }

    @Override
    public UserDTO getUserByID(Integer userId) {

        User user= this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFound("User", " ID ", userId));

        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> listOfUsers= this.userRepo.findAll();

        List<UserDTO> users = listOfUsers.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());

        return users;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", " id ", userId));

        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){

        User user= modelMapper.map(userDTO,User.class);
//        User user= new User();
//        user.setId(userDTO.getId());
//        user.setAbout(userDTO.getAbout());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());

        return user;
    }

    public UserDTO userToDTO(User user){

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//        UserDTO userDTO= new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setAbout(user.getAbout());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());

        return userDTO;
    }
}
