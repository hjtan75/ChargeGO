package com.lb02b.chargego.UtilBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomerWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CgInterceptor cgInterceptor(){
        return new CgInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = new ArrayList<String>();
        excludeList.add("/sign*");
        excludeList.add("/swagger**/**");
        excludeList.add("/operator/sign*");
        excludeList.add("/error");
        excludeList.add("/**");
        registry.addInterceptor(cgInterceptor()).addPathPatterns("/**").excludePathPatterns(excludeList);
    }

}

