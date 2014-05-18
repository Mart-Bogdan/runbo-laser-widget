package com.innahema.runbo.laserwidget;

import android.util.Log;
import com.innahema.runbo.laserwidget.impl.DummyLightControl;import com.innahema.runbo.laserwidget.impl.RunboX5LightControl;
import com.innahema.runbo.laserwidget.impl.RunboX6LightControl;

/**
 * Created by winnie on 16.05.2014.
 */
public class LightControlManager {

    static String TAG = LightControlManager.class.getName();

    public static ILightControl createLightManager() {
        Log.i(TAG, "createLightManager()");

        try
        {
            Log.i(TAG, "new RunboX6LightControl()");
            return new RunboX6LightControl();
        } catch (Throwable th) {
            Log.e(TAG, "new RunboX6LightControl", th);
        }
        try
        {
            Log.i(TAG, "new RunboX5LightControl()");
            return new RunboX5LightControl();
        } catch (Throwable th) {
            Log.e(TAG, "new RunboX5LightControl", th);
        }

        Log.i(TAG, "new DummyLightControl()");
        return new DummyLightControl();
    }
}
