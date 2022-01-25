package com.elvis.biblia.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.elvis.biblia.msg.context.Contexts;
import com.elvis.biblia.msg.ui.AbstractFragment;
import com.elvis.biblia.msg.ui.GUI;
import com.elvis.biblia.msg.utils.IntentUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String currentActivity;
    private InterstitialAd interstitial;
    private int exitControl = 0;
    private String userId;
    public String currentMsg = "";
    private AbstractFragment fragment = null;
    public FloatingActionButton fab;
    private int interstitialAdControl = 0;
    private int interstitialAdControl2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Contexts.getInstance().initActivity(MainActivity.this);

        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial = new InterstitialAd(MainActivity.this);
        interstitial.setAdUnitId(getString(R.string.interstitial_ad_id));
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                fragment.updateMessage();
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitial.loadAd(adRequest);
            }
        });
        interstitial.loadAd(adRequest);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentActivity.equals(getString(R.string.nav_messages))) {
                    if(interstitialAdControl == 3) {
                        if(interstitialAdControl2 == 0) {
                            showInterstitialAd();
                        } else {
                            fragment.updateMessage();
                        }
                        interstitialAdControl2 = interstitialAdControl2 == 12 ? 0 :
                                interstitialAdControl2 + 1;
                    } else {
                        interstitialAdControl++;
                        fragment.updateMessage();
                    }
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showMessagesScreen();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(exitControl == 0) {
                exitControl++;
                GUI.toastLong("Pressione novamente para sair!");
            } else {
                Contexts.getInstance().getActivity().finish();
            }
        }
    }

    @Override
    public void onDestroy() {
        System.exit(0);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(currentActivity.equals(getString(R.string.nav_messages))) {
            menu.findItem(R.id.share).setVisible(true);
        } else {
            menu.findItem(R.id.share).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.share) {
            sendMessage(currentMsg + "\n\nBaixe você também o app " + getString(R.string.app_name) +
                    " grátis na Google Play Store e compartilhe com seus amigos:\n\n " +
                    "https://play.google.com/store/apps/details?id=" + getPackageName());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Handle share
    private void sendMessage(String pMessage) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        // Send text
        sendIntent.putExtra(Intent.EXTRA_TEXT, pMessage);
        sendIntent.setType("text/plain");

        startActivity(sendIntent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_messages && !currentActivity.equals(getString(R.string.nav_messages))) {
            showMessagesScreen();
        } else if (id == R.id.nav_salmos && !currentActivity.equals(getString(R.string.nav_salmos))) {
            showSalmosScreen();
        } else if(id == R.id.nav_more_apps && !currentActivity.equals(getString(R.string.nav_more_apps))) {
            showMoreAppsScreen();
        }
        exitControl = 0;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        invalidateOptionsMenu();
        return true;
    }

    private void showMoreAppsScreen() {
        if(fab.isShown()) fab.hide();
        Class fragmentClass = FragmentMoreApps.class;
        currentActivity = getString(R.string.nav_more_apps);

        try {
            fragment = (AbstractFragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateFragment();

        invalidateOptionsMenu();
    }

    private void showSalmosScreen() {
        if(fab.isShown()) fab.hide();
        Class fragmentClass = FragmentSalmos.class;
        currentActivity = getString(R.string.nav_salmos);

        try {
            fragment = (AbstractFragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateFragment();

        invalidateOptionsMenu();
    }

    private void showMessagesScreen() {
        if(!fab.isShown()) fab.show();
        Class fragmentClass = FragmentMessages.class;
        currentActivity = getString(R.string.nav_messages);

        try {
            fragment = (AbstractFragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateFragment();

        invalidateOptionsMenu();
    }

    private void updateFragment() {
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    public void executeGoSite(MenuItem item) {
        IntentUtils.intentBrowser("http://nowitgoesapps.xyz");
        exitControl = 0;
    }

    public void executeRate(MenuItem item) {
        IntentUtils.intentGooglePlay("com.elvis.biblia.msg");
        exitControl = 0;
    }

    private void showInterstitialAd() {
        if(interstitial != null && interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
