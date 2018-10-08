package jp.ikeyat.tools.javadoc2xls;

import com.sun.tools.doclets.formats.html.HtmlDoclet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ikeya on 15/01/18.
 */
public class Options {
    private static final String OPTION_TEMPLATE = "-template";
    private static final String OPTION_REPORTDIR = "-reportdir";
    private static final String OPTION_REPORTFILENAME = "-reportfilename";
    private static final String OPTION_SORTTAG = "-sorttag";
    private static final String OPTION_SHEETINDEX = "-sheetindex";


    private Map<String, Set<String>> optionsMap = null;

    private String templateFilePath;
    private String reportDir;
    private String reportFileName;
    private String sortTag;
    private int sheetIndex;

    public Options(String[][] options) {
        optionsMap = newHashMap(options);
        templateFilePath = require(OPTION_TEMPLATE).iterator().next();
        reportDir = require(OPTION_REPORTDIR).iterator().next();
        reportFileName = require(OPTION_REPORTFILENAME).iterator().next();
        sortTag = optional(OPTION_SORTTAG).iterator().next();
        sheetIndex = Integer.parseInt(require(OPTION_SHEETINDEX).iterator().next());
    }

    private Map<String, Set<String>> newHashMap(String[][] arrays) {
        // memo: Multimap cannot be used for this.
        Map<String, Set<String>> ret = new HashMap<String, Set<String>>();

        for (String[] array : arrays) {
            Set<String> values = null;
            for (String value : array) {
                if (values == null) {
                    // add key
                    values = new HashSet<String>();
                    ret.put(value, values);
                } else {
                    // add value
                    values.add(value);
                }
            }
        }
        return ret;
    }

    private Set<String> require(String key) {
        Set<String> ret = optionsMap.get(key);
        if (ret == null || ret.size() == 0) {
            throw new IllegalArgumentException("option [" + key + "] is required.");
        }
        return ret;
    }

    private Set<String> optional(String key) {
        Set<String> ret = optionsMap.get(key);
        if (ret == null || ret.size() == 0) {
            ret = new HashSet<String>();
            ret.add("");
        }
        return ret;
    }

    public String getReportDir() {
        return reportDir;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public String getSortTag() {
        return sortTag;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public static int optionLength(String option) {
        if (option.equals(OPTION_TEMPLATE)) return 2;
        if (option.equals(OPTION_REPORTDIR)) return 2;
        if (option.equals(OPTION_REPORTFILENAME)) return 2;
        if (option.equals(OPTION_SORTTAG)) return 2;
        if (option.equals(OPTION_SHEETINDEX)) return 2;
        return HtmlDoclet.optionLength(option);
    }
}
