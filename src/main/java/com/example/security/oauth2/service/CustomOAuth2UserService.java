package com.example.security.oauth2.service;

import com.example.security.common.exceptions.custom.InvalidOauth2Exception;
import com.example.security.enums.Provider;
import com.example.security.models.entity.AppUser;
import com.example.security.oauth2.user.OAuth2UserInfo;
import com.example.security.oauth2.user.UserPrincipal;
import com.example.security.oauth2.user.factory.OAuth2UserInfoFactory;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.security.AddRoleToUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final AddRoleToUserService addRoleToUserService;

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        logger.info("loadUser method called");

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        logger.info("processOAuth2User method called");
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new InvalidOauth2Exception("Email not found from OAuth2 provider");
        }

        Optional<AppUser> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        AppUser user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new InvalidOauth2Exception("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private AppUser registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        logger.info("registerNewUser method called");
        AppUser user = AppUser.builder()
        .provider(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
        .providerId(oAuth2UserInfo.getId())
        .enabled(true)
        .credentialsNonExpired(true)
        .accountNonLocked(true)
        .accountNonExpired(true)
        .firstName(oAuth2UserInfo.getName())
        .email(oAuth2UserInfo.getEmail())
        .imageUrl(oAuth2UserInfo.getImageUrl())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now()).build();

        userRepository.save(user);
        addRoleToUserService.addRoleToUser(user.getEmail(),"USER");
        return user;
    }

    private AppUser updateExistingUser(AppUser existingUser, OAuth2UserInfo oAuth2UserInfo) {
        logger.info("updateExistingUser method called");

        existingUser.setFirstName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        existingUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existingUser);
    }

}
