package hsc.marketingmessager.view.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    protected List<Fragment> fragments;
    protected Context mContext;
    protected String[] title;

    public BaseFragmentPagerAdapter(Context context, FragmentManager fragmentManager, List<Fragment> fragments, String[] title) {
        super(fragmentManager);
        mContext = context;
        this.fragments = fragments;
        this.title = title;
    }

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < title.length)
            return title[position];
        else
            return "no title";
    }
}
