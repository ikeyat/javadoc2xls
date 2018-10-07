package jp.ikeyat.tools.javadoc2xls.report.writer.once;

import jp.ikeyat.tools.javadoc2xls.doc.ClassDocBean;

import java.util.Map;

/**
 * Created by ikeya on 15/01/18.
 */
public class TagOnceCellWriter extends OnceCellWriter {
    public TagOnceCellWriter(String type) {
        super(type);
    }

    @Override
    protected String getText(ClassDocBean classDocBean) {
        Map<String, String> tags = classDocBean.getTags();
        String tagText = tags.get(getType());
        return tagText;
    }
}
