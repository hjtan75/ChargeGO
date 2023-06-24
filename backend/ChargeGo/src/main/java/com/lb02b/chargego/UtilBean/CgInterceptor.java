package com.lb02b.chargego.UtilBean;

import com.lb02b.chargego.Utils.CommonReturnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class CgInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisUtil redis;
    public static final String USER_REDIS_SESSION = "user_redis_session";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        var userId = request.getHeader("id");
        var userToken = request.getHeader("token");

        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)) {
            String uniqueToken = redis.get(USER_REDIS_SESSION + ":" + userId);
            if (StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)) {
                returnErrorResponse(response, new CommonReturnType().errorTokenMsg("Please sign in first."));
                return false;
            } else {
                if (!uniqueToken.equals(userToken)) {
                    returnErrorResponse(response, new CommonReturnType().errorTokenMsg("The token is expired, please sign in again."));
                    return false;
                }
            }
        } else {
            returnErrorResponse(response, new CommonReturnType().errorTokenMsg("Please sign in first."));
            return false;
        }

        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, CommonReturnType result)
            throws IOException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(com.lb02b.chargego.Utils.JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }

}


