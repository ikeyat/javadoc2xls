package jp.ikeyat.tools.javadoc2xls;

import jp.ikeyat.tools.javadoc2xls.doc.ClassDocBean;
import jp.ikeyat.tools.javadoc2xls.doc.MethodDocBean;
import jp.ikeyat.tools.javadoc2xls.report.TestListBook;
import com.sun.javadoc.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
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
        int methodTotal = 0;
        // body
        for (ClassDoc classDoc : rootDoc.classes()) {
            String classId = generateId(classCount++);
            String reportFileName = generateReportFileName(classId, classDoc.name());
            ClassDocBean classDocBean = new ClassDocBean(
                    classId,
                    classDoc.qualifiedName(),
                    classDoc.commentText(),
                    sortTag,
                    reportFileName);
            parseClassTags(classDoc, classDocBean);

            int methodCount = 0;
            for (MethodDoc methodDoc : classDoc.methods(true)) {
                String methodId = generateId(methodCount++);
                methodTotal++;
                MethodDocBean methodDocBean = new MethodDocBean(
                        methodId,
                        methodDoc.name(),
                        methodDoc.commentText()
                );
                if (isTestAnnotated(methodDoc)) {
                    parseMethodTags(methodDoc, methodDocBean);
                    classDocBean.getMethodDocs().add(methodDocBean);
                }
            }

            if (classDocBean.getMethodDocs().size() == 0) {
                // skip
                continue;
            }

            File file = new File(reportDir + "/" + classDocBean.getReportFileName());
            try (OutputStream outputStream = new FileOutputStream(file)) {
                TestListBook book = new TestListBook(templateFilePath, sheetIndex);

                book.writeCell(classDocBean);
                for (MethodDocBean methodDocBean : classDocBean.getMethodDocs()) {
                    book.writeRow(methodDocBean);
                }
                book.saveToFile(outputStream);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            } catch (InvalidFormatException e) {
                throw new IllegalArgumentException(e);
            } finally {
                logger.debug("{} methods in {}", methodCount, classDocBean.getClassName());
            }
        }
        logger.debug("{} classes, {} methods", classCount, methodTotal);
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

    private static void parseClassTags(ClassDoc classDoc, ClassDocBean classDocBean) {
        parseTags(classDocBean.getTags(), classDoc.tags());
    }

    private static void parseMethodTags(MethodDoc methodDoc, MethodDocBean methodDocBean) {
        parseTags(methodDocBean.getTags(), methodDoc.tags());
    }

    private static void parseTags(Map<String, String> tagsMap, Tag[] tags) {
        for (Tag tag : tags) {
            String tagName = tag.name();
            String tagText = tag.text().replaceAll(tagName + ":", "");
            tagsMap.put(tagName, tagText);
        }
    }

    /**
     * Extract only methods that have @Test annotation
     *
     * @param methodDoc
     * @return
     */
    private static boolean isTestAnnotated(MethodDoc methodDoc) {
        String testAnnotationName = Test.class.getName();
        for (AnnotationDesc annotationDesc : methodDoc.annotations()) {
            if (testAnnotationName.equals(annotationDesc.annotationType().qualifiedTypeName())) {
                return true;
            }
        }
        return false;
    }
}
