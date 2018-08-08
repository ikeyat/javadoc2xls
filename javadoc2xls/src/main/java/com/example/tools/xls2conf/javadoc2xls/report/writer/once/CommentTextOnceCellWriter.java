package com.example.tools.xls2conf.javadoc2xls.report.writer.once;

import com.example.tools.xls2conf.javadoc2xls.doc.ClassDocBean;
import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;
import com.example.tools.xls2conf.javadoc2xls.report.writer.CellWriter;
import com.example.tools.xls2conf.javadoc2xls.report.writer.CommentTextCellWriter;

/**
 * Created by ikeya on 15/01/18.
 */
public class CommentTextOnceCellWriter extends OnceCellWriter {
    public static final String COMMENT_TEXT = CommentTextCellWriter.COMMENT_TEXT;

    public CommentTextOnceCellWriter() {
        super(COMMENT_TEXT);
    }

    @Override
    protected String getText(ClassDocBean classDocBean) {
        String commentText = classDocBean.getCommentText();
        return commentText;
    }

    public static boolean isCommentTextType(String type) {
        return COMMENT_TEXT.equals(type);
    }
}
