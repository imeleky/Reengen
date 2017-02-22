package com.reengen.auditreporter.action;

import com.reengen.auditreporter.domain.FileData;
import com.reengen.auditreporter.action.base.AbstractReportGenerator;
import com.reengen.auditreporter.model.FileDataModel;
import com.reengen.auditreporter.model.UserDataModel;
import com.reengen.auditreporter.model.UserFileMapDataModel;
import com.reengen.auditreporter.utils.ConsoleUtility;

/**
 * Created by melek on 13.02.2017.
 */
public class ConsoleReportGenerator extends AbstractReportGenerator {

    @Override
    protected void createTopSizedReport(int topValue) {
        ConsoleUtility.printTopHeader(topValue);
        FileDataModel.getInstance().sortModel();

        /** controls if -top n N value exceeds my maximum file size*/
        if(topValue > FileDataModel.getInstance().getModel().size()) {
            topValue = FileDataModel.getInstance().getModel().size();
        }

        for (int i=0; i<topValue; i++)
        {
            FileData fd= FileDataModel.getInstance().getModel().get(i);
            ConsoleUtility.printTopNFile(fd.getName(), UserDataModel.getInstance().getModel().get(fd.getOwner()).getName(),fd.getSize());
        }
    }

    @Override
    protected void createFullReport() {
        ConsoleUtility.printHeader();

        for (String userID : UserFileMapDataModel.getInstance().getModel().keySet())
        {
            ConsoleUtility.printUserHeader(UserDataModel.getInstance().getModel().get(userID).getName());

            for (FileData fd: UserFileMapDataModel.getInstance().getModel().get(userID))
            {
                ConsoleUtility.printUserMapFile(fd.getName(), fd.getSize());
            }
        }
    }
}
