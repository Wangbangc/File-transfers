package com.example.demo.filter;

import com.example.demo.utils.UserUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AntPathMatcher implements Filter {


    private static final org.springframework.util.AntPathMatcher PATH_MATCHER = new org.springframework.util.AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{"/user/register","/user/login"};
        boolean check = check(urls, requestURI);

        if (check) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader("Authorization");
            if (UserUtils.isTokenValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
        return;

    }

    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {

            if (PATH_MATCHER.match(url, requestURI)) return true;
        }
        return false;
    }
}
