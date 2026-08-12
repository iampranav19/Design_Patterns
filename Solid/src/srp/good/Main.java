package srp.good;

public class Main {

    public static void main(String[] args) {
        Employee employee = new Employee("Alice", 75000);

        EmployeeRepository repository = new EmployeeRepository();
        repository.save(employee);

        EmployeeReportGenerator reporter = new EmployeeReportGenerator();
        reporter.generate(employee);
    }
}