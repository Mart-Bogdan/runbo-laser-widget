package com.innahema.runbo.laserwidget.impl;

import com.innahema.runbo.laserwidget.ILightControl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by winnie on 16.05.2014.
 */
public class RunboX6LightControl implements ILightControl {
    Class<?> lightClass;
    Object lingthInstance;
    Method closeLight;
    private final Method openLight;


    public RunboX6LightControl() {
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
        try {
            openLight.invoke(lingthInstance,0);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disableLaser() {
        try {
            closeLight.invoke(lingthInstance,0);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
