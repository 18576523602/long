package com.example.atest;

import android.content.pm.ActivityInfo;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class VideoActivity extends AppCompatActivity {

    private TextureView textureView;
    private MediaPlayer mediaPlayer;
    private LinearLayout layout;
    private SurfaceTexture surfaceTexture;

    public void toClick(){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterFullScreen();
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tinyWindow();
                return false;
            }
        });
    }

    public void enterFullScreen() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewGroup viewGroup = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        viewGroup.removeView(layout);
        viewGroup.removeAllViews();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        viewGroup.addView(layout,params);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exitFullScreen();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void exitFullScreen() {
        getSupportActionBar().show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ViewGroup viewGroup = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        viewGroup.removeView(layout);
        viewGroup.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,310);
        addContentView(layout,params);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void tinyWindow(){
        ViewGroup viewGroup = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        viewGroup.removeView(layout);
        viewGroup.removeAllViews();
        float width = getResources().getDisplayMetrics().widthPixels*0.6f;
        float heigth = getResources().getDisplayMetrics().widthPixels*0.6f*9f/16f;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)width,(int)heigth);
        params.gravity = Gravity.BOTTOM|Gravity.RIGHT;
        viewGroup.addView(layout,params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        layout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,310);
        textureView = new TextureView(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        layout.addView(textureView,params1);
        layout.setOrientation(LinearLayout.VERTICAL);
        addContentView(layout,params);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.i("test","---onSurfaceTextureAvailable---");
                try {
                    if(surfaceTexture == null){
                        surfaceTexture = surface;
                        mediaPlayer.setDataSource(VideoActivity.this, Uri.parse("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4"));
                        mediaPlayer.setSurface(new Surface(surface));
                        mediaPlayer.prepareAsync();
                    }else {
                       textureView.setSurfaceTexture(surfaceTexture);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

        toClick();
    }



}
