package com.epassi.filereader.auth.service;

import com.epassi.filereader.auth.entity.User;
import com.epassi.filereader.auth.enums.UserRole;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepositoryImpl implements UserRepository{
    private List<User> users = new ArrayList<>();

    @PostConstruct
    private void setPreDefinedUserList(){
        users.add(new User(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "amila", "$2a$12$nbOaHUpqtEE9.Iriy21HOuvKZDRufDoNfD56LfCefEAQ67vx4yWpm", UserRole.ADMIN));
        users.add(new User(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "test_user_1", "$2a$12$nbOaHUpqtEE9.Iriy21HOuvKZDRufDoNfD56LfCefEAQ67vx4yWpm", UserRole.USER));
        users.add(new User(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "test_user_2", "$2a$12$nbOaHUpqtEE9.Iriy21HOuvKZDRufDoNfD56LfCefEAQ67vx4yWpm", UserRole.USER));
    }

    @Override
    public UserDetails findByLogin(String login) {
        Optional<User> first = users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
        if(first.isEmpty()){
            throw new AuthenticationException("User not found!") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
        else return first.get();
    }
}
