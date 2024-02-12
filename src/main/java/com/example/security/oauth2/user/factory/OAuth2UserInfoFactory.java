package com.example.security.oauth2.user.factory;

import com.example.security.common.exceptions.custom.InvalidOauth2Exception;
import com.example.security.enums.Provider;
import com.example.security.oauth2.user.OAuth2UserInfo;
import com.example.security.oauth2.user.social.FacebookOAuth2UserInfo;
import com.example.security.oauth2.user.social.GithubOAuth2UserInfo;
import com.example.security.oauth2.user.social.GoogleOAuth2UserInfo;
import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        System.out.println("registrationId: " + registrationId);
        if(registrationId.equalsIgnoreCase(Provider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(Provider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(Provider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new InvalidOauth2Exception("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
