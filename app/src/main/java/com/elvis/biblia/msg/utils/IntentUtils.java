package com.elvis.biblia.msg.utils;

import android.content.Intent;
import android.net.Uri;

import com.elvis.biblia.msg.MainActivity;
import com.elvis.biblia.msg.context.Contexts;

/**
 * Created by elvis on 08/07/15.
 */
public class IntentUtils {

    static public void intentGooglePlay(String pPackageName) {
        String url;

        try {
            Contexts.getInstance().getActivity().getPackageManager().getPackageInfo("com.android.vending", 0);
            url = "market://details?id=" + pPackageName;
        } catch ( final Exception e ) {
            url = "https://play.google.com/store/apps/details?id=" + pPackageName;
        }

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Contexts.getInstance().getActivity().startActivity(intent);
    }

    static public void intentBrowser(String pUrl) {
        Uri uri = Uri.parse(pUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Contexts.getInstance().getActivity().startActivity(intent);
    }
}
