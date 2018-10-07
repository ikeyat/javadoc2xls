package com.example.tools.xls2conf.javadoc2xls.report.writer;

import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ikeya on 15/01/18.
 */
public abstract class CellWriter {
    private static final Logger logger = LoggerFactory.getLogger(CellWriter.class);

    private String type;
    private int columnIndex;

    public CellWriter(String type, int columnIndex) {
        this.type = type;
        this.columnIndex = columnIndex;
    }

    public void write(Row row, MethodDocBean methodDocBean) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }
        String text = getText(methodDocBean);
        cell.setCellValue(text);
        logger.debug("write at column={}: {}", columnIndex, text);
    }

    public String getType() {
        return type;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    protected abstract String getText(MethodDocBean methodDocBean);
}
