package com.reengen.auditreporter.utils;

/**
 * Created by melek on 14.02.2017.
 */
public final class ConsoleUtility {


    private ConsoleUtility() {
        throw new AssertionError();
    }

    public static void printHeader() {
        System.out.println("Audit Report");
        System.out.println("============");
    }

    public static void printTopHeader(int topValue) {
        System.out.println("Top #"+topValue+" Report");
        System.out.println("============");
    }

    public static void printUserHeader(String userName) {
        System.out.println("## UserData: " + userName);
    }


    public static void printUserMapFile(String fileName, long fileSize) {
        System.out.println("* " + fileName + " ==> " + fileSize + " bytes");
    }


    public static void printTopNFile(String fileName, String user, long fileSize) {
        System.out.println("* " + fileName + " ==> user " + user+","+fileSize + " bytes");
    }

}
