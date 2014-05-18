package com.innahema.runbo.laserwidget.impl;

import android.os.IBinder;
import android.os.IK508ControlService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.innahema.runbo.laserwidget.ILightControl;
import com.innahema.runbo.laserwidget.LightControlManager;

/**
 * Created by winnie on 16.05.2014.
 */
public class RunboX5LightControl implements ILightControl {
    static final String TAG = RunboX5LightControl.class.getName();

    private IK508ControlService k508Service;
    private IBinder k508ServiceBinder;

    public RunboX5LightControl() {
        init();
    }

    private void init() {
        Log.i(TAG,"init()");
        if(k508ServiceBinder==null||!k508ServiceBinder.isBinderAlive() || k508Service==null) {
            Log.i(TAG,"init() executing");
            k508ServiceBinder = ServiceManager.getService("K508Control");
            k508Service = IK508ControlService.Stub.asInterface(k508ServiceBinder);
            if(k508Service == null) {
                Log.i(TAG,"init() Can't bind K508Control service");
                throw new RuntimeException("Can't bind K508Control service");
            }
        }
    }

    @Override
    public void enableLaser() {
        Log.i(TAG,"enableLaser()");
        setVal(1);
    }

    @Override
    public void disableLaser() {
        Log.i(TAG,"enableLaser()");
        setVal(0);
    }

    private void setVal(long val){
        Log.i(TAG,"setVal("+val+')');
        init();
        try {
            k508Service.setVal(2,val);
        } catch (RemoteException e) {
            Log.e(TAG, "setVal", e);
        }
    }
}
