package jp.ikeyat.tools.javadoc2xls.report.writer;

import jp.ikeyat.tools.javadoc2xls.doc.MethodDocBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ikeya on 15/01/18.
 */
public class DateCellWriter extends CellWriter {
    public static final String DATE = "#date#";
    private String date;

    public DateCellWriter(String type, int columnIndex) {
        super(DATE, columnIndex);
        String formatText = type.replace(DATE, "");
        DateFormat format = new SimpleDateFormat(formatText);
        date = format.format(new Date());
    }

    @Override
    protected String getText(MethodDocBean methodDocBean) {
        return date;
    }

    public static boolean isDateType(String type) {
        if (type != null) {
            return type.startsWith(DateCellWriter.DATE);
        }
        return false;
    }
}
