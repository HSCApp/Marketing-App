package hsc.marketingmessager.model;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hoang Ha on 8/3/2017.
 */

public class Eu {
    public EventBus eventBus;
    public Unbinder unbinder;

    public Eu() {
        eventBus = EventBus.getDefault();
    }

    public void register(Object o) {
        eventBus.register(o);
    }

    public void unregister(Object o) {
        eventBus.unregister(o);
    }

    public void bind(View view) {
        unbinder = ButterKnife.bind(view);
    }

    public void bind(Dialog dialog) {
        unbinder = ButterKnife.bind(dialog);
    }

    public void bind(Activity activity) {
        unbinder = ButterKnife.bind(activity);
    }

    public void bind(Fragment fragment, View view) {
        unbinder = ButterKnife.bind(fragment, view);
    }

    public void unbind() {
        if (unbinder != null)
            unbinder.unbind();
    }

    public <T> void post(T t) {
        eventBus.post(t);
    }

    public <T> void postSticky(T t) {
        eventBus.postSticky(t);
    }
}
