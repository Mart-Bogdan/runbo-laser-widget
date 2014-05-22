package com.innahema.runbo.laserwidget.impl;

import android.os.*;
import android.util.Log;
import com.innahema.runbo.laserwidget.ContextHolder;
import com.innahema.runbo.laserwidget.ILightControl;
import com.innahema.runbo.laserwidget.R;

import java.io.File;
import java.io.IOException;
import java.lang.Process;
import java.util.Scanner;

import static com.innahema.runbo.laserwidget.ContextHolder.showToast;

/**
 * Created by winnie on 16.05.2014.
 */
public class RunboX6LightControl implements ILightControl {
    static final String TAG = RunboX6LightControl.class.getName();
    //static final String DEVICE_NAME = "/sys/class/leds/red/brightness";
    static final String DEVICE_NAME = "/sys/class/leds/laserlight/brightness";
    //             /sys/class/leds/flashlight/brightness

    Class<?> lightClass;


    public RunboX6LightControl() {
        Log.i(TAG, ".ctor()");
        try {
            //lightClass =Class.forName("android.hardware.Light");
            if(!new File(DEVICE_NAME).exists())
                throw new RuntimeException("Not Runbo X6/Q5 phone");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enableLaser() {
        Log.i(TAG, "enableLaser()");
        execSU("echo 1 > " + DEVICE_NAME);

    }

    @Override
    public void disableLaser() {
        Log.i(TAG,"disableLaser()");
        execSU("echo 0 > " + DEVICE_NAME);
    }


    private static void execSU(String command) {
        try {
            Process process=Runtime.getRuntime().exec(new String[]{"su", "-c", command+"; echo $?"});
            Scanner out = new Scanner(process.getInputStream());

            if(process.waitFor()!=0)
            {
                showToast(TAG, ContextHolder.get().getString(R.string.cant_get_root));
                return;
            }
            while (out.hasNext())
                if(!"0".equals(out.nextLine()))
                {
                    showToast(TAG, ContextHolder.get().getString(R.string.cant_execute_command) + command);

                }

        } catch (IOException e) {
            Log.e(TAG, "enableLaser()", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "enableLaser()", e);
        }
    }

}
