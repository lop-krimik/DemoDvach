package com.lopkrimik.user_service.controller;

import com.domain.basedomain.events.UserEvent;
import com.lopkrimik.user_service.dto.UserDto;
import com.lopkrimik.user_service.kafka.UserProducer;
import com.lopkrimik.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserProducer userProducer;
    private final UserService userService;


    @GetMapping ("/getAllUsers")
    public List<UserDto> getAllUsers (){
       return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        UserEvent userEvent = new UserEvent();
        userEvent.setUsername(userDto.getUsername());
        userEvent.setEmail(userDto.getEmail());
        userProducer.sendMessage(userEvent);
        return ResponseEntity.ok("User has been received");
    }

    @GetMapping("/getUserById/{user_id}")
    public UserDto getUserByUserId (@PathVariable Long user_id){
        return userService.getUserByUser_id(user_id);
    }

    @DeleteMapping("/deleteUserById/{user_id}")
    public void deleteUserById (@PathVariable Long user_id){
        userService.deleteUserByUser_id(user_id);
    }

    @PatchMapping("/updateUsername/id/{id}")
    public void updateUsername (@PathVariable Long id, @RequestBody String username){
        userService.updateUser(id, username);
    }


}
