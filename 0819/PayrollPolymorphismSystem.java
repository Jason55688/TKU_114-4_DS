public class PayrollPolymorphismSystem {

    public static void main(String[] args) {

        Employee[] employees = {

                new SalariedEmployee(
                        "E001",
                        "Amy",
                        50000),

                new HourlyEmployee(
                        "E002",
                        "John",
                        250,
                        160),

                new SalesEmployee(
                        "E003",
                        "Mary",
                        30000,
                        200000,
                        0.05),

                new HourlyEmployee(
                        "E004",
                        "David",
                        300,
                        120)
        };

        double totalPayroll = 0;
        Employee highestPaid = employees[0];

        System.out.println("=== Salary Report ===\n");

        for (Employee employee : employees) {

            double pay = employee.calculatePay();

            System.out.println(
                    employee.getId()
                            + " | "
                            + employee.getName()
                            + " | Salary = "
                            + pay);

            totalPayroll += pay;

            if (pay > highestPaid.calculatePay()) {
                highestPaid = employee;
            }
        }

        System.out.println("\n=== Summary ===");

        System.out.println(
                "Total Payroll = "
                        + totalPayroll);

        System.out.println(
                "Highest Paid Employee : "
                        + highestPaid.getName());

        System.out.println(
                "Highest Salary = "
                        + highestPaid.calculatePay());
    }
}

// 抽象父類別
abstract class Employee {

    protected String id;
    protected String name;

    public Employee(String id, String name) {

        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

// 月薪員工
class SalariedEmployee extends Employee {

    private double monthlySalary;

    public SalariedEmployee(
            String id,
            String name,
            double monthlySalary) {

        super(id, name);

        this.monthlySalary =
                Math.max(0, monthlySalary);
    }

    @Override
    public double calculatePay() {

        return monthlySalary;
    }
}

// 時薪員工
class HourlyEmployee extends Employee {

    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(
            String id,
            String name,
            double hourlyRate,
            int hoursWorked) {

        super(id, name);

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

// 業務員工
class SalesEmployee extends Employee {

    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public SalesEmployee(
            String id,
            String name,
            double baseSalary,
            double salesAmount,
            double commissionRate) {

        super(id, name);

        this.baseSalary =
                Math.max(0, baseSalary);

        this.salesAmount =
                Math.max(0, salesAmount);

        this.commissionRate =
                Math.max(0, commissionRate);
    }

    @Override
    public double calculatePay() {

        return baseSalary
                + salesAmount * commissionRate;
    }
}