package com.reengen.auditreporter.action;

import com.reengen.auditreporter.action.factory.ReportGeneratorFactory;
import com.reengen.auditreporter.conf.Constants;
import com.reengen.auditreporter.db.CSVLoader;
import com.reengen.auditreporter.domain.FileData;
import com.reengen.auditreporter.model.FileDataModel;
import com.reengen.auditreporter.model.UserDataModel;
import com.reengen.auditreporter.model.UserFileMapDataModel;
import com.reengen.auditreporter.utils.ConsoleUtility;
import org.junit.Test;
import testUtil.LoggedPrintStream;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by melek on 15.02.2017.
 */
public class ConsoleReportGeneratorTest {

    public void initialize()
    {
        CSVLoader csvLoader = new CSVLoader();
        try {
            csvLoader.loadUserData("resources/users.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            csvLoader.loadFileData("resources/files.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        csvLoader.loadUserFileMapData();
    }

    @Test
    public void test_createTopSizedReport() throws Exception {

        int topNValue = 3;
        initialize();

        LoggedPrintStream lpsOut = LoggedPrintStream.create(System.out);
        System.setOut(lpsOut);
        ReportGeneratorFactory.getInstance().getReportGenerator(Constants.CONSOLE_REPORT_TYPE).generateReport(topNValue);

        System.setOut(lpsOut.getUnderlying());
        String functionOutput = lpsOut.getBuf().toString();


        lpsOut = LoggedPrintStream.create(System.out);
        System.setOut(lpsOut);


        /** process and stdout topN size files*/
        ConsoleUtility.printTopHeader(topNValue);
        FileDataModel.getInstance().sortModel();

        for (int i=0; i<topNValue; i++)
        {
            FileData fd= FileDataModel.getInstance().getModel().get(i);
            ConsoleUtility.printTopNFile(fd.getName(), UserDataModel.getInstance().getModel().get(fd.getOwner()).getName(),fd.getSize());
        }

        System.setOut(lpsOut.getUnderlying());
        String testOutput = lpsOut.getBuf().toString();

        /**compare function result and test process/stdout result*/
        assertEquals(functionOutput,testOutput);
    }

    @Test
    public void test_createFullReport() throws Exception {

        initialize();

        /** lpsout is used for to get system out console data*/
        LoggedPrintStream lpsOut = LoggedPrintStream.create(System.out);
        System.setOut(lpsOut);
        ReportGeneratorFactory.getInstance().getReportGenerator(Constants.CONSOLE_REPORT_TYPE).generateReport(-1);
        System.setOut(lpsOut.getUnderlying());
        /** functional output to store createFullReport function console output*/
        String functionOutput = lpsOut.getBuf().toString();


        /** to clean console out buffer to get sys out data*/
        lpsOut = LoggedPrintStream.create(System.out);
        System.setOut(lpsOut);

        /** process and stdout full reported files*/
        ConsoleUtility.printHeader();

        for (String userID : UserFileMapDataModel.getInstance().getModel().keySet())
        {
            ConsoleUtility.printUserHeader(UserDataModel.getInstance().getModel().get(userID).getName());

            for (FileData fd: UserFileMapDataModel.getInstance().getModel().get(userID))
            {
                ConsoleUtility.printUserMapFile(fd.getName(), fd.getSize());
            }
        }

        System.setOut(lpsOut.getUnderlying());
        String testOutput = lpsOut.getBuf().toString();
    }
}