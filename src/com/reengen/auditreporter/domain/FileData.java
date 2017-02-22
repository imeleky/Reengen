package com.reengen.auditreporter.domain;

/**
 * Created by melek on 13.02.2017.
 */
public class FileData implements Comparable<FileData> {
    String ID;
    Long size;
    String name;
    String owner;

    public FileData(String ID, Long size, String name, String owner)  {
        this.ID = ID;
        this.size = size;
        this.name = name;
        this.owner = owner;
    }

    public String getID() {
        return ID;
    }

    public Long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }


    @Override
    public int compareTo(FileData o) {
        long fileSize1 = this.getSize();
        long fileSize2 = o.getSize();

        if (fileSize1 < fileSize2) {
            return 1;
        } else if (fileSize1 > fileSize2) {
            return -1;
        } else {
            return 0;
        }
    }
}
