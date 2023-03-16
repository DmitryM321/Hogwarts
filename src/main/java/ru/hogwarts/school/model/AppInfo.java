package ru.hogwarts.school.model;

public class AppInfo {

    private String appName;
    private String appVersion;
    private String appEnvironment;

    public AppInfo(String appName, String appVersion, String appEnvironment) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appEnvironment = appEnvironment;
    }

    public AppInfo() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppEnvironment() {
        return appEnvironment;
    }

    public void setAppEnvironment(String appEnvironment) {
        this.appEnvironment = appEnvironment;
    }
}
