package com.blog.contollers;

import com.blog.payloads.APIResponse;
import com.blog.payloads.UserDTO;
import com.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //post - create user
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
       UserDTO createdUser=  userService.createUser(userDTO);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //put- update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId){

        UserDTO updatedUser = this.userService.updateUser(userDTO,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //delete - delete user
    @DeleteMapping("/{userID}")
    public ResponseEntity<APIResponse> deleteMapping(@PathVariable Integer userID){
        this.userService.deleteUser(userID);
//        return new ResponseEntity<>(Map.of("Message","User Deleted successfully"), HttpStatus.OK);
        return new ResponseEntity<>(new APIResponse("User Deleted successfully", true, LocalDateTime.now()), HttpStatus.OK);

    }

    //get - get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        return  ResponseEntity.ok(this.userService.getAllUsers());
    }

    //get - get single users
    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userID){

        return  ResponseEntity.ok(this.userService.getUserByID(userID));
    }


}
