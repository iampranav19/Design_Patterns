package yagni.bad;

// BAD: Nobody asked for PDF export. Built "just in case" — untested, unused,
// and one more class that has to be updated every time the report format changes.
public class PdfReportExporter implements ReportExporter {

    @Override
    public void export(String data) {
        System.out.println("Exporting as PDF: " + data);
    }
}