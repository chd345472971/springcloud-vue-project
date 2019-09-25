package com.bootdo.clouddozuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * @version V1.0
 * @Author bootdo 1992lcg@163.com
 */
public class AccessFilter extends ZuulFilter {


    private String ignorePath = "/api-admin/login";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        final String requestUri = request.getRequestURI();
//        if (isStartWith(requestUri)) {
//            return null;
//        }
//        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
//        if(null == accessToken || accessToken == ""){
//            accessToken = request.getParameter(CommonConstants.TOKEN);
//        }
//        if (null == accessToken) {
//            setFailedRequest(R.error401(), 200);
//            return null;
//        }
//        try {
//            UserToken userToken = JwtUtils.getInfoFromToken(accessToken);
//        } catch (Exception e) {
//            setFailedRequest(R.error401(), 200);
//            return null;
//        }
//        FilterContextHandler.setToken(accessToken);
//        if(!havePermission(request)){
//            setFailedRequest(R.error403(), 200);
//            return null;
//        }
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        //We need our JWT tokens relayed to resource servers
        //添加自己header
//        ctx.addZuulRequestHeader(CommonConstants.CONTEXT_TOKEN, accessToken);
        //移除忽略token
        headers.remove("authorization");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        // We need our JWT tokens relayed to resource servers
//        headers.remove("authorization");
//        return null;
    }


}
