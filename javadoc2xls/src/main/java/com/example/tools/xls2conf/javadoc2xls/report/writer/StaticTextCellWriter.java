package com.example.tools.xls2conf.javadoc2xls.report.writer;

import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;

/**
 * Created by ikeya on 15/01/18.
 */
public class StaticTextCellWriter extends CellWriter {
    public static final String STATIC_TEXT = "#staticText#";
    private String staticText;

    public StaticTextCellWriter(String type, int columnIndex) {
        super(STATIC_TEXT, columnIndex);
        staticText = type.replace(STATIC_TEXT, "");
    }

    @Override
    protected String getText(MethodDocBean methodDocBean) {
        return staticText;
    }

    public static boolean isStaticTextType(String type) {
        if (type != null) {
            return type.startsWith(StaticTextCellWriter.STATIC_TEXT);
        }
        return false;
    }
}
