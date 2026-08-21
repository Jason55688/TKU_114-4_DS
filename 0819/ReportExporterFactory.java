public class ReportExporterFactory {

    public static void main(String[] args) {

        int[] salesData = {120, 250, 180, 300};

        ReportExporter csv =
                createExporter("csv");

        ReportExporter json =
                createExporter("json");

        ReportExporter text =
                createExporter("text");

        ReportExporter unknown =
                createExporter("xml");

        exportReport(
                csv,
                "Monthly Sales",
                salesData);

        System.out.println();

        exportReport(
                json,
                "Monthly Sales",
                salesData);

        System.out.println();

        exportReport(
                text,
                "Monthly Sales",
                salesData);

        System.out.println();

        exportReport(
                unknown,
                "Monthly Sales",
                salesData);

        System.out.println();

        // null 測試
        exportReport(
                csv,
                "Null Data Test",
                null);
    }

    // Factory Method
    public static ReportExporter createExporter(
            String format) {

        if (format == null) {
            return new TextExporter();
        }

        switch (format.toLowerCase()) {

            case "csv":
                return new CsvExporter();

            case "json":
                return new JsonExporter();

            case "text":
                return new TextExporter();

            default:
                return new TextExporter();
        }
    }

    // 僅依賴 Interface
    public static void exportReport(
            ReportExporter exporter,
            String title,
            int[] values) {

        exporter.export(title, values);
    }
}

// Interface
interface ReportExporter {

    void export(
            String title,
            int[] values);
}

// CSV
class CsvExporter
        implements ReportExporter {

    @Override
    public void export(
            String title,
            int[] values) {

        System.out.println("[CSV Export]");
        System.out.println("Title: " + title);

        if (values == null) {
            System.out.println("No Data");
            return;
        }

        for (int i = 0; i < values.length; i++) {

            System.out.print(values[i]);

            if (i < values.length - 1) {
                System.out.print(",");
            }
        }

        System.out.println();
    }
}

// JSON
class JsonExporter
        implements ReportExporter {

    @Override
    public void export(
            String title,
            int[] values) {

        System.out.println("[JSON Export]");

        if (values == null) {

            System.out.println(
                    "{ \"title\":\""
                            + title
                            + "\", \"values\":[] }");

            return;
        }

        System.out.print(
                "{ \"title\":\""
                        + title
                        + "\", \"values\":[");

        for (int i = 0; i < values.length; i++) {

            System.out.print(values[i]);

            if (i < values.length - 1) {
                System.out.print(",");
            }
        }

        System.out.println("] }");
    }
}

// Text
class TextExporter
        implements ReportExporter {

    @Override
    public void export(
            String title,
            int[] values) {

        System.out.println("[TEXT Export]");
        System.out.println("Title: " + title);

        if (values == null) {
            System.out.println("No Data");
            return;
        }

        for (int value : values) {
            System.out.println(value);
        }
    }
}