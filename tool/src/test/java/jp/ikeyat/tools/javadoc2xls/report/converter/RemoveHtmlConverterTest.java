package jp.ikeyat.tools.javadoc2xls.report.converter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveHtmlConverterTest {
    @Test
    public void testBrReplaced_001() {
        Converter converter = new RemoveHtmlConverter();
        String result = converter.convert("hoge<br>fuga");
        assertThat(result, is("hoge\r\nfuga"));
    }

    @Test
    public void testBrReplaced_002() {
        Converter converter = new RemoveHtmlConverter();
        String result = converter.convert("hoge<br/>fuga");
        assertThat(result, is("hoge\r\nfuga"));
    }

    @Test
    public void testBrReplaced_003() {
        Converter converter = new RemoveHtmlConverter();
        String result = converter.convert("hoge<br />fuga");
        assertThat(result, is("hoge\r\nfuga"));
    }

    @Test
    public void testTagRemoved_001() {
        Converter converter = new RemoveHtmlConverter();
        String result = converter.convert("<h1>hoge</h1><b>fuga</b>");
        assertThat(result, is("hogefuga"));
    }
}
