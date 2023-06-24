package com.lb02b.chargego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.lb02b.chargego"})
@MapperScan(basePackages = "com.lb02b.chargego.Dao")
@ComponentScan(basePackages = {"com.lb02b.chargego.DataObject",
        "com.lb02b.chargego.UtilBean","com.lb02b.chargego.Dao","com.lb02b.chargego.Controller", "com.lb02b.chargego.Service"})
@RestController
public class ChargeGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargeGoApplication.class, args);
    }

}
