package app.ridematrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RideMatrixVmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideMatrixVmsApplication.class, args);
	}
}
