package com.practice.snsproject.service;

import com.practice.snsproject.domain.entity.User;
import com.practice.snsproject.domain.entity.UserDetailsImpl;
import com.practice.snsproject.exception.AppException;
import com.practice.snsproject.exception.ErrorCode;
import com.practice.snsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
//스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    //사용자 이름(userName)으로 사용자의 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new AppException(ErrorCode.USERNAME_NOT_FOUND, username + "는 존재하지 않는 유저입니다."));
        if (user != null){
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            return userDetails;
        }
        return null;
    }
}
