import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

class Patient {

    private String patientId;
    private String name;

    public Patient(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return patientId + " - " + name;
    }
}

public class ClinicQueueSystem {

    private Deque<Patient> waitingQueue =
            new ArrayDeque<>();

    private List<Patient> completedList =
            new ArrayList<>();

    // 一般掛號
    public void register(Patient patient) {

        if (patient != null) {
            waitingQueue.offerLast(patient);
        }
    }

    // 查看下一位
    public Patient nextPatient() {

        return waitingQueue.peekFirst();
    }

    // 叫號
    public Patient callNext() {

        Patient patient =
                waitingQueue.pollFirst();

        if (patient != null) {
            completedList.add(patient);
        }

        return patient;
    }

    // 取消指定病歷號
    public boolean cancel(String patientId) {

        Iterator<Patient> iterator =
                waitingQueue.iterator();

        while (iterator.hasNext()) {

            Patient patient =
                    iterator.next();

            if (patient.getPatientId()
                    .equals(patientId)) {

                iterator.remove();
                return true;
            }
        }

        return false;
    }

    public void showCompleted() {

        System.out.println(
                "\n=== Completed Patients ===");

        if (completedList.isEmpty()) {

            System.out.println("No completed patients.");
            return;
        }

        for (Patient patient : completedList) {
            System.out.println(patient);
        }
    }

    public static void main(String[] args) {

        ClinicQueueSystem clinic =
                new ClinicQueueSystem();

        clinic.register(
                new Patient("P001", "Amy"));

        clinic.register(
                new Patient("P002", "Ben"));

        clinic.register(
                new Patient("P003", "Cara"));

        clinic.register(
                new Patient("P004", "David"));

        System.out.println(
                "Next Patient = "
                        + clinic.nextPatient());

        System.out.println(
                "\nCancel P003 = "
                        + clinic.cancel("P003"));

        System.out.println(
                "\nCall = "
                        + clinic.callNext());

        System.out.println(
                "Call = "
                        + clinic.callNext());

        System.out.println(
                "\nNext Patient = "
                        + clinic.nextPatient());

        System.out.println(
                "\nCall = "
                        + clinic.callNext());

        System.out.println(
                "Call = "
                        + clinic.callNext());

        // 空 Queue 測試
        System.out.println(
                "\nCall Empty = "
                        + clinic.callNext());

        System.out.println(
                "Next Empty = "
                        + clinic.nextPatient());

        clinic.showCompleted();
    }
}