package org.launchcode.onlinebankapp.models.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by User on 6/30/17.
 */
public class Authority implements GrantedAuthority{

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

