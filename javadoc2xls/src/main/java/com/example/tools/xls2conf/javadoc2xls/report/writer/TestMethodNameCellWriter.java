package com.example.tools.xls2conf.javadoc2xls.report.writer;

import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;

/**
 * Created by ikeya on 15/01/18.
 */
public class TestMethodNameCellWriter extends CellWriter {
    public static final String TEST_METHOD_NAME = "#testMethodName";

    public TestMethodNameCellWriter(int columnIndex) {
        super(TEST_METHOD_NAME, columnIndex);
    }

    @Override
    protected String getText(MethodDocBean methodDocBean) {
        String testMethodName = methodDocBean.getMethodName();
        return testMethodName;
    }

    public static boolean isTestMethodNameType(String type) {
        return TestMethodNameCellWriter.TEST_METHOD_NAME.equals(type);
    }
}
