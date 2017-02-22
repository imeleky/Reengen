package com.reengen.auditreporter.conf;

/**
 * Created by melek on 14.02.2017.
 */
public class Constants {

    public static final int USER_CSV_ARG_INDEX = 0;
    public static final int FILE_CSV_ARG_INDEX = 1;
    public static final int CSV_OUT_ARG_INDEX = 2;
    public static final int TOP_ARG_INDEX = 3;
    public static final int TOP_N_ARG_INDEX = 4;

    public static final int TOP_REPORT_VALUE = -1;
    public static final int TOPN_STRING_SIZE = 5;
    public static final int STRING_IS_NOT_IN = -1;

    public static final String CSV_REPORT_ARG = "-c";
    public static final String TOP_REPORT_ARG = "-top";

    public static final String CSV_REPORT_TYPE = "CSV_TYPE";
    public static final String CONSOLE_REPORT_TYPE = "CONSOLE_TYPE";

    public static final String REPORT_FILE = "out/reports/audit_report_";
    public static final String REPORT_PATH = "out/reports";
    public static final String DELIMITER= ",";
}
