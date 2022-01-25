package com.elvis.biblia.msg;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.elvis.biblia.msg.context.Contexts;
import com.elvis.biblia.msg.impl.IActivity;
import com.elvis.biblia.msg.ui.AbstractFragment;
import com.elvis.biblia.msg.ui.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elvis on 13/07/15.
 */
public class FragmentSalmos extends AbstractFragment implements IActivity {

    private int currentSalmos = 0;
    private Spinner spinner;
    private TextView textView;

    private static String[] from = new String[]{"display", "display"};
    private static int[] to = new int[]{R.id.simple_spitem_display, R.id.simple_spdditem_display};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_salmos, container, false);
        context = Contexts.getInstance().getContext();
        initScreen();
        return view;

    }

    @Override
    protected void initScreen() {
        String fontPath = "fonts/OpenSans-Semibold.ttf";

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);

        textView = (TextView) findViewById(R.id.section_label);
        textView.setTypeface(typeface);

        spinner = (Spinner) findViewById(R.id.section_title);

        List<Map<String, String>> mapList = new ArrayList<>();
        for(int i = 0; i < Constants.salmos.length; i++) {
            Map<String, String> row = new HashMap<>();
            mapList.add(row);
            row.put(from[0], "SALMOS " + String.valueOf(i + 1));
        }

        SimpleAdapter adapter = new SimpleAdapter(view.getContext(), mapList, R.layout.simple_spinner_item, from, to);
        adapter.setDropDownViewResource(R.layout.simple_spinner_drop_down);
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                ((TextView) view).setText(data.toString());
                currentSalmos = spinner.getSelectedItemPosition();
                showSalmos(Constants.salmos[currentSalmos]);
                return true;
            }
        });
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        setOnClickListener(R.id.btn_next);
        setOnClickListener(R.id.btn_prev);

        GUI.delayPost(new Runnable() {
            @Override
            public void run() {
                reloadData();
            }
        }, 25);
    }
    @Override
    public void setOnClickListener(int pViewResId) {
        findViewById(pViewResId).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prev:
                executePrev();
                break;
            case R.id.btn_next:
                executeNext();
                break;
        }
    }

    private void reloadData() {
        YoYo.with(Techniques.BounceInRight).duration(1000).playOn(findViewById(R.id.section_label));
    }
    private void executeNext() {
        currentSalmos = currentSalmos == 149 ? 0 : currentSalmos + 1;
        YoYo.with(Techniques.BounceInRight).duration(1000).playOn(textView);
        showSalmos(Constants.salmos[currentSalmos]);
    }

    private void executePrev() {
        currentSalmos = currentSalmos == 0 ? 149 : currentSalmos - 1;
        YoYo.with(Techniques.BounceInLeft).duration(1000).playOn(textView);
        showSalmos(Constants.salmos[currentSalmos]);
    }

    private void showSalmos(int pSalmos) {
        textView.setText(pSalmos);
        spinner.setSelection(currentSalmos);
    }
}
