package yagni.good;

public class Main {

    public static void main(String[] args) {
        CsvReportExporter exporter = new CsvReportExporter();
        exporter.export("quarterly-sales-report");
    }
}