package com.elvis.biblia.msg.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elvis.biblia.msg.R;

import java.util.List;

/**
 * Created by elvis on 15/09/15.
 */
public class ArrayAdapterView extends ArrayAdapter<AdapterRowView> {
    private final Context context;
    private final List<AdapterRowView> adapterRowViews;

    //  private final String[] titles;
    //  private final Integer[] wallpaperThumbIntegers;
/*
    public ArrayAdapterView(Context pContext, Integer[] pWallpaperThumbIntegers, String[] pTitles) {
        super(pContext, -1, pTitles);
        context = pContext;
        titles = pTitles;
        wallpaperThumbIntegers = pWallpaperThumbIntegers;
    }
*/
    public ArrayAdapterView(Context pContext, List<AdapterRowView> pAdapterRowViews) {
        super(pContext, -1, pAdapterRowViews);
        context = pContext;
        adapterRowViews = pAdapterRowViews;
    }

    @Override
    public View getView(int pPosition, View pConvertView, ViewGroup pParent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;

        if(pConvertView == null) {
            pConvertView = inflater.inflate(R.layout.adapter_view, pParent, false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView) pConvertView.findViewById(R.id.thumbnail);
            holder.title = (TextView) pConvertView.findViewById(R.id.title);
            // holder.credits = (TextView) pConvertView.findViewById(R.id.credits);
            pConvertView.setTag(holder);
        } else {
            holder = (ViewHolder) pConvertView.getTag();
        }
        //View rowView = inflater.inflate(R.layout.adapter_layout, pParent, false);
        //TextView title = (TextView) pConvertView.findViewById(R.id.title);
        //ImageView thumbnail = (ImageView) pConvertView.findViewById(R.id.thumbnail);
        holder.title.setText(adapterRowViews.get(pPosition).getAppTitle());//titles[pPosition]);
        //  holder.credits.setText(adapterRowViews.get(pPosition).getPackageName());
        // change the icon for Windows and iPhone
        //String s = titles[position];
        //if (s.startsWith("iPhone")) {
        holder.thumbnail.setImageResource(adapterRowViews.get(pPosition).getThumbnailResource());//wallpaperThumbIntegers[pPosition]);//R.drawable.thumb1);
        //} else {
        //    imageView.setImageResource(R.drawable.ok);
        //}

        return pConvertView;
    }

    // Store as a tag in the row’s view after inflating it
    private class ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        // public TextView credits;
    }
}

