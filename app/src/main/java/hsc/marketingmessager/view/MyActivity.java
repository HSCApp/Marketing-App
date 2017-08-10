package hsc.marketingmessager.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.OnClick;
import hsc.marketingmessager.R;
import hsc.marketingmessager.view.base.BaseActivity;

/**
 * Created by Hoang Ha on 8/3/2017.
 */

public class MyActivity extends BaseActivity {

    AdView adView;
    @BindView(R.id.rl_send_message)
    RelativeLayout rlSendMessage;

    @Override
    protected int setContentView() {
        return R.layout.main_fragment;

    }

    @OnClick(R.id.rl_send_message)
    public void onClickSendMessage(View view) {
//        Intent intent = new Intent(this, InterstitialAdActivity.class);
//        startActivity(intent);
    }

    @Override
    protected void initVariable() {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdUnit");
//        Interstitial banner = new Interstitial();
//        banner.setId("");
//
//        banner.setAdUnitString("ca-app-pub-6685676995277716/5696162317");
//        databaseReference.child("NativeBanner").push().setValue(banner);
    }

    @Override
    protected void initData() {
//        loadAd();
//        loadAdNative();

    }

    public void loadAd() {
//        if (banners.get(adPost).getId().equals("Banner") && adPost < banners.size()) {
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner_main_fragment_top));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("06B82F4D58EA261A196880F5CE5DBFBD")
                .build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                LogE("onAdLoaded");
//                rlAdView.removeView(adView);
//                rlAdView.setVisibility(View.VISIBLE);
//                rlAdView.addView(adView);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                    LogE("onAdFailedToLoad");
            }
        });

//        }
    }

    protected void loadAdNative()
    {
//      AdRequest adRequest=new AdRequest.Builder()
//              .addTestDevice("06B82F4D58EA261A196880F5CE5DBFBD")
//              .build();
//        nativeAdView.loadAd(adRequest);
//        nativeAdView.setAdListener(new AdListener(){
//            @Override
//            public void onAdFailedToLoad(int i) {
//                super.onAdFailedToLoad(i);
//                LogE("onAdFailedToLoad)))");
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                LogE("onAdLoaded)))");
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null)
            adView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null)
            adView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null)
            adView.destroy();
    }

    @Override
    protected boolean isRegisterBus() {
        return true;
    }

    @Override
    public <T> void onEvent(T t) {
        super.onEvent(t);
    }
}
