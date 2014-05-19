package com.innahema.runbo.laserwidget;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by winnie on 16.05.2014.
 */
public class ContextHolder {
    private static final ThreadLocal<Context> context = new ThreadLocal<Context>();

    public static Context get() {
        return context.get();
    }

    public static void set(Context context) {
        ContextHolder.context.set(context);
    }


    public static void showToast(String tag, String text) {
        Context context = ContextHolder.get();

        Toast toast = Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();

        Log.e(tag, text);
    }

}
