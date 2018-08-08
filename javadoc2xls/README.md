# Motivation

This tool aim to generate test case documents (Excel worksheet) from javadoc on JUnit classes.
In general, test case documents are required in enterprise software developments.
It usually becomes very difficult to keep the consistency between test case documents and JUnit code when there are some modifications.
This tool will resolve this problem and keep the consistency with its one-way generation mechanisim.

# Get Started

## Setup

1. Please checkout or download this repos. (Because this tool is not registerd in MavenCentral)

1. Install with maven  
    ```console
    $ cd xls2-conf-parent
    $ mvn install
    $ cd ../xls2-conf
    $ mvn install
    $ cd ../javadoc2xls
    $ mvn install
    ```

1. Check an example  
    See the ``javadoc2xls-test`` project.
    https://github.com/ikeyat/xls2conf/tree/master/javadoc2xls-test
    
    See JUnit code (``HogeTest.java``, ``FugaTest.java``) in ``src/java/test``. You will find javadoc on these classes.
    
    Next, see the template Excel worksheet in ``report/template``. You will find worksheet with some annotated cells.
    
    Next, see ``pom.xml``. There is a defifition to switch default javadoc generator to this tool as follows.
    You can modifiy the configuration if you want to tune.
    For example, output directory, file name convention, etc...
    
    ```xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <configuration>
                    <doclet>com.example.tools.xls2conf.javadoc2xls.Javadoc2XlsDoclet</doclet>
                    <docletArtifact>
                        <groupId>com.example.tools.xls2conf</groupId>
                        <artifactId>javadoc2xls</artifactId>
                        <version>1.0-SNAPSHOT</version>
                    </docletArtifact>
                    <encoding>UTF-8</encoding>
                    <useStandardDocletOptions>false</useStandardDocletOptions>
                    <additionalparam>
                        -template ${project.basedir}/report/template/sheet_01.xls
                        -reportdir ${project.basedir}/report/testlist
                        -reportfilename {0}_TestCaseaa({1}).xls
                        -sorttag @no
                        -sheetindex 0
                    </additionalparam>
                </configuration>
            </plugin>
    ```
  
1. Try the example!  
    Go back to the console, please execute Maven as follows to generate javadoc of test classes.
    ```console
    $ cd ../javadoc2xls-test
    $ mvn javadoc:test-javadoc
    ```


    Afeter execution, go to the ``report/testlist`` folder. You can find generated Excel worksheets with javadoc contents if Maven complete successfully.
    

# Spec
## Execution parameters
|Parameter       | Description                                  |
|----------------|----------------------------------------------|
|-template       | Path to a template Excel worksheet file.     |
|-reportdir      | Output directory path.                       |
|-reportfilename | Name convention of output worksheet files. ``{0}`` variable means test class id (auto generated), ``{1}`` variables means class name. |
|-sorttag        | Any javadoc tag on test methods to sort test methods. |
|-sheetindex     | Target sheet to be generated. This tool can handle only 1 sheet for each worksheet. |

## Worksheet variables
