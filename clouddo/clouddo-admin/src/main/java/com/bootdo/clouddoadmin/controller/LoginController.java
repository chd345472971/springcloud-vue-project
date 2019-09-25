package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.service.MenuService;
import com.bootdo.clouddoadmin.service.TokenService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddoadmin.utils.SecuityUtils;
import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.LoginDTO;
import com.bootdo.clouddocommon.dto.UserToken;
import com.bootdo.clouddocommon.utils.JwtUtils;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/router")
    R router() {
        return R.ok().put("router", menuService.RouterDTOsByUserId(SecuityUtils.getCurrentUser().getId()));
    }


    @RequestMapping("/logout")
    R logout(String token) {
        consumerTokenServices.revokeToken(token);
        return R.ok();
    }


}
