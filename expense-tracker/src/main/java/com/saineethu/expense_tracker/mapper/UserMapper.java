
package com.saineethu.expense_tracker.mapper;

import com.saineethu.expense_tracker.dto.UserDto;
import com.saineethu.expense_tracker.dto.UserRegisterRequest;
import com.saineethu.expense_tracker.entity.User;

import java.time.Instant;
import java.util.UUID;

public class UserMapper {

    private UserMapper(){}

    public static UserDto toDto (User user){
        if(user == null) return null;
        return new UserDto(
          user.getId(),
          user.getFirstName(),
          user.getLastName(),
          user.getEmail()
        );
    }

    /* Create a User entity from a registration request.
    Note: password must be hashed by service before saving OR pass hashed password here
    *
    * */
    public static User fromRegisterRequest(UserRegisterRequest req, String hashedPassword){
        if(req == null) return null;
        User u = new User();
        //u.setId(UUID.randomUUID());
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        u.setEmail(req.getEmail());
        u.setPassword(hashedPassword);
       // u.setCreatedAt(Instant.now()); // removing here to reply on @PrePersist
        return u;

    }
}
