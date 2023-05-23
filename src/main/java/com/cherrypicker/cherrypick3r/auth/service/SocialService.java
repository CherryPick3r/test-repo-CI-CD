package com.cherrypicker.cherrypick3r.auth.service;

import com.cherrypicker.cherrypick3r.auth.dto.GoogleUserInfoDto;
import com.cherrypicker.cherrypick3r.auth.dto.SocialDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final KakaoService kakaoService;
    private final GoogleService googleService;
    private final ObjectMapper objectMapper;

    public SocialDto verificationKakao(String code){

        SocialDto socialDto = new SocialDto();
        // 코드를 이용하여 accessToken 추출
        String accessToken = kakaoService.getAccessTokenByCode(code);
        // accessToken을 이용하여 사용자 정보 추출
        String userInfo = kakaoService.getUserInfoByAccessToken(accessToken);

        try {
            JsonNode jsonNode = objectMapper.readTree(userInfo);
            String email = String.valueOf(jsonNode.get("kakao_account").get("email"));
            socialDto.setEmail("kakao_" + email.substring(1, email.length() - 1));
            String name = String.valueOf(jsonNode.get("kakao_account").get("profile").get("nickname"));
            socialDto.setName(name.substring(1, name.length() - 1));
            String imageUrl = String.valueOf(jsonNode.get("kakao_account").get("profile").get("profile_image_url"));
            socialDto.setImageUrl(imageUrl.substring(1, imageUrl.length() - 1));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return socialDto;
    }

    public SocialDto verificationGoogle(String code){

        SocialDto socialDto = new SocialDto();
        // 코드를 이용하여 accessToken 추출
        String accessToken = googleService.getAccessTokenByCode(code);
        // accessToken을 이용하여 사용자 정보 추출
        GoogleUserInfoDto googleUserInfoDto = googleService.getUserInfoByAccessToken(accessToken);

        String email = googleUserInfoDto.getEmail();
        socialDto.setEmail("google_" + email);
        String name = googleUserInfoDto.getName();
        socialDto.setName(name);
        String imageUrl = googleUserInfoDto.getPicture();
        socialDto.setImageUrl(imageUrl);

        return socialDto;
    }
}