package com.example.tools.xls2conf.javadoc2xls.report.writer;

import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;

/**
 * Created by ikeya on 15/01/18.
 */
public class CommentTextCellWriter extends CellWriter {
    public static final String COMMENT_TEXT = "#commentText";

    public CommentTextCellWriter(int columnIndex) {
        super(COMMENT_TEXT, columnIndex);
    }

    @Override
    protected String getText(MethodDocBean methodDocBean) {
        String commentText = methodDocBean.getCommentText();
        return commentText;
    }

    public static boolean isCommentTextType(String type) {
        return CommentTextCellWriter.COMMENT_TEXT.equals(type);
    }
}
