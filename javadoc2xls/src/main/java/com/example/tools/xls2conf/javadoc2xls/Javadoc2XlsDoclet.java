package com.example.tools.xls2conf.javadoc2xls;

import com.example.tools.xls2conf.javadoc2xls.doc.ClassDocBean;
import com.example.tools.xls2conf.javadoc2xls.doc.MethodDocBean;
import com.example.tools.xls2conf.javadoc2xls.report.TestListBook;
import com.sun.javadoc.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by ikeya on 15/01/13.
 */
public class Javadoc2XlsDoclet {
    private static final Logger logger = LoggerFactory.getLogger(Javadoc2XlsDoclet.class);
    private static Options options = null;

    // entry point
    public static boolean start(RootDoc rootDoc) {
        options = new Options(rootDoc.options());
        execute(rootDoc);
        return true;
    }

    public static int optionLength(String option) {
        logger.debug("option detected: {}", option);
        return Options.optionLength(option);
    }

    public static LanguageVersion languageVersion() {
        return LanguageVersion.JAVA_1_5;
    }

    private static void execute(RootDoc rootDoc) {
        String reportDir = options.getReportDir();
        String templateFilePath = options.getTemplateFilePath();
        String sortTag = options.getSortTag();
        int sheetIndex = options.getSheetIndex();

        int classCount = 0;
        // body
        for (ClassDoc classDoc : rootDoc.classes()) {
            String classId = generateId(classCount++);
            String reportFileName = generateReportFileName(classId, classDoc.name());
            ClassDocBean classDocBean = new ClassDocBean(
                    classId,
                    classDoc.qualifiedName(),
                    sortTag,
                    reportFileName);

            int methodCount = 0;
            for (MethodDoc methodDoc : classDoc.methods(true)) {
                String methodId = generateId(methodCount);
                MethodDocBean methodDocBean = new MethodDocBean(
                        methodId,
                        methodDoc.name()
                );
                parseAnnotations(methodDoc, methodDocBean);
                classDocBean.getMethodDocs().add(methodDocBean);
            }

            File file = new File(reportDir + "/" + classDocBean.getReportFileName());
            try (OutputStream outputStream = new FileOutputStream(file)) {
                TestListBook book = new TestListBook(templateFilePath, sheetIndex);

                for (MethodDocBean methodDocBean : classDocBean.getMethodDocs()) {
                    book.write(methodDocBean);
                }
                book.saveToFile(outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String generateId(int count) {
        return String.format("%05d", count);
    }

    /**
     * Generate report file name.
     * Replace {0} and {1} to classId and className on reportfilename given as Doclet option.
     *
     * @param classId
     * @param className
     * @return
     */
    private static String generateReportFileName(String classId, String className) {
        String ret = options.getReportFileName();
        return MessageFormat.format(ret, classId, className);
    }

    private static void parseAnnotations(MethodDoc methodDoc, MethodDocBean methodDocBean) {
        // TODO: extract only methods that have @Test annotation
        Map<String, String> tagsMap = methodDocBean.getTags();
        Tag[] tags = methodDoc.tags();
        for (Tag tag : tags) {
            String tagName = tag.name();
            String tagText = tag.text().replaceAll(tagName + ":", "");
            tagsMap.put(tagName, tagText);
        }
    }
}
