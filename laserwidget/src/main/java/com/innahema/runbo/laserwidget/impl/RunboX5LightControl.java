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

    static String TAG = RunboX5LightControl.class.getName();
    private IK508ControlService k508Service;
    private IBinder k508ServiceBinder;

    public RunboX5LightControl() {
        init();
    }

    private void init() {
        if(k508ServiceBinder==null||!k508ServiceBinder.isBinderAlive() || k508Service==null) {
            k508ServiceBinder = ServiceManager.getService("K508Control");
            k508Service = IK508ControlService.Stub.asInterface(k508ServiceBinder);
        }
    }

    @Override
    public void enableLaser() {
        setVal(1);
    }

    @Override
    public void disableLaser() {
        setVal(0);
    }

    private void setVal(long val){
        init();
        try {
            k508Service.setVal(2,val);
        } catch (RemoteException e) {
            Log.e(TAG, "setVal", e);
        }
    }
}
