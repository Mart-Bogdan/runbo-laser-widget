package com.innahema.runbo.laserwidget;

import android.content.Context;

/**
 * Created by winnie on 16.05.2014.
 */
public class ContextHolder {
    public static final ThreadLocal<Context> context = new ThreadLocal<Context>();
}
