package com.victor.module;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;

import com.victor.util.Constant;

import java.util.Timer;
import java.util.TimerTask;

public class Player implements OnBufferingUpdateListener, OnCompletionListener,
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener,
		SurfaceHolder.Callback, OnVideoSizeChangedListener {

	public static final int PLAYER_PREPARING =				0xf100;
	public static final int PLAYER_PREPARED =				0xf101;
	public static final int PLAYER_BUFFERING_START = 		0xf102;
	public static final int PLAYER_BUFFERING_END = 		0xf103;
	public static final int PLAYER_ERROR = 					0xf104;
	public static final int PLAYER_SEEK_END =				0xf105;
	public static final int PLAYER_PROGRESS_INFO = 		0xf106;
	public static final int PLAYER_COMPLETE = 				0xf107;
	public static final int PLAYER_CAN_NOT_SEEK = 			0xf108;

	private static final String TAG = "Player";
	private int videoWidth;
	private int videoHeight;
	private MediaPlayer mMediaPlayer;
	private SurfaceView mSurfaceView; // 绘制View
	private SurfaceHolder mSurfaceHolder = null; // 显示一个surface的抽象接口，使你可以控制surface的大小和格式
	private Handler mNotifyHandler;

	private Context mContext;
	private String mPlayUrl = null;
	private SeekBar mSkbProgress;
	private boolean mIsLive;
	private int replayCount;//重播次数

	Timer mTimer = new Timer();
	TimerTask mTimerTask = new MyTimerTask();
	private boolean isTimerRun;

	public Player(Context context, SurfaceView surfaceView, Handler handler,
				  SeekBar skbProgress) {
		mContext = context;
		mNotifyHandler = handler;
		mSkbProgress = skbProgress;

		if (surfaceView != null) {
			mSurfaceView = surfaceView;
			mSurfaceHolder = mSurfaceView.getHolder();
			mSurfaceHolder.addCallback(this);
			mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 视频缓冲完就开始播放，不使用SurfaceView的缓冲区
		}
	}

	public void open(SurfaceView SurfaceView) {
		Log.e(TAG, "open()......");
		mSurfaceView = SurfaceView;
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void close() {
		Log.d(TAG, "close()......");
		stopTimer();
		if (mNotifyHandler != null) {
			mNotifyHandler.removeCallbacks(mRunable);
		}
		releaseMediaPlayer();
	}

	private void createMediaPlayer() {
		Log.e(TAG, "createMediaPlayer()......");
		try {
			if (mMediaPlayer != null) {
				mMediaPlayer.release();
				mMediaPlayer = null;
			}

			mMediaPlayer = new MediaPlayer();

			mMediaPlayer.reset();
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.setOnErrorListener(this);
			mMediaPlayer.setOnBufferingUpdateListener(this);
			mMediaPlayer.setOnInfoListener(this);
			mMediaPlayer.setOnSeekCompleteListener(this);
			mMediaPlayer.setOnCompletionListener(this);

			if (mSurfaceHolder != null) {
				mMediaPlayer.setDisplay(mSurfaceHolder); // 设置画面输出
				mMediaPlayer.setScreenOnWhilePlaying(true); // 保持屏幕高亮
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void playUrl(String videoUrl, boolean isLive) {
		Log.e(TAG, "playUrl()......" + videoUrl);
		stopTimer();
		mPlayUrl = videoUrl;
		mIsLive = isLive;
		openVideo();
	}

	private void replay() {
		replayCount++;
		stopTimer();
		Log.e(TAG, "replay()......replayCount = " + replayCount);
		if (replayCount >= 3) {
			replayCount = 0;
			stop();
			if (mNotifyHandler != null) {
				mNotifyHandler.sendEmptyMessage(Player.PLAYER_ERROR);
			}
			return;
		}
		if (mPlayUrl != null) {
			playUrl(mPlayUrl,mIsLive);
		}

	}

	public void stop() {
		Log.e(TAG, "stop()......");
		stopTimer();
		if (mMediaPlayer != null) {
			mPlayUrl = null;
			mMediaPlayer.stop();
		}
	}

	public void pause() {
		Log.e(TAG, "pause()......");
		stopTimer();
		if (mMediaPlayer != null) {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
			}
		}
	}

	public void resume() {
		Log.e(TAG, "resume()......");
		stopTimer();
		if (mMediaPlayer != null) {
			if (!mMediaPlayer.isPlaying()) {
				mMediaPlayer.start();
			}
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.e(TAG, "surfaceChanged()......");
		mSurfaceHolder = arg0;
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		Log.e(TAG, "surfaceCreated()......");
		mSurfaceHolder = arg0;
		createMediaPlayer();

		// //////////////////
		// because maybe surface view is not ready when first play,
		// but application already call playUrl,
		// then we need to play again.
		// /////////////////
		openVideo();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.e(TAG, "surfaceDestroyed()......");
		releaseMediaPlayer();
	}

	private void openVideo() {
		Log.e(TAG, "openVideo()......");
		if (mNotifyHandler != null) {
			mNotifyHandler.removeMessages(Player.PLAYER_BUFFERING_END);
		}

		if (mPlayUrl == null || mSurfaceHolder == null) {
			Log.d(TAG, "mPlayUrl or mSurfaceHolder is null");
			return;
		}
		synchronized (this) {
			try {
				if (mNotifyHandler != null) {
					mNotifyHandler.sendEmptyMessage(Player.PLAYER_PREPARING);
				}
				if (mMediaPlayer != null) {
					mMediaPlayer.reset();
					mMediaPlayer.setDataSource(mPlayUrl);
					mMediaPlayer.prepareAsync();
					startNotify();
				} else {
					Log.e(TAG,"mMediaPlayer == null");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void releaseMediaPlayer() {
		Log.e(TAG, "releaseMediaPlayer()......");
		synchronized (this) {
			if (mMediaPlayer != null) {
				mMediaPlayer.stop(); // mMediaPlayer.reset();
				mMediaPlayer.release();
			}
			mMediaPlayer = null;
		}
	}

	/**
	 * 通过onPrepared播放
	 */
	public void onPrepared(MediaPlayer mp) {
		stopTimer();
		Log.e(TAG, "onPrepared()......");

		videoWidth = mMediaPlayer.getVideoWidth();
		videoHeight = mMediaPlayer.getVideoHeight();

		if (videoHeight != 0 && videoWidth != 0) {
			if (mNotifyHandler != null) {
				mNotifyHandler.sendEmptyMessage(Player.PLAYER_PREPARED);
			}
			mp.start();

		}
	}

	public void onCompletion(MediaPlayer mp) {
		Log.e(TAG, "onCompletion()......");
		if (mIsLive) {
			replay();
			return;
		}
		if (mNotifyHandler != null) {
			mNotifyHandler.sendEmptyMessage(Player.PLAYER_COMPLETE);
		}
	}

	public void onBufferingUpdate(MediaPlayer mp, int bufferingProgress) {
		Log.e(TAG, "onBufferingUpdate()......");
		if (mSkbProgress != null) {
			mSkbProgress.setSecondaryProgress(bufferingProgress);
			int currentProgress = mSkbProgress.getMax()
					* mMediaPlayer.getCurrentPosition()
					/ mMediaPlayer.getDuration();
		}
	}

	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.e(TAG, "onError()......");
		replay();
		return false;
	}

	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		Log.e(TAG, "onInfo()......");

		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			// replay every 10s
			startTimer();
			Log.e(TAG, "onInfo-buffer start......isTimerRun = " + isTimerRun);
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			stopTimer();
			Log.e(TAG, "onInfo-buffer end......isTimerRun = " + isTimerRun);

			if (mNotifyHandler != null) {
				// here we delay send message to resume play.
				mNotifyHandler.removeMessages(Player.PLAYER_BUFFERING_END);
				mNotifyHandler.sendEmptyMessage(Player.PLAYER_BUFFERING_END);
			}

			break;
		case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
			break;
		case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
			if (mNotifyHandler != null) {
				mNotifyHandler.sendEmptyMessage(Player.PLAYER_CAN_NOT_SEEK);
			}
			break;
		case MediaPlayer.MEDIA_INFO_UNKNOWN:
			Log.e(TAG, "UNKNOWN...");
			break;
		}

		return false;
	}

	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onSeekComplete()......");
		if (mNotifyHandler != null) {
			mNotifyHandler.sendEmptyMessage(Player.PLAYER_SEEK_END);
		}
	}

	public MediaPlayer getMediaPlayer() {
		return mMediaPlayer;
	}

	public void startNotify() {
		Log.e(TAG, "startNotify()......");
		if (mNotifyHandler != null) {
			mNotifyHandler.post(mRunable);
		}
	}

	Runnable mRunable = new Runnable() {

		public void run() {
			Message msg = new Message();
			msg.what = PLAYER_PROGRESS_INFO;
			if (mNotifyHandler != null) {
				mNotifyHandler.sendMessage(msg);
				mNotifyHandler.postDelayed(mRunable, 500);
			}

		}
	};

	public void seekTo(int msec) {
		Log.e(TAG, "seekTo()......");
		if (mMediaPlayer != null) {
			mMediaPlayer.seekTo(msec);
			if (mNotifyHandler != null) {
				mNotifyHandler.sendEmptyMessage(Constant.Msg.HIDE_PLAY_CTRL_VIEW);
			}
		}
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
	}

	class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			replay();
		}

	}

	private void startTimer() {
		Log.e(TAG, "startTimer()......");
		if (mTimer == null) {
			mTimer = new Timer();
		}

		if (mTimerTask == null) {
			mTimerTask = new MyTimerTask();
		}

		if (mTimer != null && mTimerTask != null && isTimerRun == false) {
			mTimer.schedule(mTimerTask, 10000, 10000);
			isTimerRun = true;
		}

	}

	private void stopTimer() {
		Log.e(TAG, "stopTimer()......");
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}

		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}

		isTimerRun = false;
	}

}
