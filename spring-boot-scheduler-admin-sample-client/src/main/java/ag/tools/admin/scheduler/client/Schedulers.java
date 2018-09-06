package ag.tools.admin.scheduler.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedulers {

    private static final Logger LOGGER = LoggerFactory.getLogger(Schedulers.class);

    @Scheduled(fixedDelay = 2 * 1000)
    void sample(){
        LOGGER.info("execute");
    }
}
