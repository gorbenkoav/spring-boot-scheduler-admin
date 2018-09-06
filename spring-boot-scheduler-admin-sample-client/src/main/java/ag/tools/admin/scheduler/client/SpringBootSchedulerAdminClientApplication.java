package ag.tools.admin.scheduler.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootSchedulerAdminClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSchedulerAdminClientApplication.class, args);
	}
}
