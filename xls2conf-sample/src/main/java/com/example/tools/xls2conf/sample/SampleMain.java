package com.example.tools.xls2conf.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ikeya on 14/12/15.
 */
public class SampleMain {
    private static final String USAGE = "usage: <command> input_file_path output_file_path";

    private static Logger logger = LoggerFactory.getLogger(SampleMain.class);

    public static int main(String[] args) {
        logger.debug("args: {}, {}", args);

        if (args.length < 2) {
            System.err.println(USAGE);
            System.exit(-1);
        }


        return 0;
    }

}
