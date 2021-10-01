public class EmployeePair {
    private Employee employee1;
    private Employee employee2;

    public EmployeePair() {
        assert employee1 != null;
        assert employee2 != null;
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public void setEmployee1(Employee employeeId) {
        this.employee1 = employeeId;
    }

    public void setEmployee2(Employee employeeId) {
        this.employee2 = employeeId;
    }
}
