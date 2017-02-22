package com.reengen.auditreporter.db;

import com.reengen.auditreporter.domain.FileData;
import com.reengen.auditreporter.domain.UserData;
import com.reengen.auditreporter.model.FileDataModel;
import com.reengen.auditreporter.model.UserDataModel;
import com.reengen.auditreporter.model.UserFileMapDataModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by melek on 14.02.2017.
 */
public class CSVLoader {

    public CSVLoader() {
    }
    public void loadUserData(String userFn) throws IOException {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(userFn));

            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] sArr = line.split(",");
                UserDataModel.getInstance().addToModel(new UserData(sArr[0],sArr[1]));
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public  void loadUserFileMapData (){


        for (FileData fd:FileDataModel.getInstance().getModel())
        {
            UserFileMapDataModel.getInstance().addToModel(fd);
        }
    }

    public void loadFileData(String filesFn) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filesFn));
            String [] nextLine;
            String line;

            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {;
                nextLine = line.split(",");
                FileDataModel.getInstance().addToModel(new FileData(nextLine[0],Long.parseLong(nextLine[1]),nextLine[2],nextLine[3] ));


            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}
