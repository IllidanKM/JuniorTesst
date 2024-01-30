package net.tryhard.juniortesst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "net.tryhard.juniortesst.*")

public class  JuniorTesstApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuniorTesstApplication.class, args);
	}

}
