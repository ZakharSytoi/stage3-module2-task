package com.mjc.school.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.mjc.school.service", "com.mjc.school.repository"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Config {
}
