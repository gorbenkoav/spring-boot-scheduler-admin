package ag.tools.admin.scheduler.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ScheduledTaskConfigurationHolder {

    private final Map<String, Boolean> taskEnabling = new ConcurrentHashMap<>();

    private final Map<String, String> taskNames = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskConfigurationHolder.class);

    @Autowired
    public ScheduledTaskConfigurationHolder(ScheduledConfigurationProperties scheduledConfigurationProperties) {
        if (scheduledConfigurationProperties != null) {
            if (scheduledConfigurationProperties.getEnables() != null) {
                taskEnabling.putAll(scheduledConfigurationProperties.getEnables().entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey(),
                                e -> Boolean.parseBoolean(e.getValue())
                        )));
            }
            if (scheduledConfigurationProperties.getNames() != null) {
                taskNames.putAll(scheduledConfigurationProperties.getNames());
            }
        }
    }

    public boolean isTaskEnable(String task) {
        return taskEnabling.getOrDefault(task, true);
    }

    public void setTaskEnable(String task, boolean isEnable) {
        LOGGER.info("Set enabling {} for task {}", isEnable, task);
        taskEnabling.put(task, isEnable);
    }

    public String getTaskName(String task) {
        return taskNames.getOrDefault(task, "No name");
    }
}
