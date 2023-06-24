package com.lb02b.chargego.Controller;

import com.lb02b.chargego.UtilBean.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    public RedisUtil redisUtil;

    public static final String USER_REDIS_SESSION = "user_redis_session";
}
