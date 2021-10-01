import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Employee {
    private final int id;
    private final Map<Integer, List<WorkRangesByProject>> workRangesByProjects = new HashMap<>();

    public Employee(Record record) {
        this.id = record.getEmployeeId();
        WorkRangesByProject newProject = new WorkRangesByProject(record.getStartDate(), record.getEndDate());
        List<WorkRangesByProject> newProjectContracts = new ArrayList<>();

        newProjectContracts.add(newProject);
        workRangesByProjects.put(record.getProjectId(), newProjectContracts);
    }

    public int getId() {
        return id;
    }

    public List<WorkRangesByProject> getProjectsById(int id) {
        return workRangesByProjects.get(id);
    }

    public long getMaxWorkingDays(Employee otherEmployee) {
        if (this.id == otherEmployee.id) return 0L;
        long maxWorkingDays = 0;

        for (Map.Entry<Integer, List<WorkRangesByProject>> project : workRangesByProjects.entrySet()) {
            List<WorkRangesByProject> otherEmployeeProjectContracts =
                    otherEmployee.getProjectsById(project.getKey());

            maxWorkingDays += WorkRangesByProject.getMaximumWorkingDaysByProject(project.getValue(),
                    otherEmployeeProjectContracts);
        }
        return maxWorkingDays;
    }

    public void addProjectFromRecord(Record record) {
        WorkRangesByProject WorkRange = new WorkRangesByProject(record.getStartDate(), record.getEndDate());

        if (workRangesByProjects.containsKey(record.getProjectId())) {
            workRangesByProjects.get(record.getProjectId()).add(WorkRange);
        } else {
            List<WorkRangesByProject> Project = new ArrayList<>();

            Project.add(WorkRange);
            workRangesByProjects.put(record.getProjectId(), Project);
        }
    }
}
