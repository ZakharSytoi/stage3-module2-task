package com.mjc.school.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.mjc.school.service", "com.mjc.school.repository", "com.mjc.school.controller", "com.mjc.school.main"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Config {
}
