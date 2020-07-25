package com.xdclass.online_xdclass.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdclass.online_xdclass.utils.JWTUtils;
import com.xdclass.online_xdclass.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入到controller之前的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String accessToken = request.getHeader("token");
            if (accessToken == null) {
                accessToken = request.getParameter("token");
            }

            if (StringUtils.isNoneBlank(accessToken)) {
                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims == null) {
                    //告诉登录过期，重新登录
                    sendJsonMessage(response, JsonData.buildError("登录过期，重新登录"));
                    return false;
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                return true;
            }
        } catch (Exception e) {}
        //登录失败
        sendJsonMessage(response, JsonData.buildError("登录过期，重新登录"));
        return false;
    }

    /**
     * 相应Json数据给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response,Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json:charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
