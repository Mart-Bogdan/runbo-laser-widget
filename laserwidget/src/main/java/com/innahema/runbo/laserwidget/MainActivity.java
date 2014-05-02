package com.innahema.runbo.laserwidget;

import android.app.Activity;
import android.os.IK508ControlService;
import android.os.ServiceManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        try {
//             Class sm = Class.forName("android.os.ServiceManager");
//            Class IK508 = Class.forName("android.os.IK508ControlService");
//            Class IK508_Stub = Class.forName("android.os.IK508ControlService$Stub");
//            Method setVal = IK508.getMethod("setVal", int.class, long.class);
//            Method getService = sm.getMethod("getService",String.class);
//            Object k508_binder= getService.invoke(null,"K508Control");
//            IK508ControlService k508 = (IK508ControlService) IK508_Stub.getMethod("asInterface",android.os.IBinder.class).invoke(null,k508_binder);


            IK508ControlService svc = IK508ControlService.Stub.asInterface(ServiceManager.getService("K508Control"));
            svc = IK508ControlService.Stub.asInterface(ServiceManager.getService("K508Control"));

            getMenuInflater().inflate(R.menu.main, menu);


        } catch (Exception e) {
            e.printStackTrace();
        }
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

}
