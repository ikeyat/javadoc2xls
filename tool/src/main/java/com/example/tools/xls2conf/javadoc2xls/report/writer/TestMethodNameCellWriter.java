package com.example.tools.xls2conf.javadoc2xls.report.writer;

import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;

/**
 * Created by ikeya on 15/01/18.
 */
public class TestMethodNameCellWriter extends CellWriter {
    public static final String METHOD_NAME = "#methodName";

    public TestMethodNameCellWriter(int columnIndex) {
        super(METHOD_NAME, columnIndex);
    }

    @Override
    protected String getText(MethodDocBean methodDocBean) {
        String methodName = methodDocBean.getMethodName();
        return methodName;
    }

    public static boolean isTestMethodNameType(String type) {
        return TestMethodNameCellWriter.METHOD_NAME.equals(type);
    }
}
