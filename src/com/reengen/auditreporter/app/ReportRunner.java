package com.reengen.auditreporter.app;

import com.reengen.auditreporter.action.factory.ReportGeneratorFactory;
import com.reengen.auditreporter.conf.Constants;
import com.reengen.auditreporter.db.CSVLoader;

import java.io.IOException;

/**GitHub Project*/
public class ReportRunner {


    private static String userCsvArg;
    private static String fileCsvArg;
    private static String csvReportArg;
    private static int topNArg;

    public ReportRunner() {
    }

    public static void main(String[] args) throws IOException {

        ReportRunner myReportRunner = new ReportRunner();
        if(myReportRunner.parseArguments(args))
        {
            CSVLoader csvLoader = new CSVLoader();
            csvLoader.loadUserData(userCsvArg);
            csvLoader.loadFileData(fileCsvArg);
            csvLoader.loadUserFileMapData();

            ReportGeneratorFactory.getInstance().getReportGenerator(csvReportArg).generateReport(topNArg);
        }
        System.out.println("GitHub dev branch Project");
    }

    public  boolean parseArguments(String[] args) {

        boolean retFlag= true;
        topNArg=-1;

        csvReportArg = Constants.CONSOLE_REPORT_TYPE;

        if( args.length >= Constants.FILE_CSV_ARG_INDEX +1) {
            try {
                userCsvArg = args[Constants.USER_CSV_ARG_INDEX];

                try {
                    fileCsvArg = args[Constants.FILE_CSV_ARG_INDEX];

                    /**String builder is used to store -c -top n arguments in an arbitrary sequence*/
                    StringBuilder stringBuilder = createStringBuilderFromArguments(args);


                    if (Constants.STRING_IS_NOT_IN != stringBuilder.indexOf(Constants.CSV_REPORT_ARG)) {
                        csvReportArg = Constants.CSV_REPORT_TYPE;
                    }


                    int topNindex = stringBuilder.indexOf(Constants.TOP_REPORT_ARG);

                    if (Constants.STRING_IS_NOT_IN != topNindex) {

                        if (stringBuilder.length() > topNindex + Constants.TOPN_STRING_SIZE) {
                            int i = stringBuilder.indexOf(Constants.DELIMITER, topNindex + Constants.TOPN_STRING_SIZE);
                            try {
                                topNArg = Integer.parseInt(stringBuilder.substring(topNindex + Constants.TOPN_STRING_SIZE, i));
                            } catch (IllegalArgumentException e) {
                                System.err.println("Not a Valid Top N argument exception....");
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Not a Valid File CSV argument exception....");
                }

            } catch (IllegalArgumentException e) {
                System.err.println("Not a Valid User CSV argument exception....");
            }
        }
        else
        {
            retFlag = false;
            System.err.println("Not a Valid argument exception....");
        }
        return retFlag;
    }

    private static StringBuilder createStringBuilderFromArguments(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        int index = Constants.CSV_OUT_ARG_INDEX;
        while( index < args.length)
        {
            stringBuilder.append(args[index]);
            stringBuilder.append(Constants.DELIMITER);
            index++;
        }

        return stringBuilder;
    }

    public static String getUserCsvArg() {
        return userCsvArg;
    }

    public static String getFileCsvArg() {
        return fileCsvArg;
    }

    public static String getCsvReportArg() {
        return csvReportArg;
    }

    public static int getTopNArg() {
        return topNArg;
    }
}
