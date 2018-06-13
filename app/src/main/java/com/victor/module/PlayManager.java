package com.victor.module;

import android.view.SurfaceView;
import android.view.TextureView;

import com.victor.app.App;
import com.victor.widget.PlayLayout;

public class PlayManager {
    public static PlayManager mPlayManager;
    private PlayLayout mPlayLayout;
    private SurfaceView mSurfaceView;
    private TextureView mTextureView;


    public SurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    public TextureView getTextureView() {
        return mTextureView;
    }
    public void setSurfaceView(SurfaceView mSurfaceView) {
        this.mSurfaceView = mSurfaceView;
    }

    public void setTextureView(TextureView mTextureView) {
        this.mTextureView = mTextureView;
    }

    public PlayManager () {
        init();
    }

    private void init () {
        mPlayLayout = new PlayLayout(App.getContext());
        mSurfaceView = new SurfaceView(App.getContext());
        mTextureView = new TextureView(App.getContext());
//        mPlayLayout.addView(mSurfaceView);
    }

    public static PlayManager getInstanse () {
        if (mPlayManager == null) {
            synchronized (PlayManager.class) {
                if (mPlayManager == null) {
                    mPlayManager = new PlayManager();
                }
            }
        }
        return mPlayManager;
    }
}
