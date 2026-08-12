package yagni.bad;

public class CsvReportExporter implements ReportExporter {

    @Override
    public void export(String data) {
        System.out.println("Exporting as CSV: " + data);
    }
}