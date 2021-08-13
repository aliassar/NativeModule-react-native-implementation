package com.implementnativemodule;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";


    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastExample";
    }


    @ReactMethod
    public void startPingService(int timeout, Callback successCallback) {
        WritableArray devicesInfo = Arguments.createArray();

        try {

            ReactContext context = this.getReactApplicationContext();
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
            String subnet = getSubnetAddress(mWifiManager.getDhcpInfo().gateway);


            for (int i = 1; i < 255; i++) {

                String host = subnet + "." + i;

                if (InetAddress.getByName(host).isReachable(timeout)) {

                    String strMacAddress = getMacAddressFromIP(host);

                    Log.w("DeviceDiscovery", "Reachable Host: " + String.valueOf(host) + " and Mac : " + strMacAddress + " is reachable!");

                    WritableMap device = Arguments.createMap();
                    device.putString("mac", strMacAddress);
                    device.putString("host", host);
                    devicesInfo.pushMap(device);
//
                } else {
                    Log.e("DeviceDiscovery", "❌ Not Reachable Host: " + String.valueOf(host));

                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        successCallback.invoke(devicesInfo);


    }
    public String getMacAddressFromIP(@NonNull String ipFinding)
    {

        Log.i("IPScanning","Scan was started!");
        List<LocalDeviceInfo> antarDevicesInfos = new ArrayList();


        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4) {
                    String ip = splitted[0];
                    String mac = splitted[3];
                    if (mac.matches("..:..:..:..:..:..")) {

                        if (ip.equalsIgnoreCase(ipFinding))
                        {
                            return mac;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "00:00:00:00";
    }

    private String getSubnetAddress(int address)
    {
        String ipString = String.format(
                "%d.%d.%d",
                (address & 0xff),
                (address >> 8 & 0xff),
                (address >> 16 & 0xff));

        return ipString;
    }
}