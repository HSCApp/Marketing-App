package hsc.marketingmessager.view;

import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import hsc.marketingmessager.R;
import hsc.marketingmessager.view.addgroup.AddGroup;
import hsc.marketingmessager.view.addnumber.fragment.AddNumber;
import hsc.marketingmessager.view.base.BaseFragment;
import hsc.marketingmessager.view.managenumber.ManagerNumber;
import hsc.marketingmessager.view.managergroup.ManagerGroup;
import hsc.marketingmessager.view.sendmessage.SendMessage;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rl_add_number)
    RelativeLayout addNumber;
    @BindView(R.id.rl_manager_number)
    RelativeLayout managerNumber;
    @BindView(R.id.rl_add_group)
    RelativeLayout addGroup;
    @BindView(R.id.rl_manager_group)
    RelativeLayout managerGroup;
    @BindView(R.id.rl_send_message)
    RelativeLayout sendMessage;

    @Override
    protected int setContentView() {
        return R.layout.main_fragment;

    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void init() {
        addNumber.setOnClickListener(this);
        managerNumber.setOnClickListener(this);
        addGroup.setOnClickListener(this);
        managerGroup.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
    }


    @Override
    protected boolean isRegisterBus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        BaseFragment fragment = null;
        switch (v.getId()) {
            case R.id.rl_add_number:
                fragment = new AddNumber();
                break;
            case R.id.rl_manager_number:
                fragment = new ManagerNumber();
                break;
            case R.id.rl_add_group:
                fragment = new AddGroup();
                break;
            case R.id.rl_manager_group:
                fragment = new ManagerGroup();
                break;
            case R.id.rl_send_message:
                fragment = new SendMessage();
                break;
        }
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_activity, fragment)
                    .addToBackStack(fragment.getTAG())
                    .commit();
        }
    }
}
