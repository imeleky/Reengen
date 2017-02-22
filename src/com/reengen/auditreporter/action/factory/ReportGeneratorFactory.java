package com.reengen.auditreporter.action.factory;

import com.reengen.auditreporter.action.CSVReportGenerator;
import com.reengen.auditreporter.action.ConsoleReportGenerator;
import com.reengen.auditreporter.action.base.AbstractReportGenerator;
import com.reengen.auditreporter.conf.Constants;

/**
 * Created by melek on 14.02.2017.
 */

/**
 * FACTORY design pattern usage
 */
public class ReportGeneratorFactory {

    private static ReportGeneratorFactory instance;

    /** SINGLETON design pattern usage */
    public static ReportGeneratorFactory getInstance()
    {
        if (null == instance)
            instance = new ReportGeneratorFactory();
        return instance;
    }


    /**Method for selecting related ReportGenerator using reporterType parameter */
    public AbstractReportGenerator getReportGenerator(String reporterType){
        if(reporterType == null){
            return null;
        }
        if(reporterType.equalsIgnoreCase(Constants.CSV_REPORT_TYPE)){
                return new CSVReportGenerator();


        } else if(reporterType.equalsIgnoreCase(Constants.CONSOLE_REPORT_TYPE)){
            return new ConsoleReportGenerator();

        }
        return null;
    }
}
