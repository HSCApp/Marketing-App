package hsc.marketingmessager.view.addnumber.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import hsc.marketingmessager.R;
import hsc.marketingmessager.adapter.ReadFromExcelAdapter;
import hsc.marketingmessager.data.model.NumberOnExcel;
import hsc.marketingmessager.view.base.BaseFragment;

import static hsc.marketingmessager.R.id.rl_open_file;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class AddNumberFromExcel extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    public static int REQUEST_FILE_PATH = 1;
    @BindView(rl_open_file)
    RelativeLayout rlOpenFile;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.fb_add)
    FloatingActionButton fbAdd;
    ArrayList<NumberOnExcel> numberOnExcels;
    ReadFromExcelAdapter readFromExcelAdapter;
    private boolean isLoadExcel = false;

    @Override
    protected int setContentView() {
        return R.layout.add_number_from_excel;
    }

    @Override
    protected void initVariable() {
        numberOnExcels = new ArrayList<>();
        readFromExcelAdapter = new ReadFromExcelAdapter(getContext(), numberOnExcels);
        LogE("initVariable");
    }

    @Override
    protected void init() {
        recyclerView.setAdapter(readFromExcelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHorizontalScrollBarEnabled(true);
        cbAll.setOnCheckedChangeListener(this);
        LogE("init");
        if (isLoadExcel)
            rlOpenFile.setVisibility(View.INVISIBLE);
    }

    @OnClick(rl_open_file)
    public void rlOpenFileClick(View view) {
        isLoadExcel = true;
        rlOpenFile.setVisibility(View.INVISIBLE);
        ReadExcelAsyncTask readExcelAsyncTask = new ReadExcelAsyncTask(getContext(), "");
        readExcelAsyncTask.execute();
    }

    @OnClick(R.id.fb_add)
    public void fbAddClick(View view) {
        int i = 0;
        for (int j = 0; j < numberOnExcels.size(); ++j)
            if(numberOnExcels.get(j).isCheck())
                i++;
        Toast.makeText(getContext(), "Chọn " + i + "/" + numberOnExcels.size()+" bản ghi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected boolean isRegisterBus() {
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i = 0; i < numberOnExcels.size(); ++i)
            numberOnExcels.get(i).setCheck(isChecked);
        readFromExcelAdapter.setNumberOnExcels(numberOnExcels);
    }

    public class ReadExcelAsyncTask extends AsyncTask<Void, NumberOnExcel, Void> {
        public NumberOnExcel numberOnExcel;
        public Context context;
        public String path;
        int max = 0;
        HashMap<Integer, String> map;

        public ReadExcelAsyncTask(Context context, String path) {
            map = new HashMap<>();
            this.context = context;
            this.path = path;
        }

        @Override
        protected Void doInBackground(Void... params) {
            InputStream stream = context.getResources().openRawResource(R.raw.mybook);
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(stream);
                XSSFSheet sheet = workbook.getSheetAt(0);
                int count = sheet.getLastRowNum() + 1;
                for (Row row : sheet) {
                    int i = row.getLastCellNum();
                    if (i > max)
                        max = i;
                }
                Row header = sheet.getRow(0);
                if (header != null) {
                    for (int j = 0; j < max; ++j) {
                        Cell cell = header.getCell(j, Row.CREATE_NULL_AS_BLANK);
                        String key = "";
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                key = String.valueOf(cell.getNumericCellValue());
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                break;
                            case Cell.CELL_TYPE_STRING:
                                key = cell.getStringCellValue();
                                break;
                        }
                        map.put(j, key);
                    }
                }
                String data = "//";
                String title = "";
                for (int i = 1; i < count; ++i) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        numberOnExcel = new NumberOnExcel();
                        for (int j = 0; j < max; ++j) {
                            Cell cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                        data = String.valueOf(dateFormat.format(cell.getDateCellValue()));
                                    } else {
                                        data = String.format("%.0f", cell.getNumericCellValue());
                                    }
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    data = cell.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    data = cell.getStringCellValue();
                                    break;
                            }
                            title = map.get(j);
                            if (title.equalsIgnoreCase("name"))
                                numberOnExcel.setName(data);
                            else if (title.equalsIgnoreCase("email"))
                                numberOnExcel.setEmail(data);
                            else if (title.equalsIgnoreCase("address"))
                                numberOnExcel.setAddress(data);
                            else if (title.equalsIgnoreCase("phoneNumber"))
                                numberOnExcel.setPhoneNumber(data);
                            else if (title.equalsIgnoreCase("birthday"))
                                numberOnExcel.setBirthday(data);
                            else if (title.equalsIgnoreCase("description"))
                                numberOnExcel.setDescription(data);

                        }
                        publishProgress(numberOnExcel);
                    }
                }
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(NumberOnExcel... values) {
            super.onProgressUpdate(values);
            numberOnExcels.add(values[0]);
//            LogE(numberOnExcels.size() + "");
//            if (numberOnExcels.size() == 100)
//                readFromExcelAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            readFromExcelAdapter.notifyDataSetChanged();
        }
    }
}
