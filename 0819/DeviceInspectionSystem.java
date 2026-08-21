public class DeviceInspectionSystem {

    public static void main(String[] args) {

        Device[] devices = {

                new Laptop("Dell XPS"),
                new Printer("HP LaserJet"),
                new Router("ASUS AX3000"),
                new Printer("Canon G3010")
        };

        System.out.println("=== Device Diagnostic ===");

        for (Device device : devices) {

            // 多型呼叫
            device.runDiagnostic();

            // Pattern Matching instanceof
            if (device instanceof Printer printer) {

                printer.cleanPrintHead();
            }

            System.out.println();
        }
    }
}

// 父類別
abstract class Device {

    protected String deviceName;

    public Device(String deviceName) {
        this.deviceName = deviceName;
    }

    public abstract void runDiagnostic();
}

// Laptop
class Laptop extends Device {

    public Laptop(String deviceName) {
        super(deviceName);
    }

    @Override
    public void runDiagnostic() {

        System.out.println(
                "Laptop [" + deviceName +
                "] Diagnostic Passed.");
    }
}

// Printer
class Printer extends Device {

    public Printer(String deviceName) {
        super(deviceName);
    }

    @Override
    public void runDiagnostic() {

        System.out.println(
                "Printer [" + deviceName +
                "] Diagnostic 