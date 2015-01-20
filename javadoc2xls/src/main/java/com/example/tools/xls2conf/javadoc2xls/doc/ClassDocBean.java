package com.example.tools.xls2conf.javadoc2xls.doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ikeya on 15/01/18.
 */
public class ClassDocBean {
    private class MethodComparator implements Comparator<MethodDocBean> {
        private final Logger logger = LoggerFactory.getLogger(MethodComparator.class);
        private String sortTag;

        public MethodComparator(String sortTag) {
            this.sortTag = sortTag;
        }

        public int compare(MethodDocBean methodDocBean, MethodDocBean methodDocBean2) {
            String key = methodDocBean.getId();
            String key2 = methodDocBean2.getId();
            int ret = key.compareTo(key2);

            if (sortTag != null && sortTag.length() != 0) {
                key = methodDocBean.getTags().get(sortTag);
                key2 = methodDocBean2.getTags().get(sortTag);
            }
            if (key != null) {
                ret = key.compareTo(key2);
            }
            return ret;
        }
    }

    private String id;
    private String className;
    private String reportFileName;
    private Set<MethodDocBean> methodDocs;

    public ClassDocBean(String id, String className, String sortTag, String reportFileName) {
        this.id = id;
        this.className = className;
        this.reportFileName = reportFileName;
        this.methodDocs = new TreeSet<>(new MethodComparator(sortTag));
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public Set<MethodDocBean> getMethodDocs() {
        return methodDocs;
    }
}
