package com.reengen.auditreporter.model;

import com.reengen.auditreporter.domain.FileData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by melek on 14.02.2017.
 */
public class UserFileMapDataModel implements IModel{

    private static UserFileMapDataModel instance;
    private HashMap<String, ArrayList<FileData>> model;

    public UserFileMapDataModel() {
        model = new HashMap<String, ArrayList<FileData>>();

    }

    public static UserFileMapDataModel getInstance()
    {
        if (null == instance)
            instance = new UserFileMapDataModel();
        return instance;
    }

    public HashMap<String, ArrayList<FileData>> getModel() {
        return model;
    }

    @Override
    public void addToModel(Object o) {

        FileData fd = (FileData)o;
        if( null == model.get(fd.getOwner()))
        {
            model.put (fd.getOwner(),new ArrayList<FileData>());
        }

        ArrayList<FileData> aList = model.get(fd.getOwner());
        aList.add(fd);
        model.put(fd.getOwner(),aList);
    }

    @Override
    public void sortModel() {
    }



}
