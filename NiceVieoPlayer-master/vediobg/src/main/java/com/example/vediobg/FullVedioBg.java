package com.example.vediobg;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;

import static android.view.View.getDefaultSize;


/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class FullVedioBg extends VideoView {


    public FullVedioBg(Context context) {
        super(context);
    }

    public FullVedioBg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullVedioBg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FullVedioBg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       /* int widith = getDefaultSize(0,widthMeasureSpec);
        int height = getDefaultSize(0,heightMeasureSpec);
        super.onMeasure(widith, height);*/
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        super.setOnPreparedListener(l);
    }
}
