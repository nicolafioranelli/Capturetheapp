package com.example.capturetheapp.model;

import android.content.pm.ApplicationInfo;

import java.util.List;

public class PhoneInfo {
    String imei;
    String simOperatorName;
    String simCell;
    String lineNumber;
    List<String> applicationInfos;

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getSimCell() {
        return simCell;
    }

    public void setSimCell(String simCell) {
        this.simCell = simCell;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public List<String> getApplicationInfos() {
        return applicationInfos;
    }

    public void setApplicationInfos(List<String> applicationInfos) {
        this.applicationInfos = applicationInfos;
    }
}
