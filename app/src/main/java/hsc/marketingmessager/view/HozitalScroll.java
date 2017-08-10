package hsc.marketingmessager.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import butterknife.BindView;
import hsc.marketingmessager.R;
import hsc.marketingmessager.adapter.ReadFromExcelAdapter;
import hsc.marketingmessager.data.model.NumberOnExcel;
import hsc.marketingmessager.support.IoSupport;
import hsc.marketingmessager.view.base.BaseActivity;

/**
 * Created by Hoang Ha on 8/9/2017.
 */

public class HozitalScroll extends BaseActivity implements CompoundButton.OnCheckedChangeListener{
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    ArrayList<NumberOnExcel> numberOnExcels;
    ReadFromExcelAdapter readFromExcelAdapter;

    @Override
    protected int setContentView() {
        return R.layout.hozital_layout;
    }

    @Override
    protected void initVariable() {
        numberOnExcels = IoSupport.readExcel(this, "");
        readFromExcelAdapter = new ReadFromExcelAdapter(this, numberOnExcels);
        recyclerView.setAdapter(readFromExcelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHorizontalScrollBarEnabled(true);
        cbAll.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isRegisterBus() {
        return false;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for(int i=0;i<numberOnExcels.size();++i)
            numberOnExcels.get(i).setCheck(isChecked);
        readFromExcelAdapter.setNumberOnExcels(numberOnExcels);
    }
}
