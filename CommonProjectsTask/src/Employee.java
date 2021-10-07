import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Employee {
    private final int id;
    private final Map<Integer, List<WorkRangeByProject>> workRangesByProjects = new HashMap<>();

    public Employee(Record record) {
        this.id = record.getEmployeeId();
        WorkRangeByProject newProject = new WorkRangeByProject(record.getStartDate(), record.getEndDate());
        List<WorkRangeByProject> listOfWorkRanges = new ArrayList<>();

        listOfWorkRanges.add(newProject);
        workRangesByProjects.put(record.getProjectId(), listOfWorkRanges);
    }

    public int getId() {
        return id;
    }

    public List<WorkRangeByProject> getProjectsWorkRangesById(int id) {
        return workRangesByProjects.get(id);
    }

    public long getMaxWorkingDays(Employee otherEmployee) {
        if (this.id == otherEmployee.id) return 0L;
        long maxWorkingDays = 0;

        for (Map.Entry<Integer, List<WorkRangeByProject>> project : workRangesByProjects.entrySet()) {
            List<WorkRangeByProject> otherEmployeeWorkRanges =
                    otherEmployee.getProjectsWorkRangesById(project.getKey());

            maxWorkingDays += WorkRangeByProject.getMaximumWorkingDaysByProject(project.getValue(),
                    otherEmployeeWorkRanges);
        }
        return maxWorkingDays;
    }

    public void addProjectFromRecord(Record record) {
        WorkRangeByProject workRange = new WorkRangeByProject(record.getStartDate(), record.getEndDate());

        if (workRangesByProjects.containsKey(record.getProjectId())) {
            workRangesByProjects.get(record.getProjectId()).add(workRange);
        } else {
            List<WorkRangeByProject> listOfWorkRanges = new ArrayList<>();

            listOfWorkRanges.add(workRange);
            workRangesByProjects.put(record.getProjectId(), listOfWorkRanges);
        }
    }
}
