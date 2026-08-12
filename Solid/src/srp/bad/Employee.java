package srp.bad;

// BAD: This class has multiple reasons to change:
// 1. If employee data changes (name, salary)
// 2. If the database changes (MySQL -> PostgreSQL)
// 3. If the report format changes (text -> PDF)
public class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Responsibility 1: Employee data
    public String getName() { return name; }
    public double getSalary() { return salary; }

    // Responsibility 2: Database logic (should NOT be here)
    public void saveToDatabase() {
        System.out.println("Connecting to DB...");
        System.out.println("INSERT INTO employees VALUES ('" + name + "', " + salary + ")");
    }

    // Responsibility 3: Report generation (should NOT be here)
    public void generateReport() {
        System.out.println("=== Employee Report ===");
        System.out.println("Name:   " + name);
        System.out.println("Salary: $" + salary);
    }
}