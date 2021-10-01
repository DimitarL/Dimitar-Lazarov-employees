public class EmployeePair<Employee1, Employee2> {
    private Employee1 employee1;
    private Employee2 employee2;

    public EmployeePair(Employee1 employee1, Employee2 employee2) {
        this.employee1 = employee1;
        this.employee2 = employee2;
        assert employee1 != null;
        assert employee2 != null;
    }

    public Employee1 getEmployee1() {
        return employee1;
    }

    public Employee2 getEmployee2() {
        return employee2;
    }

    public void setEmployee1(Employee1 employeeId) {
        this.employee1 = employeeId;
    }

    public void setEmployee2(Employee2 employeeId) {
        this.employee2 = employeeId;
    }
}
