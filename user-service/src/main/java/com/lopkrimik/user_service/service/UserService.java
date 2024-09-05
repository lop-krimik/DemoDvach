package com.lopkrimik.user_service.service;

import com.lopkrimik.user_service.dto.UserDto;
import com.lopkrimik.user_service.handler.ResourceNotFoundException;
import com.lopkrimik.user_service.repository.UserRepository;
import com.lopkrimik.user_service.user.User;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        var userList = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        userList.forEach(user -> userDtos.add(UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build()));
        return userDtos;
    }

    public void addUser(UserDto userDto) {
        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .build());
    }

    public UserDto getUserByUser_id(Long id) {
        var user = userRepository.getUserById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id" + id + "not found"));
        return user != null ?
                UserDto.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .build() :
                null;
    }

    @Transactional
    public void deleteUserByUser_id (Long id){
        var user = userRepository.getUserById(id).orElse(null);
        if (user!=null)
            userRepository.deleteUserById(id);
        else log.info(String.format("User with id: %s is not existing",id));
    }

    public void updateUser (Long user_id, String username){
        var user = userRepository.getUserById(user_id).orElse(null);
        var userList = userRepository.findAll();
        if (user!= null){
            for(int i=0; i<userList.size(); i++){
                if(!userList.get(i).getUsername().equals(username)){
                    user.setUsername(username);
                    userRepository.save(user);
                }
                else log.error(String.format("User with this username: %s already exists", username ));
            }

        }
    }
}
