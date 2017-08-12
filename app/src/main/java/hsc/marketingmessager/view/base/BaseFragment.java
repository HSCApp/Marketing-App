package hsc.marketingmessager.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import hsc.marketingmessager.support.Util;
import hsc.marketingmessager.model.Eu;

/**
 * Created by Hoang Ha on 7/26/2017.
 */

public abstract class BaseFragment extends Fragment {
    protected Eu eu;
    private String TAG = getClass().getSimpleName();
    public static int READ_CONTACT=1;
    public BaseFragment()
    {
        initVariable();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setContentView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eu.bind(this,view);
        init();
    }

    protected abstract int setContentView();
    protected abstract void initVariable();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        eu = new Eu();
        if (isRegisterBus())
            eu.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (isRegisterBus())
            eu.unregister(this);
        eu.unbind();
    }
    protected abstract void init();

    protected abstract boolean isRegisterBus();

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public <T> void onEvent(T t) {
    }

    public void LogE(String message) {
        Log.e(TAG, message);
    }

    public void hideKey() {
        Util.hideKeyBoardFrom(this);
    }

    public String getTAG() {
        return TAG;
    }
}
