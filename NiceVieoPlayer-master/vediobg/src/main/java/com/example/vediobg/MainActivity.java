package com.example.vediobg;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class MainActivity extends Activity {

    private FullVedioBg fullVedioBg ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullVedioBg = (FullVedioBg) findViewById(R.id.fvb);
        getActionBar().hide();
        startVedio();
    }

    private void startVedio() {
        Log.i("MainActivity","------开始加载------");
        fullVedioBg.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo));
        fullVedioBg.start();
        fullVedioBg.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                fullVedioBg.start();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fullVedioBg.stopPlayback();
    }
}
