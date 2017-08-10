package hsc.marketingmessager.view;

import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.OnClick;
import hsc.marketingmessager.R;
import hsc.marketingmessager.support.IoSupport;
import hsc.marketingmessager.view.base.BaseActivity;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class MainActivity extends BaseActivity {
    private MainFragment mainFragment;
    @BindView(R.id.ib_back)
    ImageButton back;
    @BindView(R.id.ib_menu)
    ImageButton menu;
    @BindView(R.id.ib_home)
    ImageButton home;

    @Override
    protected int setContentView() {
        return R.layout.main_activity;
    }

    @Override
    protected void initVariable() {
        mainFragment = new MainFragment();

    }

    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_activity, mainFragment)
                .commit();
    }

    @Override
    protected boolean isRegisterBus() {
        return false;
    }

    @OnClick(R.id.ib_back)
    public void backClick(View view) {
        onBackPressed();
    }

    @OnClick(R.id.ib_menu)
    public void menuClick(View view) {

    }

    @OnClick(R.id.ib_home)
    public void homeClick(View view) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_activity,mainFragment).commit();
//        getFragmentManager().popBackStack();
        LogE(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
        while (getSupportFragmentManager().getBackStackEntryCount() >= 1)
            getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
