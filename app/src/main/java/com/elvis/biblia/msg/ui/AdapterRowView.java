package com.elvis.biblia.msg.ui;

/**
 * Created by elvis on 16/09/15.
 */
public class AdapterRowView {
    private int thumbnailResource;
    private String appTitle, packageName;

    public AdapterRowView(int pThumbnailResource, String pAppTitle, String pPackageName) {
        thumbnailResource = pThumbnailResource;
        appTitle = pAppTitle;
        packageName = pPackageName;
    }

    public int getThumbnailResource() {
        return thumbnailResource;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public String getPackageName() {
        return packageName;
    }
}