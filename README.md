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
    $ cd tool
    $ mvn install
    ```

1. Check an example  
    See the ``sample`` project.
    https://github.com/ikeyat/javadoc2xls/tree/master/sample
    
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
                    <doclet>jp.ikeyat.tools.javadoc2xls.Javadoc2XlsDoclet</doclet>
                    <docletArtifact>
                        <groupId>jp.ikeyat.tools.javadoc2xls</groupId>
                        <artifactId>javadoc2xls-tool</artifactId>
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
    Don't forget to switch Maven profile (``-P``).
    ```console
    $ cd ../sample
    $ mvn javadoc:test-javadoc -P javadoc2xls
    ```


    Afeter execution, go to the ``report/testlist`` folder. You can find generated Excel worksheets with javadoc contents if Maven complete successfully.
    

# Spec
## Input and Output
* Input
    - Template worksheet with variables (1 file)
    - JUnit classes annotated with ``@Test`` (N files)
* Output
    - Generated test case documents (N files)

## Execution parameters
|Parameter       | Description                                  |
|----------------|----------------------------------------------|
|-template       | Path to a template Excel worksheet file.     |
|-reportdir      | Output directory path.                       |
|-reportfilename | Name convention of output worksheet files. ``{0}`` variable means test class id (auto generated), ``{1}`` variables means class name. |
|-sorttag        | Any javadoc tag on test methods to sort test methods. |
|-sheetindex     | Target sheet to be generated. This tool can handle only 1 sheet for each worksheet. |

## Worksheet variables
Variables are annotation to be replaced with actual values such as javadoc contents, class name, method name, timestamp, static text, etc...
To write variables on worksheet template, please follow a simple grammar as follows.

### Variable binding mode
This tool supports 2 variable binding modes. You can choose any mode for your purpose.

* Cell binding mode
    binds value at once. The replacement is never repeated for each test method.
    Therefore this is used to describe overview of a test class (ex. target class name).
* Row binding mode 
    binds value repeatedly for each row of the worksheet.
    Number of repeats is the number of test method in a test class.
    This is used to describe each test method (ex. test method name).
    
### Syntax

```
#{binding mode}{variable}
```

For example, 
- ``#cell#className`` means "binds ``#className`` variable with cell binding mode"
- ``#row#methodName`` means "binds ``#methodName`` variable with row binding mode"

### Variables
|Variable        | ``#cell`` | ``#row`` | Description                            | Example           | Example Output        |
|----------------|-----------|----------|----------------------------------------|-------------------|-----------------------|
|``#className``  |OK         |NG        |Write a full name of the test class.    |``#cell#className``|``com.example.fuga.FugaTest``|
|``#methodName`` |NG         |OK        |Write a test method name.               |``#row#methodName``|``testFuga_001``       |
|``#commentText``|OK         |OK        |Write a javadoc comment of each test method.|``#row#commentText``|``This is a comment``|
|``#staticText#{any text}`` |NG   |OK   |Write a given static text.              |``#row#staticText#Passed!``|``Passed!``    |
|``#date#{format}``|OK       |OK        |Write a runtime system timetamp with given format. |``#row#date#yyyy/MM/dd``|``2018/08/22``|
|``@{tag name}``|OK         |OK         |Write a value in the javadoc tag of each test method. |``#row@expected``|``return "1234"``|


# Hints
If you have the ``maven-javadoc-config`` to your app, you cannot get standard javadoc of your app.
Maven profile will help this problem. I recommend that you have the config at the minor Maven profile, not the default profile.

Other cases, if you would like to get standard javadoc of test code additionally, custom tag option will be helpful. You can find a simple [example](https://github.com/ikeyat/javadoc2xls/blob/master/sample/pom.xml).
For more detail, see https://maven.apache.org/plugins-archives/maven-javadoc-plugin-2.8.1/examples/tag-configuration.html.

