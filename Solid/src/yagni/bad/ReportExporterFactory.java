package yagni.bad;

// BAD: A factory built to support 4 formats when the product only ever ships
// with 1. Every extra branch is dead weight: it has to be maintained, it can
// silently rot, and it was written for a requirement nobody has asked for.
public class ReportExporterFactory {

    public static ReportExporter create(String format) {
        switch (format) {
            case "CSV":
                return new CsvReportExporter();
            case "PDF":
                return new PdfReportExporter();
            case "XML":
                return new XmlReportExporter();
            case "EXCEL":
                return new ExcelReportExporter();
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
}