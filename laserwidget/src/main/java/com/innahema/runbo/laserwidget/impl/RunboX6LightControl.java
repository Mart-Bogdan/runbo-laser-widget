package com.innahema.runbo.laserwidget.impl;

import android.util.Log;
import com.innahema.runbo.laserwidget.ILightControl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by winnie on 16.05.2014.
 */
public class RunboX6LightControl implements ILightControl {
    static final String TAG = RunboX5LightControl.class.getName();

    Class<?> lightClass;
    Object lingthInstance;
    Method closeLight;
    private final Method openLight;


    public RunboX6LightControl() {
        Log.i(TAG, ".ctor()");
        try {
            lightClass = Class.forName("android.hardware.Light");
            closeLight = lightClass.getMethod("closeLight", int.class);
            openLight = lightClass.getMethod("openLight",int.class);

            lingthInstance = lightClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enableLaser() {
        Log.i(TAG, "enableLaser()");
        try {
            openLight.invoke(lingthInstance,0);
        } catch (IllegalAccessException e) {
            Log.e(TAG,"enableLaser()",e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e(TAG,"enableLaser()",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disableLaser() {
        Log.i(TAG,"disableLaser()");
        try {
            closeLight.invoke(lingthInstance,0);
        } catch (IllegalAccessException e) {
            Log.e(TAG,"disableLaser()",e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e(TAG,"disableLaser()",e);
            throw new RuntimeException(e);
        }
    }
}
