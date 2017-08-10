package hsc.marketingmessager.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import hsc.marketingmessager.model.Eu;
import hsc.marketingmessager.support.Util;

/**
 * Created by Hoang Ha on 7/26/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Eu eu;
    protected String TAG = getClass().getSimpleName();
    protected boolean isFullScreen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eu = new Eu();
        setContentView(setContentView());
        eu.bind(this);
        initVariable();
        initData();
    }

    protected abstract int setContentView();

    @Override
    protected void onStop() {
        super.onStop();
        if (isRegisterBus())
            eu.unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isRegisterBus())
            eu.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eu.unbind();
    }

    protected abstract void initVariable();

    protected abstract void initData();

    protected abstract boolean isRegisterBus();

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public <T> void onEvent(T t) {
    }

    public void LogE(String message) {
        Log.e(TAG, message);
    }

    public void LogV(String message) {
        Log.v(TAG, message);
    }

    public void hideKey() {
        Util.hideKeyBoardFrom(this);
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        View decorView = getWindow().getDecorView();
//        if (hasFocus && isFullScreen) {
//
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        } else {
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//    }
}
