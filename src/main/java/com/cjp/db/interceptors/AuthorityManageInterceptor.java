package com.cjp.db.interceptors;

import com.cjp.db.annotation.Authority;
import com.cjp.db.enums.Role;
import com.cjp.db.exceptions.NotAuthorizationException;
import com.cjp.db.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author chenjianpeng
 */
@Component
@Slf4j
public class AuthorityManageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long start = System.currentTimeMillis();
        if(!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        Authority authority = method.getAnnotation(Authority.class);

        if(authority == null) {
            log.info("{} has no authority requirement", method.getName());
            return true;
        }
        String token = request.getHeader("Authorization");
        Map<String, Object> map = JwtUtil.parseToken(token);
        if(map == null){
            throw new NotAuthorizationException("没有权限");
        }
        String role = map.get("role").toString();
        Role roleEnum = Role.valueOf(role);
        Long end = System.currentTimeMillis();
        log.info("running time: {}", (end-start)/1000);
        if(!roleEnum.equals(authority.value())){
            log.warn("No Authorization !!!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }
}
