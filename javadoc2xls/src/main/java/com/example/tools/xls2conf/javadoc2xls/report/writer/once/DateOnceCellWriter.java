package com.example.tools.xls2conf.javadoc2xls.report.writer.once;

import com.example.tools.xls2conf.javadoc2xls.doc.ClassDocBean;
import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;
import com.example.tools.xls2conf.javadoc2xls.report.writer.CellWriter;
import com.example.tools.xls2conf.javadoc2xls.report.writer.DateCellWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ikeya on 15/01/18.
 */
public class DateOnceCellWriter extends OnceCellWriter {
    public static final String DATE = DateCellWriter.DATE;
    private String date;

    public DateOnceCellWriter(String type) {
        super(DATE);
        String formatText = type.replace(DATE, "");
        DateFormat format = new SimpleDateFormat(formatText);
        date = format.format(new Date());
    }

    @Override
    protected String getText(ClassDocBean classDocBean) {
        return date;
    }

    public static boolean isDateType(String type) {
        if (type != null) {
            return type.startsWith(DATE);
        }
        return false;
    }
}
