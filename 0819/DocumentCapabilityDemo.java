public class DocumentCapabilityDemo {

    public static void main(String[] args) {

        BackupDocument document =
                new BackupDocument("FinalReport.docx");

        // 使用 Exportable reference
        Exportable exportRef = document;

        // 使用 Compressible reference
        Compressible compressRef = document;

        System.out.println("=== Export Function ===");
        exportRef.export();

        System.out.println();

        System.out.println("=== Compress Function ===");
        compressRef.compress();

        System.out.println();

        // 證明兩個 reference 指向同一物件
        System.out.println(
                "Same Object: "
                        + (exportRef == compressRef));

        System.out.println();

        System.out.println(
                "exportRef 型別：Exportable");

        System.out.println(
                "compressRef 型別：Compressible");

        System.out.println(
                "兩者指向同一個 BackupDocument 物件");
    }
}

// Interface 1
interface Exportable {

    void export();
}

// Interface 2
interface Compressible {

    void compress();
}

// 同時實作兩個 Interface
class BackupDocument
        implements Exportable, Compressible {

    private String fileName;

    public BackupDocument(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void export() {

        System.out.println(
                "Exporting file: "
                        + fileName);
    }

    @Override
    public void compress() {

        System.out.println(
                "Compressing file: "
                        + fileName);
    }
}