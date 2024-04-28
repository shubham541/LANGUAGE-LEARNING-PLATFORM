package com.bits.language.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.bits")
@EnableMongoRepositories
@EnableFeignClients(basePackages = "com.bits")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LanguagePlatformDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguagePlatformDataApplication.class, args);
	}

}
