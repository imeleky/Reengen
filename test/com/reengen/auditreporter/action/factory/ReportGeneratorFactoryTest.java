package com.reengen.auditreporter.action.factory;

import com.reengen.auditreporter.action.CSVReportGenerator;
import com.reengen.auditreporter.action.ConsoleReportGenerator;
import com.reengen.auditreporter.conf.Constants;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by melek on 15.02.2017.
 */
public class ReportGeneratorFactoryTest {

    @Test
    public void test_getInstance() throws Exception {
        assertSame(ReportGeneratorFactory.getInstance(),ReportGeneratorFactory.getInstance());
    }

    @org.junit.Test
    public void test_getReporter() throws Exception {

        String reporterType = Constants.CSV_REPORT_TYPE;
        assertTrue(ReportGeneratorFactory.getInstance().getReportGenerator(reporterType) instanceof CSVReportGenerator);

        reporterType = Constants.CONSOLE_REPORT_TYPE;
        assertTrue(ReportGeneratorFactory.getInstance().getReportGenerator(reporterType) instanceof ConsoleReportGenerator);

    }
}