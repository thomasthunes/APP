package com.example.demo.DTO.User;

import com.example.demo.domain.UserObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String userName;
    private String email;

    public static UserDTO convertToDTO(UserObject userObject){
        var dto = new UserDTO();
        dto.setUserId(userObject.getUserId());
        dto.setEmail(userObject.getEmail());
        dto.setUserName(userObject.getUserName());
        return dto;
    }
}
