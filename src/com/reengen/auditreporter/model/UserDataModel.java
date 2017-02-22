package com.reengen.auditreporter.model;

import com.reengen.auditreporter.domain.UserData;

import java.util.HashMap;

/**
 * Created by melek on 14.02.2017.
 */
public class UserDataModel implements IModel {

    private HashMap<String, UserData> model;

    private static UserDataModel instance;

    public HashMap<String, UserData> getModel() {
        return model;
    }

    public UserDataModel() {
        model = new HashMap<String, UserData>();

    }

    public static UserDataModel getInstance()
    {
        if (null == instance)
            instance = new UserDataModel();
        return instance;
    }

    @Override
    public void addToModel(Object o) {
        //model.add((UserData)o);
        model.put(((UserData)o).getID(), (UserData)o);
    }

    @Override
    public void sortModel() {
    }
}
