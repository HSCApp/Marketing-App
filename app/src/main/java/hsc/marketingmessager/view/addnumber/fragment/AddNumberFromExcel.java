package hsc.marketingmessager.view.addnumber.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import hsc.marketingmessager.R;
import hsc.marketingmessager.view.MainFragment;
import hsc.marketingmessager.view.base.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class AddNumberFromExcel extends BaseFragment {
    public static int REQUEST_FILE_PATH=1;
    @BindView(R.id.rl_open_file)
    RelativeLayout rlOpenFile;
    @Override
    protected int setContentView() {
        return R.layout.add_number_from_excel;
    }

    @Override
    protected void init() {

    }

    @Override
    protected boolean isRegisterBus() {
        return false;
    }
    @OnClick(R.id.rl_open_file)
    public void openFile(View view)
    {
       readX();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_FILE_PATH&&resultCode==RESULT_OK)
        {
        }
    }
    public void read() throws IOException {
//        String inputFile=Environment.getExternalStorageDirectory()+"/book.xls";
//        LogE(inputFile);
//        File inputWorkbook = new File(inputFile);
//        Workbook w;
//        try {
//            w = Workbook.getWorkbook(inputWorkbook);
//            // Get the first sheet
//            Sheet sheet = w.getSheet(0);
//            // Loop over first 10 column and lines
//
//            for (int j = 0; j < sheet.getColumns(); j++) {
//                for (int i = 0; i < sheet.getRows(); i++) {
//                    Cell cell = sheet.getCell(j, i);
//                    LogE(cell.getContents());
//                }
//            }
//        } catch (BiffException e) {
//            e.printStackTrace();
//            LogE(e.toString());
//        }
    }

    public void readX()
    {
        File file=new File("");
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main_activity,new MainFragment())
                .addToBackStack("")
                .commit();
    }
}
