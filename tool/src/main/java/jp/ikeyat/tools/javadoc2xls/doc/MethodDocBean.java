package jp.ikeyat.tools.javadoc2xls.doc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ikeya on 15/01/18.
 */
public class MethodDocBean {
    private String id;
    private String methodName;
    private String commentText;
    private Map<String, String> tags;

    public MethodDocBean(String id, String methodName, String commentText) {
        this.id = id;
        this.methodName = methodName;
        this.commentText = commentText;
        this.tags = new HashMap<String, String>();
    }

    public String getId() {
        return id;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCommentText() {
        return commentText;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
