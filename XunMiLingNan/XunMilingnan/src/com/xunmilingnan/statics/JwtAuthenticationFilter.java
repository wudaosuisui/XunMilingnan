package com.xunmilingnan.statics;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
//import xyz.hcworld.xmlnbackend.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt身份验证过滤器
 *
 * @author: 张红尘
 * @date: 2018/4/23
 **/
//鎰熻杩欎釜姣旇緝楹荤儲鍟? 
//涓嶈繃濂藉儚鏄釜闈欐?佺被   璨屼技涓嶇敤绠?
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    /**
     *拦截特定的接口.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (isProtectedUrl(request)) {
                request = JwtUtil.validateTokenAndAddUserIdToHeader(request);
            } else if (isProtectedUserLoginUrl(request)) {
            } else if (isProtectedUserUrl(request)) {
                request = JwtUtil.validateTokenAndAddUserIdToHeader(request);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 拦截APi开头的
     */
    private boolean isProtectedUrl(HttpServletRequest request) {
        return pathMatcher.match("/api/**", request.getServletPath());
    }

    /**拦截user开头的
     */
    private boolean isProtectedUserUrl(HttpServletRequest request) {
        return pathMatcher.match("/user/**", request.getServletPath());
    }

    /**
     * 放行/user/login和/user/register开头
     */
    private boolean isProtectedUserLoginUrl(HttpServletRequest request) {
        boolean a = pathMatcher.match("/user/login", request.getServletPath())
                || pathMatcher.match("/user/login/", request.getServletPath())
                || pathMatcher.match("/user/register", request.getServletPath())
                || pathMatcher.match("/user/register/", request.getServletPath());
        System.out.println(a);
        return a;
    }
}