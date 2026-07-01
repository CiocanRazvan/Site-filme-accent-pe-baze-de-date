package proiect.filmesgbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesgbdApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmesgbdApplication.class, args);
		System.out.println(" CineVault Backend is running on http://localhost:8081");
	}

}
