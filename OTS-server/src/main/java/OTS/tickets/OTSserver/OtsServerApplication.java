package OTS.tickets.OTSserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OtsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtsServerApplication.class, args);
	}
}
