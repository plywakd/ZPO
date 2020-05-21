package com.mycompany.ZPO06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Zpo06Application{

	public static void main(String[] args) {
		SpringApplication.run(Zpo06Application.class, args);
	}
        @GetMapping("/welcome")
        public String welcome(@RequestParam(value ="mess", defaultValue = "World")String mess){
            return String.format("Hello %s", mess);
        }
        

}
