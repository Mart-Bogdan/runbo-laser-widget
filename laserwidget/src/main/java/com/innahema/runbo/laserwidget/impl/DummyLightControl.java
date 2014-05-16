package com.innahema.runbo.laserwidget.impl;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;
import com.innahema.runbo.laserwidget.ContextHolder;
import com.innahema.runbo.laserwidget.ILightControl;
import com.innahema.runbo.laserwidget.R;

/**
 * Created by winnie on 16.05.2014.
 */
public class DummyLightControl implements ILightControl {
    @Override
    public void enableLaser() {
        Context context = ContextHolder.context.get();

        Toast toast = Toast.makeText(
                context,
                context.getString(R.string.phone_not_supported),
                Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    @Override
    public void disableLaser() {
        enableLaser();
    }
}
