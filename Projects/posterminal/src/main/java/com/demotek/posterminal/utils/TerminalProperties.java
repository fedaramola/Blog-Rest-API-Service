package com.demotek.posterminal.utils;

import java.util.ResourceBundle;

public class TerminalProperties {

    //private static Properties props;
    private static ResourceBundle resource;

    public static String getMessage(String key) {
        if (resource == null) {
            resource = ResourceBundle.getBundle("flexml-ws");

        }
        return resource.getString(key);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("This "+getMessage("ENG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}