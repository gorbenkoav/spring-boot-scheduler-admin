package ag.tools.admin.scheduler.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedulers {

    private static final Logger LOGGER = LoggerFactory.getLogger(Schedulers.class);

    @Autowired
    ScheduledTaskEnablingHolder taskEnablingHolder;

    @Scheduled(fixedDelay = 2 * 1000)
    void sampleOne(){
        if (taskEnablingHolder.isTaskEnable("ag.tools.admin.scheduler.client.Schedulers.sampleOne")) {
            LOGGER.info("execute one");
        }
    }

    @Scheduled(fixedDelay = 5 * 1000)
    void sampleTwo(){
        if (taskEnablingHolder.isTaskEnable("ag.tools.admin.scheduler.client.Schedulers.sampleTwo")) {
            LOGGER.info("execute two");
        }
    }

}
