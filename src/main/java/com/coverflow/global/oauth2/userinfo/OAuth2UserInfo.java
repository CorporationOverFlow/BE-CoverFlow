package com.coverflow.global.oauth2.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {

    final protected Map<String, Object> attributes;

    public OAuth2UserInfo(
            final Map<String, Object> attributes
    ) {
        this.attributes = attributes;
    }

    public abstract String getId(); //소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"
}
