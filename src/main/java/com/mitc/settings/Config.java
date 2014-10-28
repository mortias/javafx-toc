package com.mitc.settings;

import com.mitc.Toc;

import java.io.File;

public class Config {

    private int width;
    private int height;
    private String theme;
    private String locale;
    private String pathSep;
    private String workDir;

    public Config() {
        this.pathSep = System.getProperty("file.separator");
        this.workDir = new File(Toc.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
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

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

}