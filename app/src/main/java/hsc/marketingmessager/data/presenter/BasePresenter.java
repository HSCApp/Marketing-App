package hsc.marketingmessager.data.presenter;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import hsc.marketingmessager.model.Eu;

/**
 * Created by Hoang Ha on 8/5/2017.
 */

public abstract class BasePresenter<T>  {
    protected Context mContext;
    protected Eu eu;
    protected HashMap<String, String> params;
    protected String TAG = getClass().getSimpleName();
    public BasePresenter(Context context) {
        mContext = context;
        eu = new Eu();
        eu.register(this);
    }

    public abstract void load();

    public abstract void loadCom();

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public <T> void onEvent(T t) {
    }

    protected void LogE(String message) {
        Log.e(TAG, message);
    }

    protected void LogV(String message) {
        Log.v(TAG, message);
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
