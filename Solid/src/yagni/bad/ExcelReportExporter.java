package yagni.bad;

// BAD: Same story again.
public class ExcelReportExporter implements ReportExporter {

    @Override
    public void export(String data) {
        System.out.println("Exporting as Excel: " + data);
    }
}