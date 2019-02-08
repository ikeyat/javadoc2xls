package jp.ikeyat.tools.javadoc2xls.report.writer.once;

import jp.ikeyat.tools.javadoc2xls.doc.ClassDocBean;
import jp.ikeyat.tools.javadoc2xls.report.converter.Converter;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ikeya on 15/01/18.
 */
public abstract class OnceCellWriter {
    private static final Logger logger = LoggerFactory.getLogger(OnceCellWriter.class);

    private String type;

    public OnceCellWriter(String type) {
        this.type = type;
    }

    public void write(Cell cell, ClassDocBean classDocBean, Converter converter) {
        String text = getText(classDocBean);
        text = converter.convert(text);
        cell.setCellValue(text);
        logger.debug("write onece at ({}, {}): {}", cell.getColumnIndex(), cell.getRowIndex(), text);
    }

    public String getType() {
        return type;
    }

    protected abstract String getText(ClassDocBean classDocBean);
}
