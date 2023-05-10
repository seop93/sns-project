package com.practice.snsproject.service;


import com.practice.snsproject.doamin.entity.User;
import com.practice.snsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User join(String userName, String password){


        String encPassword = passwordEncoder.encode(password);
        User user = User.registerUser(userName, encPassword);
        return userRepository.save(user);
    }


}
