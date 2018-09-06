package ag.tools.admin.scheduler;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SpringBootSchedulerAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSchedulerAdminServerApplication.class, args);
	}
}
