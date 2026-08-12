package yagni.good;

// GOOD: Only what the current requirement actually needs. If PDF export gets
// requested next quarter, add PdfReportExporter then — not before.
public class CsvReportExporter {

    public void export(String data) {
        System.out.println("Exporting as CSV: " + data);
    }
}