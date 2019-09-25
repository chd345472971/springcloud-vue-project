package com.bootdo.clouddoadmin.utils;

import com.bootdo.clouddoadmin.secuity.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecuityUtils {
    public static CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
