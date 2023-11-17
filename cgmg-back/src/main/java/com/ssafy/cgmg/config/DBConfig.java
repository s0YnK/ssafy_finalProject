package com.ssafy.cgmg.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.cgmg.model.dao")
public class DBConfig {

}
