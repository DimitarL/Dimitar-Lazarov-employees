import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkRangesByProject {
    private final Date startDate;
    private final Date endDate;

    public WorkRangesByProject(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public static long getMaximumWorkingDaysByProject(
            List<WorkRangesByProject> firstEmployeeWorkRanges,
            List<WorkRangesByProject> secondEmployeeWorkRanges) {

        if (firstEmployeeWorkRanges == null || secondEmployeeWorkRanges == null) {
            return 0L;
        }

        long maxWorkingDays = 0;
        for (WorkRangesByProject workRange : firstEmployeeWorkRanges) {
            maxWorkingDays += secondEmployeeWorkRanges.stream().mapToLong(days -> getDaysIntersection(workRange, days))
                    .sum();
        }

        return maxWorkingDays;
    }

    public static long getDaysIntersection(WorkRangesByProject firstWorkRange, WorkRangesByProject secondWorkRange) {
        Date fDateStart = firstWorkRange.getStartDate();
        Date fDateEnd = firstWorkRange.getEndDate();

        Date sDateStart = secondWorkRange.getStartDate();
        Date sDateEnd = secondWorkRange.getEndDate();

        long commonWorkingDays;
        if (fDateStart.after(sDateStart)) {
            if (fDateEnd.after(sDateEnd)) {
                commonWorkingDays = getDifferenceInTime(fDateStart, sDateEnd);
            } else {
                commonWorkingDays = getDifferenceInTime(fDateStart, fDateEnd);
            }
        } else {
            if (fDateEnd.after(sDateEnd)) {
                commonWorkingDays = getDifferenceInTime(sDateStart, sDateEnd);
            } else {
                commonWorkingDays = getDifferenceInTime(sDateStart, fDateEnd);
            }
        }

        return commonWorkingDays;
    }

    public static long getDifferenceInTime(Date firstDate, Date secondDate) {
        long differenceInMilliseconds = Math.abs(firstDate.getTime() - secondDate.getTime());

        return TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
    }
}
