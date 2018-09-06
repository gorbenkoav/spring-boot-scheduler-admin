package ag.tools.admin.scheduler.client;

import lombok.Data;

@Data
public class ScheduledTask {
    private String id;
    private String name;
    private String timetable;
    private boolean isEnable;

    public ScheduledTask() {
    }

    public ScheduledTask(String id, String name, String timetable, boolean isEnable) {
        this.id = id;
        this.name = name;
        this.timetable = timetable;
        this.isEnable = isEnable;
    }
}
