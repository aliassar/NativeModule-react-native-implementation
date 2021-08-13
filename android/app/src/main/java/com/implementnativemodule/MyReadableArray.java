package com.implementnativemodule;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MyReadableArray implements ReadableArray {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isNull(int index) {
        return false;
    }

    @Override
    public boolean getBoolean(int index) {
        return false;
    }

    @Override
    public double getDouble(int index) {
        return 0;
    }

    @Override
    public int getInt(int index) {
        return 0;
    }

    @Nullable
    @Override
    public String getString(int index) {
        return null;
    }

    @Nullable
    @Override
    public ReadableArray getArray(int index) {
        return null;
    }

    @Nullable
    @Override
    public ReadableMap getMap(int index) {
        return null;
    }

    @Nonnull
    @Override
    public Dynamic getDynamic(int index) {
        return null;
    }

    @Nonnull
    @Override
    public ReadableType getType(int index) {
        return null;
    }

    @Nonnull
    @Override
    public ArrayList<Object> toArrayList() {
        return null;
    }
}
