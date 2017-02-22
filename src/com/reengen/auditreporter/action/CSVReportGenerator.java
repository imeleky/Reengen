package com.reengen.auditreporter.action;

import com.reengen.auditreporter.action.base.AbstractReportGenerator;
import com.reengen.auditreporter.conf.Constants;
import com.reengen.auditreporter.domain.FileData;
import com.reengen.auditreporter.model.FileDataModel;
import com.reengen.auditreporter.model.UserDataModel;
import com.reengen.auditreporter.model.UserFileMapDataModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by melek on 13.02.2017.
 */
public class CSVReportGenerator extends AbstractReportGenerator {

    private DateFormat df;

    public CSVReportGenerator() {

        df = new SimpleDateFormat("yyyyMMdd_hhmmss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+3"));

    }

    @Override
    protected void createTopSizedReport(int topValue) {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(Constants.REPORT_FILE + "_top"+topValue+"_"+df.format(new Date())+".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDataModel.getInstance().sortModel();
        StringBuilder strBld = new StringBuilder();


        /** controls if -top n N value exceeds my maximum file size*/
        if(topValue > FileDataModel.getInstance().getModel().size()) {
            topValue = FileDataModel.getInstance().getModel().size();
        }

        for (int i=0; i<topValue; i++) {
            FileData fd = FileDataModel.getInstance().getModel().get(i);
            strBld.append(fd.getName());
            strBld.append(Constants.DELIMITER);
            strBld.append(UserDataModel.getInstance().getModel().get(fd.getOwner()).getName());
            strBld.append(Constants.DELIMITER);
            strBld.append(fd.getSize());
            try {
                writer.write(strBld.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            strBld.setLength(0);
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("CSV Top "+topValue+" Report Generated to --> "+Constants.REPORT_FILE + "_top"+topValue+"_"+df.format(new Date())+".csv");
    }

    @Override
    protected void createFullReport() {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(Constants.REPORT_FILE + df.format(new Date())+".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder strBld = new StringBuilder();

        for (String userID : UserFileMapDataModel.getInstance().getModel().keySet())
        {
            for (FileData fd: UserFileMapDataModel.getInstance().getModel().get(userID))
            {
                strBld.append(UserDataModel.getInstance().getModel().get(userID).getName());
                strBld.append(Constants.DELIMITER);
                strBld.append(fd.getName());
                strBld.append(Constants.DELIMITER);
                strBld.append(fd.getSize());
                try {
                    writer.write(strBld.toString());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                strBld.setLength(0);
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("CSV Report Generated to --> "+Constants.REPORT_FILE + df.format(new Date())+".csv...");
    }
}
