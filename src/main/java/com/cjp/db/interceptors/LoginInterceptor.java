package com.cjp.db.interceptors;

import com.cjp.db.constant.KeyConstant;
import com.cjp.db.enums.Role;
import com.cjp.db.exceptions.NotAuthorizationException;
import com.cjp.db.utils.JwtUtil;
import com.cjp.db.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author chenjianpeng
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try{
            Map<String, Object> map = JwtUtil.parseToken(token);
            if(map == null){
                throw new NotAuthorizationException("未登录");
            }
            String id = map.get("id").toString();
            String username = map.get("username").toString();
            String role = map.get("role").toString();
            String key = switch (role) {
                case "ADMIN" -> KeyConstant.ADMIN_LOGIN + username + "_" + id;
                case "COMPANY" -> KeyConstant.COMPANY_LOGIN + username + "_" + id;
                case "DEVELOPER" -> KeyConstant.DEVELOPER_LOGIN + username + "_" + id;
                default -> throw new NotAuthorizationException("未知角色");
            };
            Long start = System.currentTimeMillis();
            if(stringRedisTemplate.opsForValue().get(key) == null){
                throw new RuntimeException("Redis 查询结果为空");
            }
            Long end = System.currentTimeMillis();
            log.info("running time2: {}", (end-start)/1000);
            ThreadLocalUtil.set(map);
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        ThreadLocalUtil.remove();
    }
}
