package yagni.bad;

public class Main {

    public static void main(String[] args) {
        // The only format the product has ever actually requested is CSV...
        ReportExporter exporter = ReportExporterFactory.create("CSV");
        exporter.export("quarterly-sales-report");

        // ...but PdfReportExporter, XmlReportExporter, and ExcelReportExporter
        // sit in the codebase unused, untested, and drifting out of date every
        // time the report's data model changes. Built for a "someday" that
        // may never come.
    }
}