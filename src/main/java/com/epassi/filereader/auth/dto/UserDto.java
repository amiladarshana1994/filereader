package com.epassi.filereader.auth.dto;

import com.epassi.filereader.auth.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String username;
    String password;
    UserRole role;
}
