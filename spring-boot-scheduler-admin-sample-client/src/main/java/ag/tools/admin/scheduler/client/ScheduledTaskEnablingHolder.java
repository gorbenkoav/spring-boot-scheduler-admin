package ag.tools.admin.scheduler.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ScheduledTaskEnablingHolder {

    private final Map<String, Boolean> taskEnabling = new ConcurrentHashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskEnablingHolder.class);

    public boolean isTaskEnable(String task) {
        return taskEnabling.getOrDefault(task, true);
    }

    public void setTaskEnable(String task, boolean isEnable) {
        LOGGER.info("Set enabling {} for task {}", isEnable, task);
        taskEnabling.put(task, isEnable);
    }
}
