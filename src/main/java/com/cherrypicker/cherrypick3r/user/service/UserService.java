package com.cherrypicker.cherrypick3r.user.service;

import com.cherrypicker.cherrypick3r.tag.domain.Tag;
import com.cherrypicker.cherrypick3r.tag.domain.TagRepository;
import com.cherrypicker.cherrypick3r.user.domain.User;
import com.cherrypicker.cherrypick3r.user.domain.UserRepository;
import com.cherrypicker.cherrypick3r.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public User loadUserByUserEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public User saveUserByUserEmail(String email, String nickname) {
        // 이미 존재하는 유저인지 확인
        //User user = userRepository.findByEmail(email).isEmpty();
        if (!userRepository.findByEmail(email).isEmpty())
            return userRepository.findByEmail(email).get();

        Tag tag = new Tag();
        tagRepository.save(tag);

        // 존재하지 않는다면 DB에 저장
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .auth("USER")
                .tag(tag)
                .build();

        userRepository.save(user);

        return user;
    }

    //public UserDto(String email, String nickname, String auth, Tag tag, UserClassify userClassify){

}
