package com.reengen.auditreporter.model;

import com.reengen.auditreporter.domain.FileData;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by melek on 14.02.2017.
 */
public class FileDataModel implements IModel{

    private ArrayList<FileData> model;

    private static FileDataModel instance;

    public FileDataModel() {

        model = new ArrayList<FileData>();
    }

    public ArrayList<FileData> getModel() {

        return model;
    }

    public static FileDataModel getInstance()
    {
        if (null == instance)

            instance = new FileDataModel();
        return instance;
    }

    @Override
    public void addToModel(Object o) {
        model.add((FileData)o);
    }

    @Override
    public void sortModel() {

        Collections.sort(model);
    }

}
