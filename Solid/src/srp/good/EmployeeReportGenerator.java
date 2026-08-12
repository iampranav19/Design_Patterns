package srp.good;

// GOOD: Only responsible for generating reports.
// Only changes if the report format changes.
public class EmployeeReportGenerator {

    public void generate(Employee employee) {
        System.out.println("=== Employee Report ===");
        System.out.println("Name:   " + employee.getName());
        System.out.println("Salary: $" + employee.getSalary());
    }
}