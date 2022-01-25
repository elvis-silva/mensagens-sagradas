package com.elvis.biblia.msg.context;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by elvis on 01/07/15.
 */
public class Contexts {
    private static Contexts instance;
    private Context context;
    private Activity activity, lastActivity;
    public int intent = 0;

    private Contexts() {}

    public static Contexts getInstance() {
        if(instance == null) {
            synchronized (Contexts.class) {
                if(instance == null) {
                    instance = new Contexts();
                }
            }
        }
        return instance;
    }

    public boolean initActivity(Activity pActivity) {
        if(context == null) {
            initApp(pActivity, pActivity);
        }
        if(activity != pActivity) {
            lastActivity = activity;
            activity = pActivity;
//            Toast.makeText(pActivity.getApplicationContext(), activity.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private synchronized boolean initApp(Object pAppInitial, Context pContext) {
        if(context == null) {
            context = pContext.getApplicationContext();
            return true;
        }
        return false;
    }

    public Context getContext() {
        return activity.getApplicationContext();
    }

    public Activity getActivity() {
        return activity;
    }
}
