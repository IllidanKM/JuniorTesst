package net.tryhard.juniortesst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages ="net.tryhard.juniortesst.mapper" )
@ComponentScan(basePackages = "net.tryhard.juniortesst.model")
@ComponentScan(basePackages = "net.tryhard.juniortesst.repository")

public class  JuniorTesstApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuniorTesstApplication.class, args);
	}

}
