package hsc.marketingmessager.view.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import hsc.marketingmessager.support.Util;
import hsc.marketingmessager.model.Eu;

/**
 * Created by Hoang Ha on 7/8/2017.
 */

public abstract class BaseAlertDialog extends AlertDialog.Builder {
    protected AlertDialog alertDialog;
    protected Eu eu;
    protected String TAG = getClass().getSimpleName();

    public BaseAlertDialog(@NonNull Context context) {
        super(context);
        eu=new Eu();
        if (isRegisterBus())
            eu.register(this);
        View view = LayoutInflater.from(context).inflate(setContentView(), null, false);
        setView(view);
        eu.bind(view);
        initVariable();
        initData();
        setCancelable(isCancel());
    }

    abstract void initVariable();

    abstract boolean isCancel();

    abstract void initData();

    abstract int setContentView();

    public AlertDialog show() {
        alertDialog = this.create();
        alertDialog.show();
        return alertDialog;
    }

    public void dismiss() {
        if (isRegisterBus())
            eu.register(this);
        eu.unbind();
        alertDialog.dismiss();
    }

    abstract boolean isRegisterBus();

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public <T> void onEvent(T t) {
    }

    public void LogE(String message) {
        Log.e(TAG, message);
    }

    public void hideKey() {
        Util.hideKeyBoardFrom(alertDialog);
    }
}
