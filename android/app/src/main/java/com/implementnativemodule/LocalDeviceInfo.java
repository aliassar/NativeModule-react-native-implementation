package com.implementnativemodule;

public class LocalDeviceInfo {
    private String mac;
    private String ip;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDeviceInfo(String mac, String ip) {
        this.mac = mac;
        this.ip = ip;
    }
}
