package com.example.pctol.server.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.pctol.common.constant.JwtClaimsConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.common.properties.JWTproperties;
import com.example.pctol.common.utils.JwtUtil;
import com.example.pctol.pojo.VO.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import io.jsonwebtoken.Claims;
/**
 * @author hp
 * @date 2024/3/25
 */
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    JWTproperties jwTproperties;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURL().toString();


        if (url.contains("login")|url.contains("register")){

            return true;
        }

        String jwt=request.getHeader("token");

        if (!StringUtils.hasLength(jwt)){
            Result error=new Result(StateCode.NOT_LOGIN,"NOT_LOGIN");
            String noLogin= JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return false;
        }

        try {
            Claims claims=JwtUtil.parseJWT(jwTproperties.getPublicSecretKey(),jwt);
            String loginInfo= (String) claims.get(JwtClaimsConstant.ADMIN_INFO);
            if(loginInfo==null)
                loginInfo=(String) claims.get(JwtClaimsConstant.TEACHER_ID);
            if(loginInfo==null)
                loginInfo=(String) claims.get(JwtClaimsConstant.STUDENT_ID);
            log.info("loginInfo: {}",loginInfo);
            BaseContext.setLoginInfo(loginInfo);
        } catch (Exception e) {
            e.printStackTrace();
            Result error=new Result(StateCode.NOT_LOGIN,"NOT_LOGIN");
            String noLogin=JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return false;
        }

        return  true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
