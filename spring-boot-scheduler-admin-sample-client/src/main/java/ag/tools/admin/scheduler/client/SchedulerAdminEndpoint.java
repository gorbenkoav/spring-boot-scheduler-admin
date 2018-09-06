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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Endpoint(id = "scheduler-admin")
public class SchedulerAdminEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerAdminEndpoint.class);

//    private final Map<String, Boolean> taskEnabling;

    private ScheduledTasksEndpoint scheduledTasksEndpoint;

    @Autowired
    public SchedulerAdminEndpoint(ScheduledTasksEndpoint delegate) {
        this.scheduledTasksEndpoint = delegate;
//        taskEnabling = new ConcurrentHashMap<>();
    }

//    public Map<String, Boolean> getTaskEnabling() {
//        Stream<ScheduledTasksEndpoint.TaskDescription> taskDescriptionStream = Stream.of(
//                scheduledTasksEndpoint.scheduledTasks().getCron(),
//                scheduledTasksEndpoint.scheduledTasks().getFixedDelay(),
//                scheduledTasksEndpoint.scheduledTasks().getFixedRate()
//        ).flatMap(List::stream);
//
//        taskEnabling.putAll(taskDescriptionStream.collect(Collectors.toMap(i -> i.getRunnable().getTarget(), i -> true)));
//        LOGGER.info("taskEnabling {}", taskEnabling);
//        return taskEnabling;
//    }

    //@ReadOperation
//    public WebEndpointResponse<Map<String, Boolean>> scheduledTaskEnabling() {
//        return new WebEndpointResponse<>(taskEnabling);
//    }

    @ReadOperation
    public WebEndpointResponse<List<ScheduledTask>> scheduledTasks() {
        Stream<ScheduledTasksEndpoint.TaskDescription> taskDescriptionStream = Stream.of(
                scheduledTasksEndpoint.scheduledTasks().getCron(),
                scheduledTasksEndpoint.scheduledTasks().getFixedDelay(),
                scheduledTasksEndpoint.scheduledTasks().getFixedRate()
        ).flatMap(List::stream);

        List<ScheduledTask> scheduledTasks = taskDescriptionStream.
                map(t -> new ScheduledTask(t.getRunnable().getTarget(), "Some Name", "Some Time", true)).
                collect(Collectors.toList());
        return new WebEndpointResponse<>(scheduledTasks);
    }

   // @WriteOperation
//    public void configureTask(@Selector String arg0, Boolean isEnable) {
//        taskEnabling.put(arg0, isEnable);
//    }
}
