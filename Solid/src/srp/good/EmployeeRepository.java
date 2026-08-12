package srp.good;

// GOOD: Only responsible for database operations.
// Only changes if the database/persistence logic changes.
public class EmployeeRepository {

    public void save(Employee employee) {
        System.out.println("Connecting to DB...");
        System.out.println("INSERT INTO employees VALUES ('" + employee.getName() + "', " + employee.getSalary() + ")");
    }
}