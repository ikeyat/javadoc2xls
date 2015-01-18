package com.example.tools.xls2conf.javadoc2xls.report.writer;

import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;

import java.util.Map;

/**
 * Created by ikeya on 15/01/18.
 */
public class TagCellWriter extends CellWriter {
    public TagCellWriter(String type, int columnIndex) {
        super(type, columnIndex);
    }

    @Override
    protected String getText(MethodDocBean methodDocBean) {
        Map<String, String> tags = methodDocBean.getTags();
        String tagText = tags.get(getType());
        return tagText;
    }
}
