package hsc.marketingmessager.support;

import android.content.Context;
import android.util.Log;
import android.view.View;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import hsc.marketingmessager.R;
import hsc.marketingmessager.data.model.NumberOnExcel;

/**
 * Created by Hoang Ha on 8/6/2017.
 */
//name,number,email,address,birthday,group,description
public class IoSupport {
    public static ArrayList<NumberOnExcel> readExcel(Context context, String path) {
        int max = 0;
        HashMap<Integer, String> map = new HashMap<>();
        ArrayList<NumberOnExcel> NumberOnExcels = new ArrayList<>();
        NumberOnExcel numberOnExcel;
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
            for (int i = 1; i < count; ++i) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    numberOnExcel = new NumberOnExcel();
                    for (int j = 0; j < max; ++j) {
                        Cell cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
                        String data = "//";

                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                Log.e("HH ", "" + i + ":" + j + ": " + cell.getNumericCellValue());

                                if (DateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    data = String.valueOf(dateFormat.format(cell.getDateCellValue()));
                                } else {
//                                    data = Double.toString(cell.getNumericCellValue());
                                    data = String.format("%.0f", cell.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                Log.e("HH", "" + i + ":" + j + ": " + "blank");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                Log.e("HH", "" + i + ":" + j + ": " + cell.getStringCellValue());
                                data = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                Log.e("HH", "" + i + ":" + j + ": " + cell.getStringCellValue());
                                data = cell.getStringCellValue();
                                break;
                        }
                        String title = map.get(j);

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
                    NumberOnExcels.add(numberOnExcel);
                }
            }
        } catch (Exception e) {
        }
        for (int i = 0; i < NumberOnExcels.size(); ++i)
            Log.e("LIST", "" + i + " " + NumberOnExcels.get(i).toString());
        return NumberOnExcels;
    }

    public void onWriteClick(View view) {
    }

    protected String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            if (cellValue != null) {
                switch (cellValue.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = "" + cellValue.getBooleanValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        double numericValue = cellValue.getNumberValue();
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            double date = cellValue.getNumberValue();
                            SimpleDateFormat formatter =
                                    new SimpleDateFormat("dd/MM/yy");
                            value = formatter.format(HSSFDateUtil.getJavaDate(date));
                        } else {
                            value = "" + numericValue;
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        value = "" + cellValue.getStringValue();
                        break;
                    default:
                }
            } else
                value = "null";
        } catch (NullPointerException e) {

        }
        return value;
    }


}
