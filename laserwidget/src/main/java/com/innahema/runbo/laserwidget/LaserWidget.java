package com.innahema.runbo.laserwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
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
	final static String TAG = "Runbo Laser widget";
	static boolean isEnabled = false;

    static final ILightControl lightControl = LightControlManager.createLightManager();


    @Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
        Log.i(TAG, "onUpdate()");
        ContextHolder.context.set(context);

		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for(int id : appWidgetIds) {
			updateWidget(context, appWidgetManager, id);
		}
	}


	private void updateWidget(Context context,	AppWidgetManager appWidgetManager, int id) {
        ContextHolder.context.set(context);

		RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget);
        widgetView.setImageViewResource(R.id.ibSwitcher, isEnabled
                                                            ? R.drawable.laser_sign_red
                                                            : R.drawable.laser_sign);
		
		
		Intent intent = new Intent(context, LaserWidget.class);
		intent.setAction(ACTION_SWITCH);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, 0);
		widgetView.setOnClickPendingIntent(R.id.ibSwitcher, pIntent);
		appWidgetManager.updateAppWidget(id, widgetView);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive(intent="+intent+')');
		super.onReceive(context, intent);
        ContextHolder.context.set(context);
		
		if (intent.getAction().equalsIgnoreCase(ACTION_SWITCH)) {
			isEnabled = !isEnabled;
			//int id = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            final int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(context, getClass()));
            for (int id : appWidgetIds)
                updateWidget(context, manager, id);
			switchFlash();
		}
	}

	private void switchFlash() {
        Log.i(TAG, "switchFlash(isEnabled="+isEnabled+')');
        if(isEnabled)
            lightControl.enableLaser();
        else
            lightControl.disableLaser();
    }

}
