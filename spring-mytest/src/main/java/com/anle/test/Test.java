package com.anle.test;

import com.anle.config.JavaConfig;
import com.anle.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-02 10:47
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("hah");
		AnnotationConfigApplicationContext context = new
				AnnotationConfigApplicationContext(JavaConfig.class);
		User user = (User)context.getBean("user");
		System.out.println(user.toString());
	}

}
