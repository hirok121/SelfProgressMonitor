import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleManager {
    private Map<String, List<String>> weeklySchedule;

    public ScheduleManager() {
        weeklySchedule = new HashMap<>();
        initializeSchedule();
    }

    private void initializeSchedule() {
        // Example schedule
        weeklySchedule.put("Monday", List.of("Math", "English"));
        weeklySchedule.put("Tuesday", List.of("Science", "History"));
        weeklySchedule.put("Wednesday", List.of("Math", "Physical Education"));
        weeklySchedule.put("Thursday", List.of("Art", "Biology"));
        weeklySchedule.put("Friday", List.of("Chemistry", "Geography"));
    }

    public List<String> getClassesForDay(String day) {
        return weeklySchedule.get(day);
    }
}
