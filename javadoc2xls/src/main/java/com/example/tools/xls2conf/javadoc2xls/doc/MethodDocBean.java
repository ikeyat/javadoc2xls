package com.example.tools.xls2conf.javadoc2xls.doc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ikeya on 15/01/18.
 */
public class MethodDocBean {
    private String id;
    private String methodName;
    private Map<String, String> tags;

    public MethodDocBean(String id, String methodName) {
        this.id = id;
        this.methodName = methodName;
        this.tags = new HashMap<String, String>();
    }

    public String getId() {
        return id;
    }

    public String getMethodName() {
        return methodName;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
