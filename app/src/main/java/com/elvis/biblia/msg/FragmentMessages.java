package com.elvis.biblia.msg;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.elvis.biblia.msg.context.Contexts;
import com.elvis.biblia.msg.ui.AbstractFragment;
import java.util.Random;

/**
 * Created by elvis on 13/07/15.
 */
public class FragmentMessages extends AbstractFragment {

    private TextView msgView;
    private ScrollView scrollView;

    private String[] advices = {
            "Recebendo mensagem...", "Aguardando mensagem...",
            "Preparando mensagem...", "Mensagem à caminho...",
            "Nova mensagem chegando...", "Tenha fé, a mensagem vai chegar...",
            "Aguardando nova mensagem...", "Escolhendo uma mensagem especial pra você...",
            "Colhendo mensagem sagrada...", "Colhendo uma mensagem especial..."
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_messages, container, false);
        context = Contexts.getInstance().getContext();
        initScreen();
        return view;
    }

    @Override
    protected void initScreen() {
        String fontPath = "fonts/OpenSans-Semibold.ttf";

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);

        scrollView = (ScrollView) findViewById(R.id.scrollView0);

        msgView = (TextView) findViewById(R.id.msg_view);
        msgView.setTypeface(typeface);

        updateMessage();
    }

    @Override
    public void updateMessage() {
        ((MainActivity) Contexts.getInstance().getActivity()).fab.setClickable(false);

        Random random = new Random();
        final int advice = random.nextInt(advices.length);
        msgView.setText(advices[advice]);

        final int message = random.nextInt(Constants.messages.length);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                showMessage(Constants.messages[message]);
            }
        }, 2000);

        MainActivity mainActivity = (MainActivity) Contexts.getInstance().getActivity();
        mainActivity.currentMsg = mainActivity.getString(Constants.messages[message]);
    }

    private void showMessage(int pMessage) {
        YoYo.with(Techniques.BounceInDown).duration(1000).playOn(scrollView);
        msgView.setText(pMessage);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                ((MainActivity) Contexts.getInstance().getActivity()).fab.setClickable(true);
            }
        }, 500);
    }
}
