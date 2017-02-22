package com.reengen.auditreporter.domain;

/**
 * Created by melek on 13.02.2017.
 */
public class UserData {

    private String name;
    private String ID;

    public UserData(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }
}
