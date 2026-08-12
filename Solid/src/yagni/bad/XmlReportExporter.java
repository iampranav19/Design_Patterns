package yagni.bad;

// BAD: Same story — speculative "future-proofing" for a requirement that
// doesn't exist yet, and may never exist.
public class XmlReportExporter implements ReportExporter {

    @Override
    public void export(String data) {
        System.out.println("Exporting as XML: " + data);
    }
}