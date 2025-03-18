package com.calctax.tax_calculation_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxCalculationApiApplication {

	public static void main(String[] args) {

			Dotenv dotenv = Dotenv.configure().load();

			String dbUrl = dotenv.get("DB_URL");
			String dbUsername = dotenv.get("DB_USERNAME");
			String dbPassword = dotenv.get("DB_PASSWORD");


			System.setProperty("DB_URL", dbUrl);
			System.setProperty("DB_USERNAME", dbUsername);
			System.setProperty("DB_PASSWORD", dbPassword);

		SpringApplication.run(TaxCalculationApiApplication.class, args);
	}

}
