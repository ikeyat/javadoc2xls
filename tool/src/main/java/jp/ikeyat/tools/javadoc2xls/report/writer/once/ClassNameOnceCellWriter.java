package jp.ikeyat.tools.javadoc2xls.report.writer.once;

import jp.ikeyat.tools.javadoc2xls.doc.ClassDocBean;

/**
 * Created by ikeya on 15/01/18.
 */
public class ClassNameOnceCellWriter extends OnceCellWriter {
    public static final String METHOD_NAME = "#className";

    public ClassNameOnceCellWriter() {
        super(METHOD_NAME);
    }

    @Override
    protected String getText(ClassDocBean classDocBean) {
        String methodName = classDocBean.getClassName();
        return methodName;
    }

    public static boolean isTestMethodNameType(String type) {
        return METHOD_NAME.equals(type);
    }
}
