package hsc.marketingmessager.view.sendmessage;

import hsc.marketingmessager.R;
import hsc.marketingmessager.view.base.BaseFragment;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class SendMessage extends BaseFragment {
    @Override
    protected int setContentView() {
        return R.layout.add_number_fragment;
    }


    @Override
    protected void init() {

    }

    @Override
    protected boolean isRegisterBus() {
        return false;
    }
}
