package com.epassi.filereader.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    UserDetails findByLogin(String login);
}
