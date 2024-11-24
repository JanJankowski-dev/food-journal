package com.food.journal.daily.plan.api;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class KeycloakUserService {

    public static final String USER_CLAIM = "sub";

    public String getUserId(JwtAuthenticationToken token) {
        return token.getToken().getClaimAsString(USER_CLAIM);
    }
}
