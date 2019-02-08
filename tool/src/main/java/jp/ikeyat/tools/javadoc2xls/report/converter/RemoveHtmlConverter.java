package jp.ikeyat.tools.javadoc2xls.report.converter;

public class RemoveHtmlConverter implements Converter {
    public String convert(String source) {
        source = source.replaceAll("<br>", "\r\n");
        source = source.replaceAll("<br */>", "\r\n");
        source = source.replaceAll("<BR>", "\r\n");
        source = source.replaceAll("<BR */>", "\r\n");
        source = source.replaceAll("<[^<>]*>", "");
        return source;
    }
}
