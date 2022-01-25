package com.elvis.biblia.msg.ui;

import android.view.View;

/**
 * Created by elvis on 09/07/15.
 */
public abstract class AbstractFragment extends Screen {

    protected View findViewById(int pId) {
        return view.findViewById(pId);
    }

    protected abstract void initScreen();

    public void updateMessage() {

    }
}
