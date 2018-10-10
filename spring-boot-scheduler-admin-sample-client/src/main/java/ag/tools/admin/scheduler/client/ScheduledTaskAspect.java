package ag.tools.admin.scheduler.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScheduledTaskAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskAspect.class);

    private ScheduledTaskConfigurationHolder taskConfigurationHolder;

    @Autowired
    public ScheduledTaskAspect(ScheduledTaskConfigurationHolder taskConfigurationHolder) {
        this.taskConfigurationHolder = taskConfigurationHolder;
    }

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void scheduleTask(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            String taskId = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
            LOGGER.info("Enabling of task {} is {}.", taskId, taskConfigurationHolder.isTaskEnable(taskId));

            if (taskConfigurationHolder.isTaskEnable(taskId)) {
                joinPoint.proceed();
            }
        }
    }
}
