package hsc.marketingmessager.view.addnumber.fragment;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import hsc.marketingmessager.R;
import hsc.marketingmessager.view.addnumber.adapter.AddNumberFragmentAdapter;
import hsc.marketingmessager.view.base.BaseFragment;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class AddNumber extends BaseFragment {
    @BindView(R.id.tab_layout_add_number)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_add_number)
    ViewPager viewPager;
    private BaseFragment baseFragment;
    private ArrayList<Fragment> fragments;
    private AddNumberFragmentAdapter addNumberFragmentAdapter;
    private String[] title;
    @Override
    protected int setContentView() {
        return R.layout.add_number_fragment;
    }

    @Override
    protected void initVariable() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void init() {
        title=getContext().getResources().getStringArray(R.array.title_fragment_add_number);
        fragments=new ArrayList<>();
        baseFragment = new AddNumberFromExcel();
        fragments.add(baseFragment);
        baseFragment = new AddNumberFromContact();
        fragments.add(baseFragment);
        baseFragment = new AddNumberNew();
        fragments.add(baseFragment);
        addNumberFragmentAdapter = new AddNumberFragmentAdapter(getContext(), getChildFragmentManager(), fragments,title);
        viewPager.setAdapter(addNumberFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout tabsContainer = (LinearLayout) tabLayout.getChildAt(0);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            LinearLayout item = (LinearLayout) tabsContainer.getChildAt(i);
            LinearLayout.LayoutParams linearLayoutManager= (LinearLayout.LayoutParams) item.getLayoutParams();
            linearLayoutManager.setMargins(30,0,0,15);
            item.setLayoutParams(linearLayoutManager);
            switch (i)
            {
                case 0:
                    item.setBackground(getResources().getDrawable(R.drawable.bg_oval_a));
                    break;
                case 1:
                    item.setBackground(getResources().getDrawable(R.drawable.bg_oval_b));
                    break;
                case 2:
                    item.setBackground(getResources().getDrawable(R.drawable.bg_oval_c));
                    break;
                default:
                    item.setBackground(getResources().getDrawable(R.drawable.bg_oval_a));
            }

            TextView tv = (TextView) item.getChildAt(1);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(getContext().getResources().getDimension(R.dimen.item_tablayout_add_number_size));
        }
    }


    @Override
    protected boolean isRegisterBus() {
        return false;
    }
}
