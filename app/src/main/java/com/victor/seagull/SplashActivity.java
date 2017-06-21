package com.victor.seagull;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.victor.util.Constant;
import com.victor.util.SharePreferencesUtil;

import java.util.Arrays;

import permission.victor.com.library.OnPermissionCallback;
import permission.victor.com.library.PermissionHelper;

public class SplashActivity extends BaseActivity implements MediaPlayer.OnCompletionListener,OnPermissionCallback {
    private String TAG = "SplashActivity";
    private View view;
    private VideoView mVvPlay;

    private PermissionHelper permissionHelper;
    private String[] neededPermission;
    private AlertDialog builder;
    private final static String[] MULTI_PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.SET_WALLPAPER,
            Manifest.permission.ACCESS_WIFI_STATE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getLayoutInflater().from(this).inflate(R.layout.activity_splash,null);
        setContentView(view);
        initialize();
        initData();
    }

    private void initialize () {
        mVvPlay = (VideoView) findViewById(R.id.vv_play);
        mVvPlay.setOnCompletionListener(this);
        permissionHelper = PermissionHelper.getInstance(this);
        permissionHelper
                .setForceAccepting(false) // default is false. its here so you know that it exists.
                .request(MULTI_PERMISSIONS);
    }

    private void initData () {
        boolean isPlayWelcome  = SharePreferencesUtil.getBoolean(this,Constant.IS_PLAY_WELCOME);
        if (!isPlayWelcome) {
            SharePreferencesUtil.putBoolean(this,Constant.IS_PLAY_WELCOME,true);
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.welcome);
            mVvPlay.setVideoURI(uri);
            mVvPlay.start();
           return;
        }

        Intent intent = new Intent(SplashActivity.this,WelcomeActivity.class);
        startActivity(intent);
        finish();

    }

    public AlertDialog getAlertDialog(final String[] permissions, final String permissionName) {
        if (builder == null) {
            builder = new AlertDialog.Builder(this)
                    .setTitle("Permission Needs Explanation")
                    .create();
        }
        builder.setButton(DialogInterface.BUTTON_POSITIVE, "Request", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionHelper.requestAfterExplanation(permissions);
            }
        });
        builder.setMessage("Permissions need explanation (" + permissionName + ")");
        return builder;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Intent intent = new Intent(SplashActivity.this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        Log.d(TAG, "onPermissionGranted-Permission(s) " + Arrays.toString(permissionName) + " Granted");
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
        Log.d("onPermissionDeclined", "Permission(s) " + Arrays.toString(permissionName) + " Declined");
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
        Log.d("onPermissionPreGranted", "Permission( " + permissionsName + " ) preGranted");
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String s) {
        neededPermission = PermissionHelper.declinedPermissions(this, MULTI_PERMISSIONS);
        StringBuilder builder = new StringBuilder(neededPermission.length);
        if (neededPermission.length > 0) {
            for (String permission : neededPermission) {
                builder.append(permission).append("\n");
            }
        }
        AlertDialog alert = getAlertDialog(neededPermission, builder.toString());
        if (!alert.isShowing()) {
            alert.show();
        }
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        Log.d("ReallyDeclined", "Permission " + permissionName + " can only be granted from settingsScreen");
    }

    @Override
    public void onNoPermissionNeeded() {
        Log.d("onNoPermissionNeeded", "Permission(s) not needed");
    }
}
