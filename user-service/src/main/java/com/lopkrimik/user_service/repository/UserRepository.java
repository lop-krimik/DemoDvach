package com.lopkrimik.user_service.repository;

import com.lopkrimik.user_service.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> getUserById(Long id);
     void deleteUserById(Long id);

}
