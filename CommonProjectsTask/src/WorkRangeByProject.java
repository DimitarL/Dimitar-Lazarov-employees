import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkRangeByProject {
    private final Date startDate;
    private final Date endDate;

    public WorkRangeByProject(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public static long getMaximumWorkingDaysByProject(List<WorkRangeByProject> firstEmployeeWorkRanges,
            List<WorkRangeByProject> secondEmployeeWorkRanges) {

        if (firstEmployeeWorkRanges == null || secondEmployeeWorkRanges == null) {
            return 0L;
        }

        long maxWorkingDays = 0;
        for (WorkRangeByProject workRange : firstEmployeeWorkRanges) {
            maxWorkingDays += secondEmployeeWorkRanges.stream()
                    .mapToLong(range -> getDaysIntersection(workRange, range))
                    .sum();
        }

        return maxWorkingDays;
    }

    public static long getDaysIntersection(WorkRangeByProject firstWorkRange, WorkRangeByProject secondWorkRange) {
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
