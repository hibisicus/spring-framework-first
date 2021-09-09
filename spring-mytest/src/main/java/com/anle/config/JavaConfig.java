package com.anle.config;

import com.anle.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-02 10:45
 */
@Configuration
@ComponentScan
public class JavaConfig {
	@Bean
	public User user(){
		return new User("001","Test");
	}
}
