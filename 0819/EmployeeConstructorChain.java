public class EmployeeConstructorChain {

    public static void main(String[] args) {

        System.out.println("=== Full Time Employee ===");

        EmployeeBase emp1 =
                new FullTimeEmployee(
                        "F001",
                        "Amy",
                        50000);

        System.out.println(
                "Salary = "
                        + emp1.calculatePay());

        System.out.println();

        System.out.println("=== Part Time Employee ===");

        EmployeeBase emp2 =
                new PartTimeEmployee(
                        "P001",
                        "John",
                        200,
                        80);

        System.out.println(
                "Salary = "
                        + emp2.calculatePay());

        System.out.println();

        System.out.println("=== Boundary Test ===");

        EmployeeBase emp3 =
                new FullTimeEmployee(
                        "F002",
                        "Mary",
                        -30000);

        System.out.println(
                "Salary = "
                        + emp3.calculatePay());

        EmployeeBase emp4 =
                new PartTimeEmployee(
                        "P002",
                        "Tom",
                        -100,
                        -20);

        System.out.println(
                "Salary = "
                        + emp4.calculatePay());
    }
}

// 抽象父類別
abstract class EmployeeBase {

    protected String id;
    protected String name;

    public EmployeeBase(
            String id,
            String name) {

        System.out.println(
                "EmployeeBase Constructor");

        this.id = id;
        this.name = name;
    }

    public abstract double calculatePay();
}

// 正職員工
class FullTimeEmployee
        extends EmployeeBase {

    private double monthlySalary;

    public FullTimeEmployee(
            String id,
            String name,
            double monthlySalary) {

        super(id, name);

        System.out.println(
                "FullTimeEmployee Constructor");

        this.monthlySalary =
                Math.max(0, monthlySalary);
    }

    @Override
    public double calculatePay() {

        return monthlySalary;
    }
}

// 兼職員工
class PartTimeEmployee
        extends EmployeeBase {

    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(
            String id,
            String name,
            double hourlyRate,
            int hoursWorked) {

        super(id, name);

        System.out.println(
                "PartTimeEmployee Constructor");

        this.hourlyRate =
                Math.max(0, hourlyRate);

        this.hoursWorked =
                Math.max(0, hoursWorked);
    }

    @Override
    public double calculatePay() {

        return hourlyRate * hoursWorked;
    }
}