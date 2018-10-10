package ag.tools.admin.scheduler.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties("spring.boot.schedule.config")
public class ScheduledConfigurationProperties {

    private Map<String, String> names = new HashMap<>();

    private Map<String, String> enables = new HashMap<>();

    public Map<String, String> getNames() {
        return names;
    }

    public Map<String, String> getEnables() {
        return enables;
    }
}
