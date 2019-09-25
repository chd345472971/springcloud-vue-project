package com.bootdo.clouddoadmin.service.impl;

import com.bootdo.clouddoadmin.dao.UserDao;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.secuity.CurrentUser;
import com.bootdo.clouddoadmin.service.MenuService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddocommon.exception.CDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bootdo
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDO> userDOS = userDao.list(new HashMap<String, Object>() {{
            put("username", username);
        }});
        if (userDOS.size() < 1) {
            throw new CDException("用户名或密码错误！");
        }
        UserDO userDO = userDOS.get(0);
        Set<String> perms = menuService.listPerms(userDO.getUserId());
        Set<GrantedAuthority> authorities = perms.stream().filter(Objects::nonNull).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new CurrentUser(username, userDO.getPassword(), userDO.getUserId(), userDO.getName(),authorities);
    }

}
