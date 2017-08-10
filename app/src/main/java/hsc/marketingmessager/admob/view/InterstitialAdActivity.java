package hsc.marketingmessager.admob.view;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import hsc.marketingmessager.view.base.BaseActivity;
import hsc.marketingmessager.R;

/**
 * Created by Hoang Ha on 8/4/2017.
 */

public class InterstitialAdActivity extends BaseActivity {
    InterstitialAd interstitialAd;

    @Override
    protected int setContentView() {
        return R.layout.empty_layout;
    }

    @Override
    protected void initVariable() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.banner_full_main_fragment));
        AdRequest adRequest = new AdRequest.Builder().
                 addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();

            }
        });
        finish();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isRegisterBus() {
        return false;
    }
}
