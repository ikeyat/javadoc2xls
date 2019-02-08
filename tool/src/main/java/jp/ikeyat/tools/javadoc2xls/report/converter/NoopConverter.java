package jp.ikeyat.tools.javadoc2xls.report.converter;

public class NoopConverter implements Converter {
    public String convert(String source) {
        return source;
    }
}
