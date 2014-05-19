package com.innahema.runbo.laserwidget.impl;

import android.util.Log;
import com.innahema.runbo.laserwidget.ContextHolder;
import com.innahema.runbo.laserwidget.ILightControl;
import com.innahema.runbo.laserwidget.R;

import static com.innahema.runbo.laserwidget.ContextHolder.showToast;

/**
 * Created by winnie on 16.05.2014.
 */
public class DummyLightControl implements ILightControl {
    static final String TAG = DummyLightControl.class.getName();

    @Override
    public void enableLaser() {
        Log.i(TAG,"enableLaser()");

        showToast(TAG, ContextHolder.get().getString(R.string.phone_not_supported));
    }

    @Override
    public void disableLaser() {
        Log.i(TAG,"disableLaser()");
        enableLaser();
    }
}
