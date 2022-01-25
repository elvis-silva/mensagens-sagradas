package com.elvis.biblia.msg;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableRow;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.elvis.biblia.msg.context.Contexts;
import com.elvis.biblia.msg.impl.IActivity;
import com.elvis.biblia.msg.ui.AbstractFragment;
import com.elvis.biblia.msg.ui.AdapterRowView;
import com.elvis.biblia.msg.ui.ArrayAdapterView;
import com.elvis.biblia.msg.utils.IntentUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elvis on 13/07/15.
 */
public class FragmentMoreApps extends AbstractFragment {

    private Integer[] thumbIntegers = {
            R.drawable.fgts, R.drawable.finances,
            R.drawable.listadecompras, R.drawable.embaixadinhas,
            R.drawable.gogojohnny, R.drawable.flightdrone,
            R.drawable.hexa
    };

    private String[] appsNames = {
            "Simular FGTS Corrigido", "Financeirando",
            "Lista de Compras - Planilha", "Copa das Embaixadinhas",
            "Go! Go! Johnny", "Flight Drone", "Copa Rumo ao Hexa"
    };

    private String[] packages = {
            "com.elvis.fgtscorrigido.app", "com.elvis.financeirando",
            "com.elvis.shopping.list", "com.elvis.copadasembaixadinhas",
            "com.elvis.gogojohnny", "com.elvis.flightdrone",
            "com.elvis.coparumoaohexa.br"
    };

    private int position;
    public AdView adView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_more, container, false);
        context = Contexts.getInstance().getContext();
        initScreen();
        return view;
    }

    @Override
    protected void initScreen() {

        AdRequest adRequest = new AdRequest.Builder().build();

        final TableRow tabRowAdView = (TableRow) findViewById(R.id.tabRowAdView);
        adView = new AdView(context);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.admob_banner_id));
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (adView.getParent() == null) tabRowAdView.addView(adView);
            }
        });

        YoYo.with(Techniques.BounceInRight).duration(1000).playOn(findViewById(R.id.container_more));


        final List<AdapterRowView> adapterRowViews = new ArrayList<>();
        for(int i = 0; i < thumbIntegers.length; i++) {
            AdapterRowView adapterRowView = new AdapterRowView(thumbIntegers[i], appsNames[i], packages[i]);
            adapterRowViews.add(adapterRowView);
        }

        position = 0;

        //list view
        ListView listView = (ListView) findViewById(R.id.gridView1);
        listView.setAdapter(new ArrayAdapterView(context, adapterRowViews));//wallpaperThumbIntegers, wallpaperNames));//new ImageAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // imgView.setImageResource(wallpaperThumbIntegers[position]);
                AdapterRowView adapterRowView = adapterRowViews.get(position);
                IntentUtils.intentGooglePlay(adapterRowView.getPackageName());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
