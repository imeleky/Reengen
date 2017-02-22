package com.reengen.auditreporter.app;

import com.reengen.auditreporter.conf.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by melek on 15.02.2017.
 */
public class ReportRunnerTest {
    @Test
    public void test_parseArguments_CSV_TOP_REGULAR() throws Exception {

        String args[] = {"users.csv", "files.csv","-c","-top","5"};

        ReportRunner myReportRunner = new ReportRunner();
        myReportRunner.parseArguments(args);

        assertEquals(args[Constants.USER_CSV_ARG_INDEX],myReportRunner.getUserCsvArg());
        assertEquals(args[Constants.FILE_CSV_ARG_INDEX],myReportRunner.getFileCsvArg());
        assertEquals(Constants.CSV_REPORT_TYPE, myReportRunner.getCsvReportArg());
        assertEquals(Integer.parseInt(args[Constants.TOP_N_ARG_INDEX]),myReportRunner.getTopNArg());

    }

    @Test
    public void test_parseArguments_CSV_TOP_IRREGULAR() throws Exception {
        String args[] = {"users.csv", "files.csv","-top","15","-c"};

        ReportRunner myReportRunner = new ReportRunner();
        myReportRunner.parseArguments(args);

        assertEquals(args[Constants.USER_CSV_ARG_INDEX],myReportRunner.getUserCsvArg());
        assertEquals(args[Constants.FILE_CSV_ARG_INDEX],myReportRunner.getFileCsvArg());
        assertEquals(Constants.CSV_REPORT_TYPE, myReportRunner.getCsvReportArg());
        assertEquals(Integer.parseInt(args[Constants.TOP_ARG_INDEX]), myReportRunner.getTopNArg());
    }

    @Test
    public void test_parseArguments_CONSOLE_TOP_REGULAR() throws Exception {
        String args[] = {"users.csv", "files.csv","-top","20"};

        ReportRunner myReportRunner = new ReportRunner();
        myReportRunner.parseArguments(args);

        assertEquals(args[Constants.USER_CSV_ARG_INDEX],myReportRunner.getUserCsvArg());
        assertEquals(args[Constants.FILE_CSV_ARG_INDEX],myReportRunner.getFileCsvArg());
        assertEquals(Constants.CONSOLE_REPORT_TYPE, myReportRunner.getCsvReportArg());
        assertEquals(Integer.parseInt(args[Constants.TOP_ARG_INDEX]),myReportRunner.getTopNArg());
    }

    @Test
    public void test_parseArguments_INVALID_TOP_N() throws Exception {
        String args[] = {"users.csv", "files.csv","-top","a"};

        ReportRunner myReportRunner = new ReportRunner();

        try {
            myReportRunner.parseArguments(args);
            assertEquals(args[Constants.USER_CSV_ARG_INDEX],myReportRunner.getUserCsvArg());
            assertEquals(args[Constants.FILE_CSV_ARG_INDEX],myReportRunner.getFileCsvArg());
            assertEquals(Constants.CONSOLE_REPORT_TYPE, myReportRunner.getCsvReportArg());
            assertEquals(Integer.parseInt(args[Constants.TOP_ARG_INDEX]),myReportRunner.getTopNArg());

        }   catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
             assertTrue(e instanceof IllegalArgumentException);
    }


    }





}