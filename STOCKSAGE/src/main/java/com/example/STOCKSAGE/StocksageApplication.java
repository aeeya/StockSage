package com.example.STOCKSAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = {"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"})
public class StocksageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksageApplication.class, args);
	}

}