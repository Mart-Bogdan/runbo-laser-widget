package com.innahema.runbo.laserwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.widget.RemoteViews;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import android.app.Activity;

public class LaserWidget extends AppWidgetProvider {

	final static String ACTION_SWITCH = "com.innahema.runbo.laserwidget.SWITCH";
	final static String LOG_TAG = "Runbo Laser widget";
	static boolean isEnabled = false;
    private IK508ControlService k508Service;
    private IBinder k508ServiceBinder;


    @Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for(int id : appWidgetIds) {
			updateWidget(context, appWidgetManager, id);
		}
	}


	private void updateWidget(Context context,	AppWidgetManager appWidgetManager, int id) {



		RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget);
		if(isEnabled) {
			widgetView.setImageViewResource(R.id.ibSwitcher, R.drawable.laser_sign_red);
		} else {
			widgetView.setImageViewResource(R.id.ibSwitcher, R.drawable.laser_sign);
		}
		
		
		Intent intent = new Intent(context, LaserWidget.class);
		intent.setAction(ACTION_SWITCH);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, 0);
		widgetView.setOnClickPendingIntent(R.id.ibSwitcher, pIntent);
		appWidgetManager.updateAppWidget(id, widgetView);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		
		if (intent.getAction().equalsIgnoreCase(ACTION_SWITCH)) {
            //isEnabled = context.getSharedPreferences("iseEnambled",Activity.MODE_PRIVATE);
			isEnabled = !isEnabled;
			int id = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			updateWidget(context, AppWidgetManager.getInstance(context), id);
			switchFlash();
		}
	}

	private void switchFlash() {
            if(k508ServiceBinder==null||!k508ServiceBinder.isBinderAlive() || k508Service==null) {
                k508ServiceBinder = ServiceManager.getService("K508Control");
                k508Service = IK508ControlService.Stub.asInterface(k508ServiceBinder);
            }
        try {
            k508Service.setVal(2,isEnabled ? 0L : 0L);
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "switchFlash()", e);
        }
    }

}
