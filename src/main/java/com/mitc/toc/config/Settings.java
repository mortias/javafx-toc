package com.mitc.toc.config;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;

public class Settings {

    private int width;
    private int height;
    private int timeout;

    private int restPort;
    private int vertxPort;
    private int hawtioPort;

    private String key;
    private String root;
    private String theme;
    private String locale;
    private String pathSep;
    private String host;

    private boolean encrypted;
    private boolean undecorated;

    public Settings() {

        timeout = 2;
        theme = "cupertino";
        undecorated = true;
        locale = "en_US";

        restPort = 9999;
        vertxPort = 8888;
        hawtioPort = 7777;

        try {
            pathSep = System.getProperty("file.separator");
            root = FilenameUtils.getFullPath(new File("test.txt").getCanonicalPath());
            host = Inet4Address.getLocalHost().getHostAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getPathSep() {
        return pathSep;
    }

    public void setPathSep(String pathSep) {
        this.pathSep = pathSep;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean getUndecorated() {
        return undecorated;
    }

    public void setUndecorated(boolean undecorated) {
        this.undecorated = undecorated;
    }

    public int getVertxPort() {
        return vertxPort;
    }

    public void setVertxPort(int vertxPort) {
        this.vertxPort = vertxPort;
    }

    public int getRestPort() {
        return restPort;
    }

    public void setRestPort(int restPort) {
        this.restPort = restPort;
    }

     public int getHawtioPort() {
        return hawtioPort;
    }

    public void setHawtioPort(int hawtioPort) {
        this.hawtioPort = hawtioPort;
    }
}
