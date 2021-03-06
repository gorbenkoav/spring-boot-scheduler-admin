package ag.tools.admin.scheduler.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.scheduling.ScheduledTasksEndpoint;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Endpoint(id = "scheduler-admin")
public class SchedulerAdminEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerAdminEndpoint.class);

    private ScheduledTasksEndpoint scheduledTasksEndpoint;

    private ScheduledTaskConfigurationHolder taskConfigurationHolder;

    @Autowired
    public SchedulerAdminEndpoint(ScheduledTasksEndpoint delegate, ScheduledTaskConfigurationHolder taskConfigurationHolder) {
        this.scheduledTasksEndpoint = delegate;
        this.taskConfigurationHolder = taskConfigurationHolder;
    }

    @ReadOperation
    public WebEndpointResponse<List<ScheduledTask>> scheduledTasks() {
        Stream<ScheduledTasksEndpoint.TaskDescription> taskDescriptionStream = Stream.of(
                scheduledTasksEndpoint.scheduledTasks().getCron(),
                scheduledTasksEndpoint.scheduledTasks().getFixedDelay(),
                scheduledTasksEndpoint.scheduledTasks().getFixedRate()
        ).flatMap(List::stream);

        List<ScheduledTask> scheduledTasks = taskDescriptionStream.
                map(t -> new ScheduledTask(t.getRunnable().getTarget(),
                        taskConfigurationHolder.getTaskName(t.getRunnable().getTarget()),
                        "Some Time",
                        taskConfigurationHolder.isTaskEnable(t.getRunnable().getTarget()))).
                collect(Collectors.toList());
        LOGGER.info("ScheduledTasks {}", scheduledTasks);
        return new WebEndpointResponse<>(scheduledTasks);
    }

    @WriteOperation
    public void configureTask(@Selector String arg0, Boolean isEnable) {
        taskConfigurationHolder.setTaskEnable(arg0, isEnable);
    }
}
