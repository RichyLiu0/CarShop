package org.main;

import org.main.config.SwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@Import({SwaggerConfig.class})
@ComponentScan(basePackages = {"org.main.model", "org.main.biz", "org.main.controllers"})
@MapperScan("org.main.dao")
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
