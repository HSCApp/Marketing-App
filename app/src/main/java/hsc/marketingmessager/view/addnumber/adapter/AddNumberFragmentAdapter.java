package hsc.marketingmessager.view.addnumber.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import hsc.marketingmessager.view.base.BaseFragmentPagerAdapter;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class AddNumberFragmentAdapter extends BaseFragmentPagerAdapter {
    public AddNumberFragmentAdapter(Context context, FragmentManager fragmentManager, List<Fragment> fragments,String[] title) {
        super(context, fragmentManager, fragments,title);
    }
}
