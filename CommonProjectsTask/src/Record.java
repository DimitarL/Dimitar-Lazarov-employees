import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Record {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final int employeeId;
    private final int projectId;
    private final Date startDate;
    private final Date endDate;

    public Record(String line) throws ParseException {
        String[] separatedLine = line.split(",");

        Date startDate, endDate;

        if (separatedLine[2].equals("NULL")) {
            startDate = new Date();
        } else {
            startDate = DATE_FORMAT.parse(separatedLine[2]);
        }

        if (separatedLine[3].equals("NULL")) {
            endDate = new Date();
        } else {
            endDate = DATE_FORMAT.parse(separatedLine[3]);
        }

        Date currentDate = new Date();
        if (startDate.after(endDate) || startDate.after(currentDate) || endDate.after(currentDate)) {
            throw new RuntimeException("There is a wrong date range.");
        }

        this.employeeId = Integer.parseInt(separatedLine[0]);
        this.projectId = Integer.parseInt(separatedLine[1]);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
