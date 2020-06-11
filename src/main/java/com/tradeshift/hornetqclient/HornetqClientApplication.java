package com.tradeshift.hornetqclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class HornetqClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HornetqClientApplication.class, args);
	}
}
